# syntax=docker/dockerfile:1
#
# Hardened container for running one of this repo's exercise programs.
#
# This repo is a set of standalone Java exercises (no external dependencies),
# each with its own class under src/main/exercise/**. MAIN_CLASS selects which
# one to run; override it at build time (--build-arg) or run time (-e).
#
# Example (prefer `docker compose run app` — see docker-compose.yml for the
# hardening flags these plain `docker run` forms don't include):
#   docker build -t nomnom-exercises .
#   docker run --rm --read-only --tmpfs /tmp --cap-drop ALL \
#     --security-opt no-new-privileges nomnom-exercises
#   # Exercises that read stdin (e.g. ToDoList) need -i:
#   docker run --rm -i -e MAIN_CLASS=com.homework.exercise.lecture6.exercise5.ToDoList \
#     nomnom-exercises
#   # To run something other than the selected exercise (e.g. for diagnostics),
#   # override the entrypoint rather than passing a trailing command:
#   docker run --rm --entrypoint java nomnom-exercises -version

## ---- Build stage: compile everything with the JDK ----
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /build
COPY src ./src
RUN mkdir -p classes && \
    find src/main/exercise -name '*.java' > sources.txt && \
    javac -d classes @sources.txt

## ---- Runtime stage: minimal JRE, non-root, only what's needed to run ----
FROM eclipse-temurin:21-jre-alpine AS runtime

# Dedicated unprivileged user — never run the app as root.
RUN addgroup -S app && adduser -S app -G app

WORKDIR /app
COPY --from=build --chown=app:app /build/classes ./classes

USER app

ARG MAIN_CLASS=com.homework.exercise.lecture11.exercise2.Main
ENV MAIN_CLASS=${MAIN_CLASS}

ENTRYPOINT ["sh", "-c", "exec java -cp classes \"$MAIN_CLASS\" \"$@\"", "--"]
