package com.example.studentsapp

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getAll(): MutableList<Student> = students

    fun add(student: Student) {
        students.add(student)
    }

    fun findById(id: String):Student? {
        return students.find{ it.id==id }
    }

    fun update(id: String, updated: Student) {
        val index = students.indexOfFirst { it.id==id }
        if (index>=0) {students[index]=updated}
    }

    fun delete(id:String) {
        students.removeAll{ it.id==id }
    }
}