# BLOCKER — OmniEcho name / omniecho.com

Recorded: 2026-09-18 · Status: **OPEN — on hold, unverified**

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

## Standing constraints

- No DNS changes are authorized. Nothing in this file requests any.
- No Loops configuration, verification or sending is to be enabled
  while this blocker is open.
- No outside API was called to produce this record; every line is
  either local repository evidence or a re-statement of the card,
  graded above.

## Next action

Run steps 1–2 above, paste the output here, and re-grade the table. If
the domain is held by the unrelated project, the decision is a rename or
a different domain — not a DNS workaround.
