package com.st10361554.quizapp

import Category
import QuestionAdapter
import Quiz
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.st10361554.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var seekbar: SeekBar
    private lateinit var tvTimer: TextView

    private var score = 0
    private var currentQuestionIndex = 0

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var currentCategory: Category

    private lateinit var timer: CountDownTimer
    private var timeLeft = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerViewQuestions
        seekbar = binding.sbProgress
        tvTimer = binding.tvTimer

        // create a quiz object
        val quiz = Quiz()

        val categoryName = intent.getStringExtra("categoryName")
        currentCategory = quiz.quizQuestions.find { it.name == categoryName } ?: return

        questionAdapter = QuestionAdapter(currentCategory.questions) {selectedAnswer ->
            checkAnswer(selectedAnswer)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = questionAdapter

        seekbar.max = currentCategory.questions.size
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
                tvTimer.text = "Time: $timeLeft sec"
            }

            override fun onFinish() {
                checkAnswer(-1) // User didn't answer in time
            }
        }.start()
    }

    private fun checkAnswer(selectedAnswer: Int) {
        timer.cancel() // Stop timer for the current question

        val correctAnswer = currentCategory.questions[currentQuestionIndex].correctAnswerIndex
        val baseScore = 10
        val timeBonus = timeLeft

        if (selectedAnswer == correctAnswer) {
            score += baseScore + timeBonus
        }

        currentQuestionIndex++
        seekbar.progress = currentQuestionIndex

        if (currentQuestionIndex < currentCategory.questions.size) {
            recyclerView.smoothScrollToPosition(currentQuestionIndex)
            startTimer()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
//        val intent = Intent(this, ResultActivity::class.java)
//        intent.putExtra("finalScore", score)
//        startActivity(intent)
//        finish()
    }
}