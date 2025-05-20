package com.example.studentsapp

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentViewHolder(itemView: View, private val listener: OnItemClickListener?): RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name_text_view)
    private val idTextView: TextView = itemView.findViewById(R.id.student_row_id_text_view)
    private val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)
    private var student: Student? = null

    init {
        checkBox.setOnClickListener{cb -> student?.isChecked = (cb as CheckBox).isChecked}
        itemView.setOnClickListener{
            val pos = adapterPosition
            if(pos != RecyclerView.NO_POSITION){
                student?.let {
                    listener?.onItemClick(pos)
                    listener?.onItemClick(it)
                }
            }
        }
    }

    fun bind(student: Student, position: Int){
        this.student = student
        nameTextView.text = student.name
        idTextView.text = student.id
        checkBox.isChecked = student.isChecked
        checkBox.tag = position
    }

}