package com.cfbrownweb.chrisbrown.reactionz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_small_game.*

class SmallGameActivity : AppCompatActivity() {

    private val DEFAULTTIME: Int = 20
    private var score: Int = 0
    private var buttons: Array<ToggleButton> = emptyArray()
    private var remainingTime: Int = DEFAULTTIME
    private var gameRunning: Boolean = false

    private val handler: Handler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(inputMessage: Message) {
            if(gameRunning) {
                remainingTime--
                timerValue.text = remainingTime.toString()
                timerBar.progress = remainingTime * 5

                if (remainingTime <= 0) {
                    gameRunning = false
                    resetButtons()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_small_game)

        buttons = arrayOf(
                btn1,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                btn7,
                btn8,
                btn9,
                btn10,
                btn11,
                btn12,
                btn13
        )

        for (btn in buttons) {
            btn.setOnClickListener {
                buttonClicked(it)
            }
        }

        startStopButton.setOnClickListener {
            startGame(it)
        }
    }

    fun incrementScore() {
        score++
        scoreValue.text = score.toString()
    }

    fun resetScore() {
        score = 0
        scoreValue.text = score.toString()
        timerBar.progress = 100
    }

    fun startTimer() {
        val timerRunnable = Runnable {
            while (gameRunning) {
                Thread.sleep(1000)
                handler.sendEmptyMessage(0)
            }
        }

        val timerThread = Thread(timerRunnable)
        timerThread.start()
    }

    fun startGame(view: View?) {
        val startButton = view as Button
        resetScore()
        gameRunning = true

        //Update start button
        startButton.text = getString(R.string.reset_btn)
        startButton.setOnClickListener {
            resetGame(it)
        }

        //Trigger count down
        startTimer()

        //Select a random toggle button
        val nextBtn = buttons.random()
        nextBtn.isEnabled = true
        nextBtn.isChecked = true
    }

    fun resetGame(view: View?) {
        gameRunning = false
        remainingTime = DEFAULTTIME
        timerBar.progress = 100
        timerValue.text = DEFAULTTIME.toString()

        val startButton = view as Button
        startButton.text = getString(R.string.start_btn)

        resetButtons()

        startButton.setOnClickListener {
            startGame(it)
        }
    }

    fun resetButtons(){
        for (toggleButton in buttons) {
            toggleButton.isChecked = false
            toggleButton.isEnabled = false
        }
    }

    fun buttonClicked(view: View?) {
        val toggleButton = view as ToggleButton
        toggleButton.isChecked = false
        toggleButton.isEnabled = false

        val nextBtn = buttons.filterNot { it === toggleButton }.random()
        nextBtn.isEnabled = true
        nextBtn.isChecked = true
        incrementScore()
    }
}
