# baosp-panel

[![Android CI — baosp-panel](https://github.com/tech-master33/baosp-panel/actions/workflows/android.yml/badge.svg)](https://github.com/tech-master33/baosp-panel/actions/workflows/android.yml)

A quick-access control panel for BAOSP accessibility features — speech rate, flashlight, volume, and screen reader status — in one place. Part of the [BAOSP](https://github.com/tech-master33/baosp) ecosystem.

## Download

**Latest APK → [github.com/tech-master33/baosp/releases/tag/nightly](https://github.com/tech-master33/baosp/releases/tag/nightly)**

A fresh build is posted there automatically every night alongside the screen reader, TTS engine, launcher, clock, and calculator.
You can also find standalone builds on the [releases page](https://github.com/tech-master33/baosp-panel/releases) of this repo.

## Features

- **Speech rate control** — switch between Slow, Normal, and Fast with one tap; new rate announced immediately
- **Flashlight toggle** — turn the camera flash on/off as a torch, announced aloud
- **Volume up/down** — raise or lower media volume without navigating to system settings
- **Screen reader status** — shows whether baosp-screenreader is active; tap to open Accessibility settings
- **Overlay panel** — accessible from any app via the quick-access service
- **All controls voiced** — every action spoken aloud so no visual feedback is needed

## Installing on your device

1. Download the APK from the nightly link above
2. Transfer it to your Android device
3. Install it (allow unknown sources if prompted)
4. Open **BAOSP Panel** from your app drawer
5. Grant the **overlay** permission when prompted (needed for the floating panel)
6. Grant the **modify system settings** permission when prompted (needed for volume control)

## Building locally

```bash
git clone https://github.com/tech-master33/baosp-panel.git
cd baosp-panel
chmod +x gradlew
./gradlew assembleDebug
# APK at: app/build/outputs/apk/debug/app-debug.apk
```

Requires JDK 17 and Android SDK with API level 34.

## Permissions

| Permission | Why it is needed |
|-----------|-----------------|
| `VIBRATE` | Haptic feedback on control activation |
| `FLASHLIGHT` | Flashlight toggle |
| `WRITE_SETTINGS` | Volume control |
| `SYSTEM_ALERT_WINDOW` | Floating overlay panel visible from any app |

## CI/CD

Every push to `master` automatically builds a new APK and posts it as a GitHub Release.
The badge above shows whether the latest build passed or failed.

## BAOSP Ecosystem

baosp-panel is part of BAOSP — an accessible Android platform for blind and visually impaired users:

| Repo | What it does |
|------|-------------|
| [baosp](https://github.com/tech-master33/baosp) | Main project — nightly bundle, coordination |
| [baosp-screenreader](https://github.com/tech-master33/baosp-screenreader) | Screen reader — accessibility service |
| [baosp-tts](https://github.com/tech-master33/baosp-tts) | SVOX Pico TTS engine |
| [aoler](https://github.com/tech-master33/aoler) | Accessible home screen launcher |
| [baosp-clock](https://github.com/tech-master33/baosp-clock) | Accessible clock, alarm, timer |
| [baosp-calc](https://github.com/tech-master33/baosp-calc) | Accessible calculator |
| **[baosp-panel](https://github.com/tech-master33/baosp-panel)** | **Quick-access control panel (this repo)** |

All APKs are bundled together and published every night at  
**[github.com/tech-master33/baosp/releases/tag/nightly](https://github.com/tech-master33/baosp/releases/tag/nightly)**

## License

Apache License 2.0 — same as BAOSP and AOSP.
