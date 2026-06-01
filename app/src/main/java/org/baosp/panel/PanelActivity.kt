package org.baosp.panel

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class PanelActivity : AppCompatActivity() {

    private var tts: TextToSpeech? = null
    private var isFlashlightOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)
        tts = TextToSpeech(this) { if (it == TextToSpeech.SUCCESS) tts?.language = Locale.getDefault() }
        findViewById<Button>(R.id.btn_speech_slow).setOnClickListener { setRate(0.7f); speak("Slow") }
        findViewById<Button>(R.id.btn_speech_normal).setOnClickListener { setRate(1.0f); speak("Normal") }
        findViewById<Button>(R.id.btn_speech_fast).setOnClickListener { setRate(1.5f); speak("Fast") }
        findViewById<Button>(R.id.btn_flashlight).setOnClickListener { toggleFlashlight() }
        findViewById<Button>(R.id.btn_screen_reader).setOnClickListener { checkScreenReader() }
        findViewById<Button>(R.id.btn_volume_up).setOnClickListener { adjustVolume(AudioManager.ADJUST_RAISE) }
        findViewById<Button>(R.id.btn_volume_down).setOnClickListener { adjustVolume(AudioManager.ADJUST_LOWER) }
    }

    private fun setRate(rate: Float) {
        val tts2 = TextToSpeech(this) { t -> t.setSpeechRate(rate); t.shutdown() }
    }

    private fun toggleFlashlight() {
        val cm = getSystemService(CAMERA_SERVICE) as CameraManager
        try {
            isFlashlightOn = !isFlashlightOn
            cm.setTorchMode(cm.cameraIdList[0], isFlashlightOn)
            findViewById<Button>(R.id.btn_flashlight).text =
                if (isFlashlightOn) getString(R.string.flashlight_on) else getString(R.string.flashlight_off)
            speak(if (isFlashlightOn) "Flashlight on" else "Flashlight off")
        } catch (_: Exception) { Toast.makeText(this, "Flashlight unavailable", Toast.LENGTH_SHORT).show() }
    }

    private fun checkScreenReader() {
        val am = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        speak(if (am.isTouchExplorationEnabled) "Screen reader is on" else "Screen reader is off")
    }

    private fun adjustVolume(dir: Int) {
        (getSystemService(AUDIO_SERVICE) as AudioManager).adjustStreamVolume(AudioManager.STREAM_MUSIC, dir, AudioManager.FLAG_SHOW_UI)
    }

    private fun speak(text: String) { tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null) }

    override fun onDestroy() { tts?.stop(); tts?.shutdown(); super.onDestroy() }
}
