package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
        supportActionBar?.title = "Student Details"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bindStudentData()
        findViewById<Button>(R.id.details_edit_button).setOnClickListener {
            val studentId = intent.getStringExtra("student_id")
            startActivity(
                Intent(this, EditStudentActivity::class.java)
                    .apply { putExtra("student_id", studentId) }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        bindStudentData()
    }

    private fun bindStudentData() {
        val studentId = intent.getStringExtra("student_id")
        val student = studentId?.let { StudentRepository.findById(it) } ?: return
        findViewById<ImageView>(R.id.student_details_image_view).setImageResource(R.drawable.avatar)
        findViewById<TextView>(R.id.student_details_name_text_view).text = student.name
        findViewById<TextView>(R.id.student_details_id_text_view).text = student.id
        findViewById<TextView>(R.id.student_details_phone_text_view).text = student.phone
        findViewById<TextView>(R.id.student_details_address_text_view).text = student.address
        findViewById<CheckBox>(R.id.student_details_checked_checkbox).apply {
                isChecked = student.isChecked
                isEnabled = false
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { return when (item.itemId) { android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}