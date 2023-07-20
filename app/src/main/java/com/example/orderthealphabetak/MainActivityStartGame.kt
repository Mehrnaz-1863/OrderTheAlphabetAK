package com.example.orderthealphabetak

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random


class MainActivityStartGame : AppCompatActivity() {

    private lateinit var tv_welcome: TextView
    private lateinit var btn_word: Button
    private lateinit var btn_randomWord: Button
    private lateinit var ed_word: EditText
    private lateinit var intentToPage2: Intent
    private var option: String? = null

    companion object {
        var counter = 0
        var previousCounter = 0
    }


    private val easyWords = arrayOf("obok", "heoll", "enp", "zipaz")
    private val mediumWords = arrayOf("elanteph", "gaffeir", "lnio", "ertig")
    private val hardWords = arrayOf("chlateoco", "strrrawbey", "pinpleeap", "wermeatlon")

    private lateinit var random: Random
    private var modifiedRandomWorld: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_start_game)

        val intent = intent
        option = intent.getStringExtra("option")

        findViews()
        init()

    }

    private fun findViews() {
        tv_welcome = findViewById(R.id.tv_welcome_page1)
        btn_word = findViewById(R.id.btn_p1_word)
        ed_word = findViewById(R.id.ed_p1_word)
        btn_randomWord = findViewById(R.id.btn_p1_randomWord)
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        tv_welcome.text = "Welcome " + MainActivity.name

        btn_randomWord.setOnClickListener {
            btn_word.setBackgroundColor(Color.WHITE)
            ed_word.setText("")

            when (option) {
                "Easy" -> {
                    for (i in easyWords.indices) {
                        random = Random()
                        val randomIndex = random.nextInt(easyWords.size)
                        val randomWord = easyWords[randomIndex]
                        btn_word.text = randomWord

                        btn_randomWord.isEnabled = false
                        modifiedRandomWorld = randomWord

                        when (randomWord) {
                            "obok" -> modifiedRandomWorld = "book"
                            "heoll" -> modifiedRandomWorld = "hello"
                            "enp" -> modifiedRandomWorld = "pen"
                            "zipaz" -> modifiedRandomWorld = "pizza"
                        }

                        userGuess()
                    }
                }

                "Medium" -> {
                    for (i in mediumWords.indices) {
                        random = Random()
                        val randomIndex = random.nextInt(mediumWords.size)
                        val randomWord = mediumWords[randomIndex]
                        btn_word.text = randomWord

                        btn_randomWord.isEnabled = false
                        modifiedRandomWorld = randomWord

                        when (randomWord) {
                            "elanteph" -> modifiedRandomWorld = "elephant"
                            "gaffeir" -> modifiedRandomWorld = "giraffe"
                            "lnio" -> modifiedRandomWorld = "lion"
                            "ertig" -> modifiedRandomWorld = "tiger"
                        }

                        userGuess()
                    }
                }

                "Hard" -> {
                    for (i in hardWords.indices) {
                        random = Random()
                        val randomIndex = random.nextInt(hardWords.size)
                        val randomWord = hardWords[randomIndex]
                        btn_word.text = randomWord

                        btn_randomWord.isEnabled = false
                        modifiedRandomWorld = randomWord

                        when (randomWord) {
                            "chlateoco" -> modifiedRandomWorld = "chocolate"
                            "strrrawbey" -> modifiedRandomWorld = "strawberry"
                            "pinpleeap" -> modifiedRandomWorld = "pineapple"
                            "wermeatlon" -> modifiedRandomWorld = "watermelon"
                        }

                        userGuess()
                    }
                }

                else -> Toast.makeText(
                    this@MainActivityStartGame,
                    "Option mode error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun userGuess() {
        btn_word.setOnClickListener {
            val userGuess = ed_word.text.toString().trim().toLowerCase()
            if (userGuess == modifiedRandomWorld?.toLowerCase()) {
                counter++
                Toast.makeText(
                    this@MainActivityStartGame,
                    "Correct!\nScore: $counter",
                    Toast.LENGTH_SHORT
                ).show()
                btn_word.setBackgroundColor(Color.GREEN)
            } else {
                Toast.makeText(
                    this@MainActivityStartGame,
                    "Incorrect!\nScore: $counter",
                    Toast.LENGTH_SHORT
                ).show()
                btn_word.setBackgroundColor(Color.RED)
            }
            btn_randomWord.isEnabled = true
        }
    }
}