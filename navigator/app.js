"use strict";

/* =========================================================================
 * NAVIGATOR — local-only voice assistant console
 *
 * Everything in this file runs entirely in the browser:
 *   - voice input uses the browser's built-in SpeechRecognition
 *   - spoken replies use the browser's built-in SpeechSynthesis
 *   - notifications use the browser's built-in Notification API
 *   - chat history / notifications persist to localStorage on this device
 *
 * No network requests are made anywhere in this file. `generateReply()`
 * below is a deliberately simple, local placeholder "brain" — swap it for
 * a real backend/LLM call when you're ready, but that is an explicit,
 * separate decision (outside-API calls are intentionally not wired up
 * here without confirmation).
 * ========================================================================= */

const STORAGE_KEYS = {
  messages: "navigator.messages",
  notifications: "navigator.notifications",
  chats: "navigator.chats",
  lang: "navigator.lang",
};

const els = {
  statusPill: document.getElementById("status-pill"),
  statusText: document.getElementById("status-text"),
  orb: document.getElementById("orb"),
  orbCaption: document.getElementById("orb-caption"),
  thread: document.getElementById("thread"),
  composer: document.getElementById("composer"),
  composerInput: document.getElementById("composer-input"),
  micBtn: document.getElementById("mic-btn"),
  voiceReplyToggle: document.getElementById("voice-reply-toggle"),
  wakeWordToggle: document.getElementById("wake-word-toggle"),
  notifToggle: document.getElementById("notif-toggle"),
  notifBadge: document.getElementById("notif-badge"),
  notifPanel: document.getElementById("notif-panel"),
  notifList: document.getElementById("notif-list"),
  notifClear: document.getElementById("notif-clear"),
  reminderForm: document.getElementById("reminder-form"),
  reminderText: document.getElementById("reminder-text"),
  reminderMinutes: document.getElementById("reminder-minutes"),
  tabButtons: document.querySelectorAll(".tab-btn"),
  tabNotifications: document.getElementById("tab-notifications"),
  tabChats: document.getElementById("tab-chats"),
  chatList: document.getElementById("chat-list"),
  chatForm: document.getElementById("chat-form"),
  chatPlatform: document.getElementById("chat-platform"),
  chatTitle: document.getElementById("chat-title"),
  chatLink: document.getElementById("chat-link"),
  chatsClear: document.getElementById("chats-clear"),
  langSelect: document.getElementById("lang-select"),
};

/* ---------------------------------------------------------------------- *
 * State
 * ---------------------------------------------------------------------- */

let messages = loadJSON(STORAGE_KEYS.messages, []);
let notifications = loadJSON(STORAGE_KEYS.notifications, []);
let chats = loadJSON(STORAGE_KEYS.chats, []);
let unreadCount = 0;

let currentLang = "auto";
try {
  currentLang = localStorage.getItem(STORAGE_KEYS.lang) || "auto";
} catch { /* ignore */ }
els.langSelect.value = currentLang;

function resolvedLang() {
  return currentLang === "auto" ? (navigator.language || "en-US") : currentLang;
}

function loadJSON(key, fallback) {
  try {
    const raw = localStorage.getItem(key);
    return raw ? JSON.parse(raw) : fallback;
  } catch {
    return fallback;
  }
}

function saveMessages() {
  try {
    localStorage.setItem(STORAGE_KEYS.messages, JSON.stringify(messages.slice(-200)));
  } catch { /* storage may be unavailable (e.g. private mode) — fine to skip */ }
}

function saveNotifications() {
  try {
    localStorage.setItem(STORAGE_KEYS.notifications, JSON.stringify(notifications.slice(-100)));
  } catch { /* ignore */ }
}

function saveChats() {
  try {
    localStorage.setItem(STORAGE_KEYS.chats, JSON.stringify(chats.slice(-200)));
  } catch { /* ignore */ }
}

/* ---------------------------------------------------------------------- *
 * Rendering
 * ---------------------------------------------------------------------- */

