package com.example.tugaspap3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var viewButton: Button
    private lateinit var nameInput: EditText
    private lateinit var nimInput: EditText
    private var studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi komponen
        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        viewButton = findViewById(R.id.viewButton)
        nameInput = findViewById(R.id.editTextName)
        nimInput = findViewById(R.id.editTextNim)

        // Database Helper
        databaseHelper = DatabaseHelper(this)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Klik Tombol untuk menambah data
        addButton.setOnClickListener {
            addStudentData() // Fungsi untuk menambah data
        }

        // Klik Tombol untuk melihat data
        viewButton.setOnClickListener {
            showStudentData() // Langsung tampilkan data
        }
    }

    // Fungsi untuk menambah data mahasiswa
    private fun addStudentData() {
        val name = nameInput.text.toString()
        val nim = nimInput.text.toString()

        if (name.isNotEmpty() && nim.isNotEmpty()) {
            databaseHelper.addStudent(name, nim)
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()

            // Kosongkan input setelah menambah data
            nameInput.text.clear()
            nimInput.text.clear()
        } else {
            Toast.makeText(this, "Nama dan NIM harus diisi", Toast.LENGTH_SHORT).show()
        }
    }

    // Fungsi untuk langsung menampilkan data mahasiswa
    private fun showStudentData() {
        studentList.clear()
        studentList.addAll(databaseHelper.getAllStudents())
        studentAdapter.notifyDataSetChanged()

        // Pastikan RecyclerView terlihat
        recyclerView.visibility = View.VISIBLE
    }
}
