package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Students List"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(StudentRepository.getAll())
        adapter.listener = this
        recyclerView.adapter = adapter
        val fab: FloatingActionButton = findViewById(R.id.add_fab)
        fab.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        val student = StudentRepository.getAll()[position]
        val intent = Intent(this, StudentDetailsActivity:: class.java)
        intent.putExtra("student_id", student.id)
        startActivity(intent)
    }

    override fun onItemClick(student: Student) {
    }
}