function renderThread() {
  els.thread.innerHTML = "";
  for (const m of messages) {
    const div = document.createElement("div");
    div.className = `msg ${m.from}`;
    div.innerHTML = `<span class="who">${m.from === "user" ? "You" : "Navigator"}</span>${escapeHtml(m.text)}`;
    els.thread.appendChild(div);
  }
  els.thread.scrollTop = els.thread.scrollHeight;
}

function renderNotifications() {
  els.notifList.innerHTML = "";

  if (notifications.length === 0) {
    const empty = document.createElement("li");
    empty.className = "notif-empty";
    empty.textContent = "No notifications yet.";
    els.notifList.appendChild(empty);
  } else {
    for (const n of [...notifications].reverse()) {
      const li = document.createElement("li");
      li.className = `notif-item kind-${n.kind}`;
      const time = new Date(n.at).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
      li.innerHTML = `<div class="n-top"><span>${n.kind === "reminder" ? "Reminder" : "System"}</span><span>${time}</span></div>${escapeHtml(n.text)}`;
      els.notifList.appendChild(li);
    }
  }

  els.notifBadge.hidden = unreadCount === 0;
  els.notifBadge.textContent = String(unreadCount);
}

function platformClass(platform) {
  return "p-" + platform.toLowerCase().replace(/\s+/g, "-");
}

function renderChats() {
  els.chatList.innerHTML = "";

  if (chats.length === 0) {
    const empty = document.createElement("li");
    empty.className = "notif-empty";
    empty.textContent = "No chats tracked yet.";
    els.chatList.appendChild(empty);
    return;
  }

  for (const c of [...chats].reverse()) {
    const li = document.createElement("li");
    li.className = "chat-item";
    const time = new Date(c.at).toLocaleDateString([], { month: "short", day: "numeric" });
    const linkHtml = c.link
      ? `<a class="c-link" href="${escapeHtml(c.link)}" target="_blank" rel="noopener noreferrer">Open ↗</a>`
      : `<span></span>`;
    li.innerHTML = `
      <div class="c-top">
        <span class="platform-badge ${platformClass(c.platform)}">${escapeHtml(c.platform)}</span>
        <span>${time}</span>
      </div>
      <span class="c-title">${escapeHtml(c.title)}</span>
      ${linkHtml}
    `;
    const delBtn = document.createElement("button");
    delBtn.className = "text-btn";
    delBtn.textContent = "Remove";
    delBtn.style.marginTop = "6px";
    delBtn.addEventListener("click", () => removeChat(c.id));
    li.appendChild(delBtn);
    els.chatList.appendChild(li);
  }
}

function removeChat(id) {
  if (dbApi) {
    dbApi.collection("chats").doc(id).delete().catch((err) => {
      addNotification("system", `Couldn't remove that synced chat (${err.code}).`);
    });
    return; // onSnapshot updates the list
  }
  chats = chats.filter((c) => c.id !== id);
  saveChats();
  renderChats();
}

function escapeHtml(str) {
  const div = document.createElement("div");
  div.textContent = str;
  return div.innerHTML;
}

/* ---------------------------------------------------------------------- *
 * Messages
 * ---------------------------------------------------------------------- */

function addMessage(from, text) {
  messages.push({ from, text, at: Date.now() });
  saveMessages();
  renderThread();
}

function addNotification(kind, text) {
  notifications.push({ kind, text, at: Date.now() });
  saveNotifications();
  unreadCount += 1;
  renderNotifications();

  if (Notification && Notification.permission === "granted") {
    try {
      new Notification("Navigator", { body: text, silent: true });
    } catch { /* some browsers restrict this outside a user gesture */ }
  }
}

/* ---------------------------------------------------------------------- *
 * Status / orb visual state
 * ---------------------------------------------------------------------- */

function setStatus(mode, caption) {
  els.statusPill.classList.remove("listening", "speaking");
  els.orb.classList.remove("listening", "speaking");

  if (mode === "listening") {
    els.statusPill.classList.add("listening");
    els.orb.classList.add("listening");
    els.statusText.textContent = "Listening";
  } else if (mode === "speaking") {
    els.statusPill.classList.add("speaking");
    els.orb.classList.add("speaking");
    els.statusText.textContent = "Speaking";
  } else {
    els.statusText.textContent = "Standing by";
  }

  if (caption) els.orbCaption.textContent = caption;
}

