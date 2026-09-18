# PRENOS — state of record

Last updated: 2026-09-18

This file exists so state does not live in chat scrollback. It is the
handover note: what is in this repo, what is merged, and what is still
open. Update it in the same commit as whatever it describes.

## Repository

`OmniSlack/Testing` — default branch `master`.

Three bodies of work live here:

- **Java course exercises** — `src/main/exercise/**`, plain classes, no
  third-party dependencies.
- **OmniMax** — `omnimax/`, a Jarvis-style voice assistant console.
  Plain HTML/CSS/JS, no build step. Open `omnimax/index.html`.
- **Container setup** — `Dockerfile`, `docker-compose.yml`,
  `.dockerignore` for running an exercise in a hardened container.

## Open blockers

- **OmniEcho name / `omniecho.com`** — see `BLOCKER-OMNIECHO.md`. Raised
  2026-09-18 from an external Project card. Affects naming and the
  Loops/DNS plan only; no code in this repository references OmniEcho,
  the domain, or Loops. Ownership of the domain is UNVERIFIED and no DNS
  change is authorized.

## Status — everything is merged

| Work | PR | Merged into master |
| --- | --- | --- |
| Java exercise bug fixes | [#1](https://github.com/OmniSlack/Testing/pull/1) | yes — 2026-08-07 |
| OmniMax voice console + v1.0.0 | [#2](https://github.com/OmniSlack/Testing/pull/2) | yes — 2026-09-17 |
| Hardened Docker setup | [#3](https://github.com/OmniSlack/Testing/pull/3) | yes — 2026-09-17 |
| This notice | [#4](https://github.com/OmniSlack/Testing/pull/4) | yes — 2026-09-17 |

Nothing is pushed-but-unapplied any more. Before 2026-09-17, `master`
held only the Java exercises: #2 and #3 sat as open drafts for a month,
so anyone cloning the repo got neither `omnimax/` nor the `Dockerfile`.
Both were marked ready and squash-merged. The repo now has no open PRs
and no orphan work.

Note: merge commits are disabled on this repository — PRs squash-merge.

## Versions

- **OmniMax — 1.0.0.** Declared in three places that must stay in step:
  `APP_VERSION` in `omnimax/app.js` (source of truth, rendered as the
  chip beside the wordmark), `"version"` in `omnimax/manifest.json`, and
  the Version section of `omnimax/README.md`. Bump all three in one
  commit. Before 1.0.0 the app carried no version at all and a build
  could only be named by its commit SHA.
- `pom.xml` — `org.example:NomNom:1.0-SNAPSHOT`. Untouched since the
  original import; it covers the Java exercises only, not OmniMax.

## How this was verified

- OmniMax: rendered in Chromium at desktop and phone width — version chip
  shows, no console errors, no horizontal overflow. `node --check app.js`
  passes, `manifest.json` parses.
- Docker: no daemon was available, so the build stage was replicated
  directly — all 80 sources under `src/main/exercise` compile with JDK 21,
  the default `MAIN_CLASS` runs, the `ENTRYPOINT` shell form forwards args
  correctly, `MAIN_CLASS` override works, and `docker-compose.yml` parses
  with its hardening keys intact.

## Carrying state between sessions

The reason work used to get re-narrated across chats: state was kept in
Notion, and Notion has been returning `401`, so nothing was written
there. GitHub access works — this file is the durable record instead.

To restore the connectors if they are wanted back:

- Notion — https://claude.ai/customize/connectors
- GitHub app install — https://github.com/apps/claude/installations/select_target

Until Notion is reconnected, this file is the source of truth. Read it
first in a new session; it is faster and more accurate than re-reading
chat history.
