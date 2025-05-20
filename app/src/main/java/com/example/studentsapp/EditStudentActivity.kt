package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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

class EditStudentActivity: AppCompatActivity() {

    private var originalId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activty_edit_student)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
        supportActionBar?.title = "Edit Student"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val avatarView: ImageView = findViewById(R.id.edit_student_image_view)
        val nameField: EditText = findViewById(R.id.edit_student_name)
        val idField: EditText = findViewById(R.id.edit_student_id)
        val phoneField: EditText = findViewById(R.id.edit_student_phone)
        val addressField: EditText = findViewById(R.id.edit_student_address)
        val checkedBox: CheckBox = findViewById(R.id.edit_student_checked)

        val cancelButton: Button = findViewById(R.id.edit_cancel_button)
        val deleteButton: Button = findViewById(R.id.edit_delete_button)
        val saveButton: Button = findViewById(R.id.edit_save_button)

        originalId = intent.getStringExtra("student_id")
        val student = originalId?.let { StudentRepository.findById(it) }

        student?.let {
            avatarView.setImageResource(R.drawable.avatar)
            nameField.setText(it.name)
            idField.setText(it.id)
            phoneField.setText(it.phone)
            addressField.setText(it.address)
            checkedBox.isChecked = it.isChecked
        }

        cancelButton.setOnClickListener{
            finish()
        }
        deleteButton.setOnClickListener{
            originalId?.let{StudentRepository.delete(it)}
            val intent = Intent(this, MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP }
            startActivity(intent)
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
            val updated = Student(name, id, phone, address, checkedBox.isChecked)
            originalId?.let { oldId ->
                if (oldId != id) {
                    StudentRepository.delete(oldId)
                    StudentRepository.add(updated)
                }
                else {
                    StudentRepository.update(id, updated)
                }
            }
            val intent = Intent(this, MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP }
            startActivity(intent)
            finish()
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