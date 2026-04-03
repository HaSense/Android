from __future__ import annotations

from pathlib import Path
from typing import List

from fastapi import FastAPI, HTTPException, Request
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import HTMLResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates

from . import database
from .schemas import (
    DashboardPayload,
    Question,
    RecentSubmission,
    StatsResponse,
    SubmissionRequest,
    SubmissionResponse,
)

app = FastAPI(title="Quiz Android Backend", version="1.0.0")

database.init_db()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

static_dir = Path(__file__).resolve().parent.parent / "static"
templates = Jinja2Templates(directory=str(Path(__file__).resolve().parent.parent / "templates"))

app.mount("/static", StaticFiles(directory=static_dir), name="static")


@app.get("/api/health", response_model=StatsResponse)
def healthcheck() -> StatsResponse:
    return StatsResponse(**database.get_stats())


@app.get("/api/questions", response_model=List[Question])
def get_questions() -> List[Question]:
    rows = database.list_questions()
    return [
        Question(
            id=row["id"],
            text=row["text"],
            option_a=row["option_a"],
            option_b=row["option_b"],
            option_c=row["option_c"],
            option_d=row["option_d"],
        )
        for row in rows
    ]


@app.post("/api/submissions", response_model=SubmissionResponse)
def submit_answer(payload: SubmissionRequest) -> SubmissionResponse:
    question = database.get_question(payload.question_id)
    if question is None:
        raise HTTPException(status_code=404, detail="Question not found")

    normalized_answer = payload.answer.upper()
    correct_answer = question["answer"].upper()
    is_correct = normalized_answer == correct_answer
    database.record_submission(payload.username, payload.question_id, normalized_answer, is_correct)

    return SubmissionResponse(
        correct=is_correct,
        correct_answer=correct_answer,
        explanation="Nice work!" if is_correct else "Check the REST basics again.",
    )


@app.get("/api/submissions/summary", response_model=DashboardPayload)
def summary() -> DashboardPayload:
    stats = database.get_stats()
    recent = database.list_recent_submissions()
    questions = database.list_questions()
    return DashboardPayload(
        stats=StatsResponse(**stats),
        submissions=[
            RecentSubmission(
                username=row["username"],
                question_id=row["question_id"],
                answer=row["answer"],
                correct=bool(row["correct"]),
                submitted_at=row["submitted_at"],
            )
            for row in recent
        ],
        questions=[
            Question(
                id=row["id"],
                text=row["text"],
                option_a=row["option_a"],
                option_b=row["option_b"],
                option_c=row["option_c"],
                option_d=row["option_d"],
            )
            for row in questions
        ],
    )


@app.get("/", response_class=HTMLResponse)
def dashboard(request: Request) -> HTMLResponse:
    payload = summary()
    return templates.TemplateResponse("dashboard.html", {"request": request, "payload": payload})