/* ---------------------------------------------------------------------- *
 * Local "brain" — replace with a real backend/LLM call when ready.
 * Intentionally simple and fully offline.
 * ---------------------------------------------------------------------- */

function generateReply(input) {
  const text = input.trim().toLowerCase();

  if (!text) return "I didn't catch that — try again?";

  if (/\b(hi|hello|hey)\b/.test(text)) {
    return "Hello. Navigator online and listening.";
  }
  if (/time is it|current time/.test(text)) {
    return `It's ${new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}.`;
  }
  if (/what.*date|today's date/.test(text)) {
    return `Today is ${new Date().toLocaleDateString(undefined, { weekday: "long", year: "numeric", month: "long", day: "numeric" })}.`;
  }
  if (/remind me/.test(text)) {
    return "I can set that — use the reminder box in the Notifications panel with how many minutes from now.";
  }
  if (/who are you|your name/.test(text)) {
    return "I'm Navigator — your on-device assistant console. No requests of yours leave this browser unless you wire me up to a backend.";
  }
  if (/thank/.test(text)) {
    return "Anytime.";
  }

  return `Heard: "${input.trim()}". I'm running on a local placeholder responder right now — connect me to a real backend to make this smarter.`;
}

function speak(text) {
  if (!els.voiceReplyToggle.checked || !("speechSynthesis" in window)) return;

  const utter = new SpeechSynthesisUtterance(text);
  utter.lang = resolvedLang();
  utter.rate = 1.02;
  utter.pitch = 0.95;
  utter.onstart = () => setStatus("speaking");
  utter.onend = () => setStatus("idle", 'Say "Navigator" or tap the mic to talk to me.');

  window.speechSynthesis.cancel();
  window.speechSynthesis.speak(utter);
}

function handleUserUtterance(text) {
  if (!text.trim()) return;
  addMessage("user", text);
  const reply = generateReply(text);
  addMessage("navigator", reply);
  speak(reply);
}

/* ---------------------------------------------------------------------- *
 * Composer (typed input)
 * ---------------------------------------------------------------------- */

els.composer.addEventListener("submit", (e) => {
  e.preventDefault();
  const text = els.composerInput.value;
  els.composerInput.value = "";
  handleUserUtterance(text);
});

/* ---------------------------------------------------------------------- *
 * Voice input (Web Speech API — browser-native, on-device recognition
 * where the platform supports it; nothing here is sent to a third party
 * by this code).
 * ---------------------------------------------------------------------- */

const SpeechRecognitionImpl = window.SpeechRecognition || window.webkitSpeechRecognition;
let recognizer = null;
let micOn = false;
let wakeWordMode = false;
let suppressAutoRestart = false;

function buildRecognizer() {
  const r = new SpeechRecognitionImpl();
  r.continuous = true;
  r.interimResults = true;
  r.lang = resolvedLang();

  r.onstart = () => setStatus("listening");

  r.onresult = (event) => {
    let finalText = "";
    for (let i = event.resultIndex; i < event.results.length; i++) {
      if (event.results[i].isFinal) finalText += event.results[i][0].transcript;
    }
    if (!finalText) return;

    if (wakeWordMode) {
      const lower = finalText.toLowerCase();
      const idx = lower.indexOf("navigator");
      if (idx === -1) return; // ignore anything not addressed to Navigator
      const after = finalText.slice(idx + "navigator".length).replace(/^[,:\s]+/, "");
      handleUserUtterance(after || finalText);
    } else {
      handleUserUtterance(finalText);
    }
  };

  r.onerror = (event) => {
    if (event.error === "not-allowed" || event.error === "service-not-allowed") {
      addNotification("system", "Microphone access was blocked. Allow it in your browser's site settings, then tap the mic again.");
      stopListening();
    } else if (event.error === "audio-capture") {
      addNotification("system", "No microphone was found on this device.");
      stopListening();
    } else if (event.error === "network") {
      addNotification("system", "Voice recognition lost its network connection and stopped.");
      stopListening();
    }
  };

  r.onend = () => {
    if (micOn && !suppressAutoRestart) {
      // keep listening continuously until the user turns it off
      try { r.start(); } catch { /* already starting */ }
    } else if (!micOn) {
      setStatus("idle", 'Say "Navigator" or tap the mic to talk to me.');
    }
  };

  return r;
}

