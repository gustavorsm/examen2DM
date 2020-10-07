package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_raw.*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val lista = arrayListOf<Book>()
        //lista.add(Book("Roberto Carlos Callisaya Mamani", "calyr.software@gmail.com","Editorial1","Gustavo","Fragil1","url"))


        GlobalScope.launch {
            val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
            val repository = BookRepository(bookDao)
            repository.insert(Book("El Don de Dios", "4", "ExampleEditorial", "Gustavo", "Fragil","https://i.pinimg.com/564x/a8/6e/26/a86e26dffbcd0f8ffd0b7a6a4809ec68.jpg"))
            val lista = repository.getListBooks()
            lista.forEach {
                Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Pages: ${it.pages}, Editorial: ${it.editorial}, Author: ${it.author}, Description: ${it.description}, PhotoUrl: ${it.photoUrl}")
            }

            val bookListAdapter = BookListAdapter(lista as ArrayList<Book>, applicationContext)
            recyclerView.adapter = bookListAdapter
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = linearLayoutManager
        }

    }
    fun nextActivity(view: View) {
        startActivity(Intent(this, CreateBookActivity::class.java))
    }
}
