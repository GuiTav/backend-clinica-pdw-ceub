@echo off
call mvnw.cmd package
docker compose up --build