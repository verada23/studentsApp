package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
        supportActionBar?.title = "Add New Student"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val avatarView: ImageView = findViewById(R.id.new_student_image_view)
        val nameField: EditText = findViewById(R.id.new_student_name)
        val idField: EditText = findViewById(R.id.new_student_id)
        val phoneField: EditText = findViewById(R.id.new_student_phone)
        val addressField: EditText = findViewById(R.id.new_student_address)
        val checkedBox: CheckBox = findViewById(R.id.new_student_checked)

        val cancelButton: Button = findViewById(R.id.new_student_cancel_button)
        val saveButton: Button = findViewById(R.id.new_student_save_button)

        avatarView.setImageResource(R.drawable.avatar)

        cancelButton.setOnClickListener{
            finish()
        }

        saveButton.setOnClickListener{
            val name = nameField.text.toString().trim()
            val id = idField.text.toString().trim()
            val phone = phoneField.text.toString().trim()
            val address = addressField.text.toString().trim()
            if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newStudent = Student(name, id, phone, address, checkedBox.isChecked)
            StudentRepository.add(newStudent)
            finish()
        }
    }
}