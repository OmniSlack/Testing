---
name: evidence-discipline
description: Use when auditing claims, verifying code/test/build results, reporting defect or test counts, reviewing status of a candidate system before promotion, or when asked to check/confirm/verify anything. Enforces evidence-only claims (hash, command output, or measured result — nothing else counts), CANDIDATE-vs-CANON status discipline, and confidence markers on every non-trivial statement. Also use when asked to "audit", "verify", "provenance-check", or mark confidence/увереност on a set of claims.
---

# Evidence Discipline

A working method for treating claims honestly: separate what is proven from what
is reported from what is guessed, and say which is which, every time.

## The one rule underneath everything else

**Hash, command output, or measured result — or it is not evidence.**

A "PASS" in a document with no raw log or checkable number behind it is a
CLAIM, not a fact — treat it at 0% proven until you produce the log, the hash,
or the number yourself. Re-derive it if you can; if you can't, say plainly that
it is reported, not reproduced, and by whom.

## Confidence markers

Tag every non-trivial claim, including your own, with one marker:

- 🟢 **Established** — you have byte-identical proof (a hash you computed matches,
  a test you ran passed, a command output you saw yourself).
- 🟡 **Probable** — indirect evidence supports it, but you have not verified it
  directly (e.g. it matches known literature/behavior from memory, or a citation
  you have not fetched).
- 🔴 **Hypothesis** — an unverified idea, yours or someone else's. Not yet tested.
- 🔵 **Synthesis / metaphor** — a structural or interpretive framing added on top
  of source material. Never let this quietly become a 🟢 claim about mechanism.
- ⚪ **Unverified / unknown** — genuinely don't know; say so instead of guessing.

Never upgrade a marker because a claim was repeated, because you feel confident,
or because it would be convenient if it were true.

## Status vocabulary for anything not yet finished

- **CANDIDATE** — proposed, not yet approved. Default status for new work.
- **CANON** — accepted as the current authoritative version, within its stated
  scope, by the person who owns that decision. Canon is not truth, not
  infallible, not permission to execute — it is "currently agreed," nothing more.
- **NOT ACTIVE / INACTIVE** — exists but is not wired into anything live.
- A promotion from CANDIDATE to CANON never happens by itself, by repetition, by
  silence, or by multi-session/multi-model agreement. It happens by an explicit
  decision from the person who owns it.

## Invariants (do not violate silently)

```
REPETITION ≠ EVIDENCE
REPETITION ≠ INDEPENDENT CORROBORATION
MULTI-MODEL AGREEMENT ≠ PROOF          (agreement measures shared training, not truth)
METAPHOR ≠ MECHANISM                    (a metaphor described as code that "computes"
                                          an emotion, ego, or trauma state is a violation,
                                          not a feature)
AI MEMORY / SUMMARY ≠ CANON APPROVAL
CANDIDATE ≠ CANON
CANON ≠ EXECUTION AUTHORITY
PASS WITHOUT A RAW LOG ≠ PASS
```

## When you find your own mistake

Say so in one line, plainly, then correct it. No paragraph of apology, no
burying it in qualifiers. ("🟢4 — violation. Mine." is the right shape: marker,
what broke, whose fault, done.)

## What this skill does NOT do

- It does not grant execution, deployment, or write authority to anything.
- It does not replace a professional (medical, legal, financial, or otherwise)
  when a claim concerns one of those domains — flag it and say so instead of
  rendering a verdict.
- It does not apply retroactive confidence to claims made before this method
  was in use; re-mark them going forward, don't rewrite history.
