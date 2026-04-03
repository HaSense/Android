# FastAPI Quiz Backend

A lightweight FastAPI service that exposes quiz data to the Android client and also serves an HTML dashboard for quick monitoring.

## Features

- SQLite persistence for quiz questions and submissions
- REST endpoints
  - `GET /api/questions`
  - `GET /api/submissions/summary`
  - `POST /api/submissions`
  - `GET /api/health`
- HTML5 + CSS dashboard at `/` with status cards and a submission table
- Ready for systemd via `quizAndroid1.service`

## Prerequisites

- Conda or Miniconda environment named `fast`

```bash
conda activate fast
pip install -r requirements.txt
```

## Local development

```bash
cd FastAPIServer
uvicorn app.main:app --reload --host 0.0.0.0 --port 8000
```

The Android emulator can reach the service using `http://10.0.2.2:8000`.

## Deploying with systemd

1. Copy `quizAndroid1.service` to `/etc/systemd/system/` and adjust the `User`, `WorkingDirectory`, and `Environment` paths if they differ on your host.
2. Reload and start the unit:

```bash
sudo systemctl daemon-reload
sudo systemctl enable --now quizAndroid1.service
```

Logs are appended to `/var/log/quizAndroid1.log` by default.
