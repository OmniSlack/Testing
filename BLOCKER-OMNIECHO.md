# BLOCKER — OmniEcho name / omniecho.com

Recorded: 2026-09-18 · Status: **OPEN — naming question only**
Domain ownership still unverified (steps 1–5 blocked by egress policy).
Nothing we hold is waiting on the domain — see the `omniecho-core`
finding.

Companion to `PRENOS.md`. Same rule: state does not live in chat
scrollback. Update this file in the same commit as whatever changes it.

## What the blocker says

A Project card (external — ChatGPT Project "Провери свободен домейн",
17 Sep 2026) raised that the name **OmniEcho**, and probably the domain
**omniecho.com**, are already in use by an unrelated project. That moves
the earlier Loops/DNS plan from "waiting on DNS records" to **HOLD**:
without proven control of the domain, DNS records cannot legitimately be
added and sending cannot be switched on.

## Evidence grading

| Claim | Grade | Basis |
| --- | --- | --- |
| The blocker card was added | CONFIRMED | The card itself |
| A separate project "The Omni Echo" (Chris Warren) exists | CONFIRMED | Public press coverage (KPBS, Space 4 Art) |
| `omniecho.com` resolves to A record `173.236.253.152` | UNVERIFIED | Reported in the card only; no lookup run here |
| That unrelated project controls `omniecho.com` | UNVERIFIED | Press coverage shows a project exists; it does not show who holds the domain |
| The domain blocks the Loops setup | REFUTED on our side | Neither `Testing` nor `omniecho-core` contains anything that needs the domain — see the `omniecho-core` finding below |

A press mention proves a *name* is in use. It proves nothing about
registrar, registrant or DNS authority. Those are separate records and
have to be read separately.

## Blast radius in this repository — none

`OmniSlack/Testing` contains no reference to OmniEcho, to
`omniecho.com`, to Loops, or to any DNS record. Verified locally:

    grep -rin "omniecho\|omni echo\|loops" . --exclude-dir=.git   # no matches

What is here is **OmniMax** (`omnimax/`) — a static HTML/CSS/JS voice
console, no build step, no server, no outbound calls, no mail sending,
no configured domain. It is not deployed anywhere. Nothing in this repo
is reachable over the network, so nothing here is affected by whoever
holds `omniecho.com`.

The overlap is the `Omni` prefix and the `OmniSlack` GitHub org, not
shared code, hosting or identifiers. OmniMax ≠ OmniEcho.

## Verification runbook (read-only)

Every step below is a read. None registers, transfers, or changes
anything, and none touches Loops. Run them in order; stop at the first
one that answers the ownership question.

1. **Registrar and registrant — RDAP** (structured, authoritative):

       curl -s https://rdap.verisign.com/com/v1/domain/omniecho.com

   Read: `events` (registration / expiry dates), `entities` (registrar,
   and registrant if not redacted), `status`, `nameservers`.

2. **Delegated nameservers — who actually answers for the zone**:

       curl -s -H 'accept: application/dns-json' \
         'https://dns.google/resolve?name=omniecho.com&type=NS'

   The NS set is DNS authority. Whoever can edit records lives there.
   This is the record that decides whether a Loops setup is legitimate.

3. **Current A record** (to confirm or refute the reported
   `173.236.253.152`):

       curl -s -H 'accept: application/dns-json' \
         'https://dns.google/resolve?name=omniecho.com&type=A'

4. **Existing mail/sending records** — if the zone already carries SPF,
   DKIM or a vendor verification TXT, someone else's sending setup is
   live and must not be disturbed:

       curl -s -H 'accept: application/dns-json' \
         'https://dns.google/resolve?name=omniecho.com&type=TXT'
       curl -s -H 'accept: application/dns-json' \
         'https://dns.google/resolve?name=omniecho.com&type=MX'

5. **Who the site claims to be** — fetch the homepage and compare
   against the press coverage:

       curl -sI https://omniecho.com

6. **Own-account check** — log into the registrar account(s) that could
   plausibly hold it and search for the domain. If it is not there, it
   is not yours, whatever the site shows.

