# Contributing to baosp-panel

Thank you for contributing to baosp-panel, the quick-access control panel built for BAOSP.
This guide is written to work well with screen readers and keyboard-only navigation.
Every step is numbered and linear — no visual layout is assumed.

---

## Ways to contribute

1. Report a bug — describe something that does not work as expected
2. Request a new panel control — describe a setting blind users need quick access to
3. Improve documentation — fix unclear steps or add missing information
4. Test builds — install nightly APKs and report accessibility issues
5. Write code — fix bugs or add features

---

## Claiming an issue

1. Open the issue you want to work on
2. Read the full description, including the "Where to start" section if there is one
3. Leave a comment: "I'd like to take this on"
4. Wait for a maintainer to assign it to you before starting

---

## Before you start

1. A GitHub account — github.com/join
2. Git — git-scm.com
3. Android Studio or VS Code
4. Java 17 — adoptium.net
5. Android SDK (API level 34) — installed by Android Studio

---

## Step 1 — Fork

1. Open github.com/tech-master33/baosp-panel
2. Activate Fork → Create fork
3. Your copy is at github.com/YOUR-USERNAME/baosp-panel

---

## Step 2 — Clone

```bash
git clone https://github.com/YOUR-USERNAME/baosp-panel.git
cd baosp-panel
git remote add upstream https://github.com/tech-master33/baosp-panel.git
```

---

## Step 3 — Branch

```bash
git checkout -b your-branch-name
```

Examples: `fix/flashlight-toggle-crash`, `feature/wifi-toggle`, `a11y/speech-rate-announcement`

---

## Step 4 — Make changes

Key files:

- `app/src/main/java/org/baosp/panel/` — all Kotlin source
- `app/src/main/res/` — XML layouts and strings
- `app/src/main/AndroidManifest.xml` — permissions and components
- `app/src/main/res/xml/accessibility_service_config.xml` — overlay service config

### Accessibility rules

1. Every control must have a `contentDescription` that includes its current state
   (e.g. "Flashlight: off" not just "Flashlight")
2. Every state change must be announced via TTS immediately after it takes effect
3. Touch targets must be at least 48dp
4. All user-facing strings must be in `strings.xml`
5. Permissions must be requested with a clear spoken explanation of why they are needed
6. Test with the screen reader on before submitting

---

## Step 5 — Build and test

```bash
chmod +x gradlew
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

Checklist:
- App builds without errors
- Panel opens correctly and grants overlay permission
- Speech rate buttons cycle Slow → Normal → Fast and announce the new rate
- Flashlight toggles and announces "on" or "off"
- Volume buttons change volume and announce the new level
- Screen reader status is correct and tapping opens Accessibility settings
- All controls reachable by swipe navigation

---

## Step 6 — Commit

```bash
git add .
git commit -m "fix: speech rate announcement uses wrong string

The rate button was announcing 'speech' instead of 'Speech rate: Fast'.
Fixed the contentDescription format string."
```

Types: `fix`, `feature`, `docs`, `refactor`, `a11y`, `test`

---

## Step 7 — Push and pull request

```bash
git push origin your-branch-name
```

1. Open github.com/YOUR-USERNAME/baosp-panel
2. Activate Compare and pull request
3. Title: one sentence — what changed
4. Description: what problem does this solve, how did you test it
5. Activate Create pull request

---

## Reporting a bug

1. Open github.com/tech-master33/baosp-panel/issues
2. Activate New issue → Bug report
3. Include: which control failed, what happened, your Android version and device

---

## Community

- Issues: github.com/tech-master33/baosp-panel/issues
- Discussions: github.com/tech-master33/baosp-panel/discussions
- Screen reader: github.com/tech-master33/baosp-screenreader
- TTS engine: github.com/tech-master33/baosp-tts
- BAOSP main: github.com/tech-master33/baosp
