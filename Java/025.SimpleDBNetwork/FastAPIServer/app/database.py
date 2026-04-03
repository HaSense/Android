from __future__ import annotations

import sqlite3
from contextlib import contextmanager
from pathlib import Path
from typing import Generator

DB_PATH = Path(__file__).resolve().parent.parent / "quiz.db"


def get_connection() -> sqlite3.Connection:
    conn = sqlite3.connect(DB_PATH)
    conn.row_factory = sqlite3.Row
    return conn


@contextmanager
def db_cursor() -> Generator[sqlite3.Cursor, None, None]:
    conn = get_connection()
    try:
        cursor = conn.cursor()
        yield cursor
        conn.commit()
    finally:
        conn.close()


def init_db() -> None:
    DB_PATH.parent.mkdir(parents=True, exist_ok=True)
    with db_cursor() as cursor:
        cursor.execute(
            """
            CREATE TABLE IF NOT EXISTS questions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                text TEXT NOT NULL,
                option_a TEXT NOT NULL,
                option_b TEXT NOT NULL,
                option_c TEXT NOT NULL,
                option_d TEXT NOT NULL,
                answer TEXT NOT NULL
            )
            """
        )
        cursor.execute(
            """
            CREATE TABLE IF NOT EXISTS submissions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL,
                question_id INTEGER NOT NULL,
                answer TEXT NOT NULL,
                correct INTEGER NOT NULL,
                submitted_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY(question_id) REFERENCES questions(id)
            )
            """
        )

    with db_cursor() as cursor:
        cursor.execute("SELECT COUNT(*) FROM questions")
        (count,) = cursor.fetchone()
        if count == 0:
            seed_questions = [
                (
                    "REST API에서 자원을 새로 만들 때 가장 많이 쓰는 HTTP 메서드는?",
                    "GET",
                    "POST",
                    "PUT",
                    "DELETE",
                    "B",
                ),
                (
                    "이 데모 백엔드가 사용하는 데이터베이스는?",
                    "MySQL",
                    "SQLite",
                    "MongoDB",
                    "PostgreSQL",
                    "B",
                ),
                (
                    "FastAPI는 어떤 ASGI 툴킷을 기반으로 구현되었나?",
                    "Django",
                    "Starlette",
                    "Flask",
                    "Quart",
                    "B",
                ),
            ]
            cursor.executemany(
                """
                INSERT INTO questions (text, option_a, option_b, option_c, option_d, answer)
                VALUES (?, ?, ?, ?, ?, ?)
                """,
                seed_questions,
            )


def get_stats() -> dict:
    with db_cursor() as cursor:
        cursor.execute("SELECT COUNT(*) FROM questions")
        (question_count,) = cursor.fetchone()
        cursor.execute("SELECT COUNT(*) FROM submissions")
        (submission_count,) = cursor.fetchone()
        cursor.execute(
            """
            SELECT COUNT(*) FROM submissions WHERE correct = 1
            """
        )
        (correct_count,) = cursor.fetchone()

    return {
        "questions": question_count,
        "submissions": submission_count,
        "correct_submissions": correct_count,
    }


def list_questions() -> list[sqlite3.Row]:
    with db_cursor() as cursor:
        cursor.execute(
            """
            SELECT id, text, option_a, option_b, option_c, option_d FROM questions
            ORDER BY id
            """
        )
        return cursor.fetchall()


def get_question(question_id: int) -> sqlite3.Row | None:
    with db_cursor() as cursor:
        cursor.execute(
            """
            SELECT id, text, option_a, option_b, option_c, option_d, answer
            FROM questions WHERE id = ?
            """,
            (question_id,),
        )
        return cursor.fetchone()


def record_submission(username: str, question_id: int, answer: str, correct: bool) -> None:
    with db_cursor() as cursor:
        cursor.execute(
            """
            INSERT INTO submissions (username, question_id, answer, correct)
            VALUES (?, ?, ?, ?)
            """,
            (username, question_id, answer.upper(), 1 if correct else 0),
        )


def list_recent_submissions(limit: int = 20) -> list[sqlite3.Row]:
    with db_cursor() as cursor:
        cursor.execute(
            """
            SELECT username, question_id, answer, correct, submitted_at
            FROM submissions
            ORDER BY submitted_at DESC
            LIMIT ?
            """,
            (limit,),
        )
        return cursor.fetchall()
