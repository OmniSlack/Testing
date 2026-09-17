# PRENOS — state of record

Last updated: 2026-09-17

This file exists so state does not live in chat scrollback. It is the
handover note: what is in this repo, what is pushed, what is merged, and
what is still waiting. Update it in the same commit as whatever it
describes.

## Repository

`OmniSlack/Testing` — default branch `master` @ `1e16bf1`
("Fix bugs in Java exercises (#1)", 2026-08-07).

Two unrelated bodies of work live here: the original Java course exercises
under `src/main/exercise/**`, and the OmniMax app (branch only, see below).

## Status

| Work | Branch | PR | Pushed | Merged into master |
| --- | --- | --- | --- | --- |
| Java exercise bug fixes | `claude/remote-control-f6hwbd` | [#1](https://github.com/OmniSlack/Testing/pull/1) | yes | **yes** (2026-08-07) |
| OmniMax voice console (`omnimax/`) | `claude/jarvis-navigator-voice-input-nif84l` @ `7c262eb` | [#2](https://github.com/OmniSlack/Testing/pull/2) | yes | **no — open draft** |
| Hardened Docker setup | `claude/pqc-audit-enforcement-6ek8gv` @ `4ef1469` | [#3](https://github.com/OmniSlack/Testing/pull/3) | yes | **no — open draft** |
| This notice | `claude/geet-version-sync-5ytnpv` | — | — | — |

Nothing is sitting unpushed. Every piece of work above reached GitHub.
What has not happened is the second step: #2 and #3 are still **draft** PRs,
so `master` carries none of it. Anyone cloning `master` gets the Java
exercises and nothing else — no `omnimax/`, no `Dockerfile`.

To apply either one: mark the PR ready for review, then merge it.

## Versions

- `pom.xml` — `org.example:NomNom:1.0-SNAPSHOT`. Untouched since the
  original import; it covers the Java exercises only.
- `omnimax/` — carries no version field. `manifest.json` has no `version`
  key and the README states none. If OmniMax is going to be released or
  installed as a home-screen app, give it a version there first; right now
  "which OmniMax is this" can only be answered with a commit SHA
  (currently `7c262eb`, "Rename Navigator to OmniMax").

## Carrying state between sessions

The reason work gets re-narrated across chats: state was being kept in
Notion, and Notion has been returning `401`, so nothing was written there.
GitHub access is working now — this file is the durable record instead.
Two things restore the connectors if they are wanted back:

- Notion — https://claude.ai/customize/connectors
- GitHub app install — https://github.com/apps/claude/installations/select_target

Until Notion is reconnected, this file is the source of truth. Read it
first in a new session; it is faster and more accurate than re-reading
chat history.
