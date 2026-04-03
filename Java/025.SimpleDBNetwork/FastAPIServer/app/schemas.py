from __future__ import annotations

from datetime import datetime
from typing import List

from pydantic import BaseModel, Field


class Question(BaseModel):
    id: int
    text: str
    option_a: str
    option_b: str
    option_c: str
    option_d: str


class SubmissionRequest(BaseModel):
    username: str = Field(min_length=1, max_length=50)
    question_id: int
    answer: str = Field(min_length=1, max_length=1, pattern="^[A-Da-d]$")


class SubmissionResponse(BaseModel):
    correct: bool
    correct_answer: str
    explanation: str


class StatsResponse(BaseModel):
    questions: int
    submissions: int
    correct_submissions: int


class RecentSubmission(BaseModel):
    username: str
    question_id: int
    answer: str
    correct: bool
    submitted_at: datetime


class DashboardPayload(BaseModel):
    stats: StatsResponse
    submissions: List[RecentSubmission]
    questions: List[Question]