function startListening() {
  if (!SpeechRecognitionImpl) {
    addNotification("system", "This browser doesn't support voice input. Try Chrome or Edge.");
    return;
  }
  if (!window.isSecureContext) {
    addNotification("system", "Voice input needs a secure page (https:// or a local file) — this page isn't one.");
    return;
  }
  if (!recognizer) recognizer = buildRecognizer();
  micOn = true;
  els.micBtn.classList.add("active");
  try {
    recognizer.start();
  } catch (err) {
    // Most permission failures surface later via recognizer.onerror, but a
    // synchronous throw here (e.g. mic blocked outright by the page's
    // embedding context) would otherwise fail silently — always say something.
    if (err && err.name !== "InvalidStateError") {
      micOn = false;
      els.micBtn.classList.remove("active");
      addNotification("system", "Couldn't start the microphone here — it may be blocked by this page's embedding context. Try opening Navigator directly in a browser tab.");
    }
  }
}

function stopListening() {
  micOn = false;
  els.micBtn.classList.remove("active");
  if (recognizer) {
    try { recognizer.stop(); } catch { /* ignore */ }
  }
  setStatus("idle", 'Say "Navigator" or tap the mic to talk to me.');
}

els.micBtn.addEventListener("click", () => {
  if (micOn) stopListening();
  else startListening();
});

els.langSelect.addEventListener("change", () => {
  currentLang = els.langSelect.value;
  try { localStorage.setItem(STORAGE_KEYS.lang, currentLang); } catch { /* ignore */ }

  const wasOn = micOn;
  suppressAutoRestart = true;
  if (recognizer) {
    try { recognizer.stop(); } catch { /* ignore */ }
  }
  recognizer = null;

  // give the old recognizer a moment to fully stop before rebuilding
  // with the new language, so the two don't overlap.
  setTimeout(() => {
    suppressAutoRestart = false;
    if (wasOn) startListening();
  }, 150);

  addNotification("system", `Voice language set to ${els.langSelect.selectedOptions[0].textContent}.`);
});

els.wakeWordToggle.addEventListener("change", () => {
  wakeWordMode = els.wakeWordToggle.checked;
  if (wakeWordMode) {
    els.orbCaption.textContent = 'Always listening — say "Navigator" followed by your request.';
    startListening();
  } else {
    els.orbCaption.textContent = 'Say "Navigator" or tap the mic to talk to me.';
    if (micOn) stopListening();
  }
});

/* ---------------------------------------------------------------------- *
 * Notifications panel
 * ---------------------------------------------------------------------- */

els.notifToggle.addEventListener("click", () => {
  els.notifPanel.classList.toggle("collapsed");
  if (!els.notifPanel.classList.contains("collapsed")) {
    unreadCount = 0;
    renderNotifications();
  }
});

els.notifClear.addEventListener("click", () => {
  notifications = [];
  saveNotifications();
  unreadCount = 0;
  renderNotifications();
});

/* ---------------------------------------------------------------------- *
 * Tabs (Notifications / Chats) inside the same slide-out panel
 * ---------------------------------------------------------------------- */

els.tabButtons.forEach((btn) => {
  btn.addEventListener("click", () => {
    els.tabButtons.forEach((b) => b.classList.remove("active"));
    btn.classList.add("active");
    const isChats = btn.dataset.tab === "chats";
    els.tabChats.hidden = !isChats;
    els.tabNotifications.hidden = isChats;
  });
});

