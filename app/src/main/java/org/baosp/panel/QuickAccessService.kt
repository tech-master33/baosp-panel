package org.baosp.panel

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.speech.tts.TextToSpeech
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import java.util.Locale

class QuickAccessService : AccessibilityService() {

    private var tts: TextToSpeech? = null
    private var currentRate = 1.0f

    override fun onServiceConnected() {
        super.onServiceConnected()
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) { tts?.language = Locale.getDefault(); tts?.setSpeechRate(currentRate) }
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> { adjustRate(0.1f); return true }
                KeyEvent.KEYCODE_VOLUME_DOWN -> { adjustRate(-0.1f); return true }
            }
        }
        return super.onKeyEvent(event)
    }

    private fun adjustRate(delta: Float) {
        currentRate = (currentRate + delta).coerceIn(0.5f, 2.0f)
        tts?.setSpeechRate(currentRate)
        speak("Rate $currentRate")
        vibrate()
    }

    private fun speak(text: String) { tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null) }

    private fun vibrate() {
        val v = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            (getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        else @Suppress("DEPRECATION") getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        else @Suppress("DEPRECATION") v.vibrate(50)
    }

    override fun onDestroy() { tts?.stop(); tts?.shutdown(); super.onDestroy() }
}
