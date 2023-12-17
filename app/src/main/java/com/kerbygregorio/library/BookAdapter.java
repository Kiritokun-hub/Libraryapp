package com.kerbygregorio.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private final List<Book> books;
    private final OnBookClickListener onBookClickListener;

    public BookAdapter(OnBookClickListener onBookClickListener) {
        this.books = new ArrayList<>();
        this.onBookClickListener = onBookClickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book, onBookClickListener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void addBook(Book book) {
        books.add(book);
        notifyDataSetChanged();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final TextView authorTextView;
        private final ImageView bookImageView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.nameTextView);
            authorTextView = itemView.findViewById(R.id.authorsTextView);
            bookImageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(Book book, OnBookClickListener onBookClickListener) {
            titleTextView.setText(book.getName());
            authorTextView.setText(book.getAuthors());

            // Use Glide to load the image
            Glide.with(itemView)
                    .load(book.getImageResourceId())
                    .into(bookImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBookClickListener.onBookClick(book);
                }
            });
        }
    }

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }
}
