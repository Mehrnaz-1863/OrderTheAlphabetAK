package com.example.orderthealphabetak

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity




class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var btnStart: Button
    private lateinit var intent1: Intent


    companion object {
        var name: String? = null
        var selectedOption: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        init()
        intent1 = Intent(this@MainActivity, MainActivityStartGame::class.java)
    }

    private fun findViews() {
        edName = findViewById(R.id.ed_name)
        btnStart = findViewById(R.id.btn_start)
    }


    private fun init() {
        edName.setText(name)

        btnStart.setOnClickListener {
            name = edName.text.toString()

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Choose an Option")
                .setItems(arrayOf("Easy", "Medium", "Hard")) { dialog, which ->
                    selectedOption = when (which) {
                        0 -> "Easy"
                        1 -> "Medium"
                        2 -> "Hard"
                        else -> ""
                    }
                    Toast.makeText(
                        this@MainActivity,
                        "Option selected: $selectedOption",
                        Toast.LENGTH_SHORT
                    ).show()
                    intent1.putExtra("option", selectedOption)
                    startActivity(intent1)
                }
                .setNegativeButton("Cancel") { dialog, id -> dialog.dismiss() }

            val dialog = builder.create()
            dialog.show()
        }

    }
}