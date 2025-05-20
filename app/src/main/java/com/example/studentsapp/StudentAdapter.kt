package com.example.studentsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student)
}

class StudentAdapter (private val students: MutableList<Student>): RecyclerView.Adapter<StudentViewHolder>(){
    var listener: OnItemClickListener? = null
    override fun getItemCount(): Int = students.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(itemView, listener)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position], position)
    }
}

