# baosp-panel Roadmap

What we plan to build next, why it matters, and what state each item is in.

Open an issue or discussion before starting work on anything here so we can coordinate.

---

## Status key

- **Planned** — not started
- **In progress** — actively being worked on
- **Needs help** — no one assigned, good place to contribute
- **Done** — shipped in the nightly build

---

## Controls

### Wi-Fi toggle — Planned

**What it is:** Turn Wi-Fi on or off from the panel with one tap.

**Why it matters:** Toggling Wi-Fi currently requires navigating to Settings → Network → Wi-Fi, which takes many swipes. A one-tap control in the panel makes it instant.

**Note:** Android 10+ removed direct Wi-Fi toggle from third-party apps. The panel will open the Wi-Fi quick settings tile or use the Settings panel API.

---

### Bluetooth toggle — Planned

**What it is:** Turn Bluetooth on or off from the panel.

**Why it matters:** Users who use Bluetooth Braille displays or Bluetooth headphones need to toggle Bluetooth frequently without navigating to Settings.

---

### Do Not Disturb toggle — Needs help

**What it is:** Toggle Do Not Disturb mode from the panel.

**Why it matters:** Blind users attending meetings or appointments need a fast way to silence notifications. The current path through Settings takes too long.

**Where to start:** `NotificationManager.setInterruptionFilter()`. Requires `ACCESS_NOTIFICATION_POLICY` permission. Announce "Do Not Disturb on" or "Do Not Disturb off" after toggling.

---

### Battery percentage announcement — Needs help

**What it is:** A panel button that announces the current battery level and charging state.

**Why it matters:** Blind users cannot glance at the status bar to check battery. They must navigate to Settings or pull down the notification shade, which takes many steps.

**Proposed approach:** Read `BatteryManager` intent. Announce "Battery 73 percent, charging" or "Battery 12 percent, not charging".

**Where to start:** Register a `BroadcastReceiver` for `ACTION_BATTERY_CHANGED` and expose a "Battery" button in the panel that speaks the last reading.

---

### Screen brightness control — Planned

**What it is:** Raise or lower screen brightness from the panel.

**Why it matters:** Some partially sighted users need higher brightness in some conditions. Navigating to Settings → Display → Brightness takes many steps.

---

## Panel behaviour

### Customisable control order — Planned

**What it is:** Let users rearrange the order of controls in the panel.

**Why it matters:** Different users use different controls most often. The most-used control should be the first one reached by swiping.

**Proposed approach:** A settings screen with a list of controls and up/down arrows to reorder them. Order persisted in SharedPreferences.

---

### Keyboard shortcut to open panel — Needs help

**What it is:** A hardware key combination (e.g. volume-up + volume-down) that opens the panel from anywhere.

**Why it matters:** The panel is most useful when it can be opened instantly from any screen without first finding the app icon.

**Where to start:** The `QuickAccessService` accessibility service already runs in the background — add a key event handler for the chosen combination.

---

## How priorities are set

Items move up the list when more users report being blocked or a contributor volunteers to lead the work.
Every item here has a stated impact on blind or disabled users.
