# BLOCKER — OmniEcho name / omniecho.com

Recorded: 2026-09-18 · Status: **OPEN — on hold, unverified**
Last attempt: 2026-09-18 — steps 1–5 blocked by egress policy (see log)

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
| The domain blocks the Loops setup | PROPOSED | Follows only if the ownership claim above is confirmed |

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

### Material finding: `OmniSlack/omniecho-core`

There is an **OmniEcho repository inside our own organisation**, confirmed
against the GitHub API (not search index): `OmniSlack/omniecho-core`,
public, `can_push: true`, last pushed **2026-09-17 19:07 UTC** — the same
day this blocker was raised, roughly twenty minutes after PR #4 was
opened.

This reframes the blocker. "OmniEcho" is not only an outside name; it is
an active repository under our own org. Whether it is the thing the Loops
plan belongs to, and whether it carries domain or sending configuration,
is unknown — the repository is outside this session's allowed scope and
was not read.

Until it is read, treat the name collision as **unresolved in both
directions**: an unrelated art project uses the name publicly, and we
ourselves have a repo under it.

## Standing constraints

- No DNS changes are authorized. Nothing in this file requests any.
- No Loops configuration, verification or sending is to be enabled
  while this blocker is open.
- No outside API was called to produce this record; every line is
  either local repository evidence or a re-statement of the card,
  graded above.

## Next action

Two things, in this order:

1. **Read `OmniSlack/omniecho-core`.** It is ours and it is active. Before
   arguing about an outside name collision, establish what we already
   have under that name and whether any domain or sending config lives
   there. Requires adding the repo to the session.
2. **Run steps 1–5 from a machine with outbound access** and paste the
   output here, then re-grade the table.

If the domain turns out to be held by the unrelated project, the decision
is a rename or a different domain — not a DNS workaround.
