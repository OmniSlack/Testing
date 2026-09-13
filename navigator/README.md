# Navigator

A personalized, Jarvis-style voice assistant console — branded **Navigator**.
Plain HTML/CSS/JS, no build step, no framework, no server. Open
`index.html` in a browser (Chrome or Edge recommended for voice input) and
it works.

## Why plain HTML/CSS/JS

This is meant as the design/UX starting point before it gets folded into a
bigger build (an all-in-one Android app + website), which was called out as
later, separate work. Plain static files are the easiest thing to carry
into that later step, whatever framework it ends up using.

## Features

- **Jarvis-style HUD theme** — dark console, glowing cyan orb that pulses
  while Navigator is listening or speaking, status pill in the header.
- **Voice input ("listen")** — tap the mic button to talk, or enable
  "Always listen for Navigator" for hands-free wake-word style use (say
  "Navigator" followed by your request). Uses the browser's built-in
  `SpeechRecognition` API.
- **Spoken replies** — Navigator can read its replies back using the
  browser's built-in `SpeechSynthesis` API. Toggle with "Speak replies".
- **Notifications panel** — a slide-out panel (bell icon, top right) showing
  system messages and reminders you set yourself, with an unread badge.
  Set a reminder with a message + minutes-from-now; it fires as a local
  on-device notification (and gets spoken, if replies are on).
- **Chat history & notifications persist** to `localStorage` on this
  device between visits.

## What this app does *not* do

- It does **not** call any external API, backend, or third-party service.
  Voice recognition and synthesis are handled by the browser itself; no
  audio or text is sent anywhere by this code.
- The assistant's replies come from `generateReply()` in `app.js` — a
  small, local, rule-based placeholder ("brain"). It's clearly marked in
  the source. Wiring Navigator up to a real AI backend is a deliberate,
  separate step (an outside API call), left for you to decide on and set
  up explicitly rather than added silently here.
- Notifications are local-only (the browser `Notification` API on this
  device). Nothing is pushed from a server.

## Files

- `index.html` — layout: header, notifications panel, chat thread, composer.
- `styles.css` — the Jarvis/HUD dark theme, responsive down to phone widths.
- `app.js` — all behavior: voice input/output, notifications, local reply
  engine, persistence.

## Browser support

Voice input relies on the Web Speech API, which is best supported in
Chromium-based browsers (Chrome, Edge). The rest of the app (typed chat,
notifications, reminders) works in any modern browser; if voice input isn't
supported, Navigator will say so in the notifications panel and the typed
composer still works normally.
