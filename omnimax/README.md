# OmniMax

A personalized, Jarvis-style voice assistant console — branded **OmniMax**.
Plain HTML/CSS/JS, no build step, no framework, no server. Open
`index.html` in a browser (Chrome or Edge recommended for voice input) and
it works.

## Version

**OmniMax 1.0.0** — shown as a chip next to the wordmark in the header.

The version is declared in three places and they must stay in step:

- `app.js` — `APP_VERSION`, the single source of truth the UI renders from.
- `manifest.json` — `"version"`, what the home-screen app reports.
- this section.

Bump all three in the same commit. Before this release the app carried no
version at all, so a build could only be identified by its commit SHA.

## Why plain HTML/CSS/JS

This is meant as the design/UX starting point before it gets folded into a
bigger build (an all-in-one Android app + website), which was called out as
later, separate work. Plain static files are the easiest thing to carry
into that later step, whatever framework it ends up using.

## Features

- **Jarvis-style HUD theme** — dark console, glowing cyan orb that pulses
  while OmniMax is listening or speaking, status pill in the header.
- **Voice input ("listen")** — tap the mic button to talk, or enable
  "Always listen for OmniMax" for hands-free wake-word style use (say
  "OmniMax" followed by your request). Uses the browser's built-in
  `SpeechRecognition` API.
- **Spoken replies** — OmniMax can read its replies back using the
  browser's built-in `SpeechSynthesis` API. Toggle with "Speak replies".
- **Notifications panel** — a slide-out panel (bell icon, top right) showing
  system messages and reminders you set yourself, with an unread badge.
  Set a reminder with a message + minutes-from-now; it fires as a local
  on-device notification (and gets spoken, if replies are on).
- **Chats tab** — next to Notifications: log conversations you've had on
  Claude, ChatGPT, or Gemini (platform, title, optional link). When this
  page runs as the published OmniMax artifact with the `db` capability
  granted, entries sync live across every device/tab you open it on, via
  Claude's own per-artifact store. Opened as the standalone files below
  (no such runtime), entries just stay in that browser's `localStorage`
  instead — same UI either way.
- **Installable as a home-screen app** — `manifest.json` + Apple's
  "web app capable" meta tags let iOS/Android's "Add to Home Screen" launch
  OmniMax full-screen with its own icon and name, no browser bar. This
  only works when `index.html` is opened directly (e.g. in Safari) as the
  page itself — a page embedded in another site's iframe (like a hosted
  preview link) can't control the home-screen icon, since the OS reads
  these tags from whatever page is actually at the top of the tab.
- **Chat history & notifications persist** to `localStorage` on this
  device between visits.

## Real AI replies (published artifact only)

The **published OmniMax artifact** (the claude.ai link, not these
standalone files) can use Claude's own `sample` capability to generate
real replies — it runs through your own Claude usage, with no API key to
manage or store. It's conversation-only by design: OmniMax talks, it
never takes actions in other apps or services. If that capability isn't
granted in a given view, or a call fails, it falls back to the same local
placeholder described below, with a notification saying so.

## What this app does *not* do

- These standalone files (`index.html` opened directly, or via the zip)
  call **no** external API, backend, or third-party service at all — not
  even Claude. Voice recognition and synthesis are handled by the browser
  itself; the assistant's replies come from `generateReply()` in
  `app.js`, a small local rule-based placeholder, clearly marked in the
  source. Wiring these files up to a real AI backend would be a
  deliberate, separate step left for you to set up explicitly.
- OmniMax (either version) never takes actions in other apps or
  services — it only converses.
- Notifications are local-only (the browser `Notification` API on this
  device). Nothing is pushed from a server.

## Files

- `index.html` — layout: header, notifications panel, chat thread, composer.
- `styles.css` — the Jarvis/HUD dark theme, responsive down to phone widths.
- `app.js` — all behavior: voice input/output, notifications, local reply
  engine, persistence.
- `manifest.json`, `icons/icon-512.png` — home-screen app metadata/icon.

## Installing on an iPhone (Add to Home Screen)

1. Open `index.html` directly in **Safari** (not another app's in-app
   browser/preview) — e.g. from the Files app, tap the file and choose
   "Open in Safari" if it doesn't open there by default.
2. Tap the **Share** icon, then **Add to Home Screen**.
3. OmniMax now has its own icon on your home screen and opens full-screen
   with no Safari address bar.

Note: iOS Safari does not implement the Web Speech *recognition* API at
all (it does support spoken replies) — this is an Apple platform
limitation, true whether OmniMax is installed this way, opened in a
regular Safari tab, or viewed through another app's preview. Typed input,
notifications, reminders, and the Chats tracker all work normally
regardless.

## Browser support

Voice input relies on the Web Speech API, which is best supported in
Chromium-based browsers (Chrome, Edge). The rest of the app (typed chat,
notifications, reminders) works in any modern browser; if voice input isn't
supported, OmniMax will say so in the notifications panel and the typed
composer still works normally.

## Renamed from "Navigator"

This project was originally branded "Navigator." It was renamed to
**OmniMax** because the name collided with `window.navigator`, the
browser's own built-in JavaScript object (used internally by this app,
e.g. `navigator.language`) — confusing to work with under the same name.
Renaming reset a few internal `localStorage` key names (`omnimax.*`
instead of `navigator.*`), so anyone who had used the old version will see
a fresh local state once on this version; synced Chats entries (stored via
the `db` capability, keyed independently of this name) were not affected.