Decision rule: the domain is usable only if step 1 or 6 shows the
registrant/account as ours. Neither a press article nor an IP address
settles it.

## Attempt log — 2026-09-18

Steps 1–5 were authorized and attempted. **None of them could run**: this
session's network egress policy rejects CONNECT to every external host
(403 at the gateway — `rdap.verisign.com`, `dns.google`,
`cloudflare-dns.com`, `rdap.org`, `omniecho.com`, and `google.com` alike).
Anthropic-side `WebFetch` enforces the same policy. No third-party fetch
proxy was used to get around it.

So the ownership question is **still open**. Steps 1–5 have to be run from
a machine with normal outbound access — they take under a minute.

Web search (a different, permitted channel) did return two things:

- **"The Omni Echo" is a sound-art installation**, not a software or email
  product: an immersive reverberation chamber by Chris Warren, a sound
  designer teaching at San Diego State University, shown 1–15 May 2022 at
  Art Produce, San Diego. His installations are published under
  `alloyelectric.com`, not under `omniecho.com`. That is a *signal*, not a
  finding: it weakens the assumption that this project holds the domain,
  and it does not establish who does.
- A separate `github.com/OmniEcho` organisation appears in results.
  Unverified — not inspected.

### `OmniSlack/omniecho-core` — read, and it settles the Loops question

There is an **OmniEcho repository inside our own organisation**:
`OmniSlack/omniecho-core`, public, last pushed **2026-09-17 19:07 UTC** —
the same day this blocker was raised, about twenty minutes after PR #4 was
opened. Read at `136298f` ("omniecho-core v0.3 — authority boundary
evaluator, 17 tests"). Five files, 212 lines of source in total.

It is **not a website, a product site, or anything that sends mail**. It is
a dependency-free authority-boundary evaluator for AI reasoning
(`Core.evaluate(proposal, context)` → `STOP > HOLD > CLEAR`), implementing
the KOMPAS Core Design Canon v0.1. Its own README states: *"No
dependencies. No network. No filesystem writes."* and *"External action
boundary: closed"*.

Verified directly rather than taken from the README:

    grep -rinE "omniecho\.com|loops|dns|smtp|dkim|spf|sendgrid|resend|https?://"   # 0 matches
    grep -rinE "fetch\(|http\.|https\.|require\(|import |net\.|dns\." core.js  # 0 matches
    node --test core.test.js                                                      # 17/17 pass

The test run reproduces the claim recorded in `EVIDENCE_UNIT.txt`
(17 tests, 17 pass, exit 0) on Node v22.22.2.

**Consequence for this blocker.** Neither repository under our control has
any artefact that needs `omniecho.com`: `Testing` holds a static console
that is not deployed, and `omniecho-core` is offline logic with no network
code and a deliberately closed action boundary. So "the domain blocks
Loops" has no target on our side — there is nothing here waiting on a DNS
record. The blocker is real only as a **naming** question, and only if
something that does not yet exist is meant to be published under that
name.

That reduces the domain question from blocking to preparatory. It does not
answer who holds `omniecho.com` — that still needs steps 1–5.

## Standing constraints

- No DNS changes are authorized. Nothing in this file requests any.
- No Loops configuration, verification or sending is to be enabled
  while this blocker is open.
- Outside calls made for this record, all read-only and all authorized:
  attempted RDAP/DNS/HTTP reads (blocked by egress policy, nothing
  returned), web search, and GitHub repository listing. No write, no
  registration, no Loops call.

## Next action

1. **Decide what `omniecho.com` is actually for.** Nothing we hold needs
   it today. If no artefact is planned for publication under that name,
   the domain question can be closed rather than answered.
2. **If it is still wanted: run steps 1–5** from a machine with outbound
   access and paste the output here, then re-grade the table. If the
   domain is held by the unrelated project, the decision is a rename or a
   different domain — not a DNS workaround.

Note on the name itself: the public "Omni Echo" is a sound-art
installation, a different field entirely, and it publishes under
`alloyelectric.com`. A collision on a `.com` is worth checking; a
collision on the *name* across unrelated fields is a much weaker
objection than the card implies.