/* ---------------------------------------------------------------------- *
 * Chats tracker — a manual log of conversations you've had elsewhere
 * (Grok, ChatGPT, Gemini, Copilot, etc). Nothing is fetched from those
 * services by this code — you log entries yourself.
 *
 * When this page runs as a published Navigator artifact with the `db`
 * capability granted, entries sync live across every device/tab you open
 * it on (Claude's own per-artifact store — no third-party service
 * involved). Outside that context (the standalone files opened directly
 * in a browser), there's no such runtime and entries just stay local to
 * that browser, same as before.
 * ---------------------------------------------------------------------- */

let dbApi = null;

async function initChatSync() {
  if (!(window.claude && typeof window.claude.use === "function")) return;
  try {
    dbApi = await window.claude.use("db");
  } catch {
    dbApi = null;
  }
  if (!dbApi) return; // not granted/available here — stays local-only, silently
  subscribeChats();
}

function subscribeChats() {
  dbApi.collection("chats").orderBy("at", "asc").limit(200).onSnapshot(
    (snap) => {
      chats = snap.docs.map((d) => ({ id: d.id, ...d.data() }));
      renderChats();
    },
    (err) => {
      addNotification("system", `Chat sync stopped (${err.code}). New entries will stay local to this browser.`);
      dbApi = null;
    },
  );
}

els.chatForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const title = els.chatTitle.value.trim();
  if (!title) return;

  const entry = {
    platform: els.chatPlatform.value,
    title,
    link: els.chatLink.value.trim(),
    at: Date.now(),
  };

  if (dbApi) {
    try {
      await dbApi.collection("chats").add(entry);
      // onSnapshot delivers the update; nothing else to do here.
    } catch (err) {
      addNotification("system", `Couldn't sync that chat (${err.code}). Saved locally instead.`);
      chats.push({ id: `${Date.now()}-${Math.random().toString(36).slice(2, 7)}`, ...entry });
      saveChats();
      renderChats();
    }
  } else {
    chats.push({ id: `${Date.now()}-${Math.random().toString(36).slice(2, 7)}`, ...entry });
    saveChats();
    renderChats();
  }

  addNotification("system", `Tracked a new ${entry.platform} chat: "${entry.title}"`);

  els.chatTitle.value = "";
  els.chatLink.value = "";
});

els.chatsClear.addEventListener("click", async () => {
  if (dbApi) {
    try {
      const snap = await dbApi.collection("chats").get();
      await Promise.all(snap.docs.map((d) => dbApi.collection("chats").doc(d.id).delete()));
    } catch (err) {
      addNotification("system", `Couldn't clear synced chats (${err.code}).`);
    }
    return; // onSnapshot updates the list
  }
  chats = [];
  saveChats();
  renderChats();
});

initChatSync();

els.reminderForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const text = els.reminderText.value.trim();
  const minutes = Math.max(0, Number(els.reminderMinutes.value) || 0);
  if (!text) return;

  const fireAt = new Date(Date.now() + minutes * 60_000);
  addNotification("system", `Reminder set for ${fireAt.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}: "${text}"`);

  setTimeout(() => {
    addNotification("reminder", text);
    speak(`Reminder: ${text}`);
  }, minutes * 60_000);

  els.reminderText.value = "";
});

/* Ask for notification permission on first interaction (never on load,
 * and never silently — the browser requires a user gesture anyway). */
document.addEventListener(
  "click",
  () => {
    if ("Notification" in window && Notification.permission === "default") {
      Notification.requestPermission();
    }
  },
  { once: true },
);

/* ---------------------------------------------------------------------- *
 * Boot
 * ---------------------------------------------------------------------- */

renderThread();
renderNotifications();
renderChats();

if (messages.length === 0) {
  addMessage("navigator", "Navigator online. Voice recognition and replies run entirely in this browser — nothing is sent off this device.");
}
if (notifications.length === 0) {
  addNotification("system", "Navigator initialized.");
  unreadCount = 0; // don't badge the very first boot notice
  renderNotifications();
}
