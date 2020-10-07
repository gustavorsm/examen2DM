package com.example.examen2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_raw.*
import kotlinx.android.synthetic.main.book_raw.view.*

class BookListAdapter(val items: ArrayList<Book>, val context: Context): RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_raw, parent, false)
        return BookListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val book = items.get(position)
        holder.itemView.title.text = book.title
        holder.itemView.pages.text = book.pages
        holder.itemView.editorial.text = book.editorial
        holder.itemView.author.text = book.author
        holder.itemView.description.text = book.description

        val picasso = Picasso.get()
        picasso.load(book.photoUrl).into(holder.itemView.my_image_view)
    }

    class BookListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}
