package com.example.bd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Book> books;

    public RecyclerViewAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);

        holder.Name.setText(book.getName());
        holder.Author.setText(book.getAuthor());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SelectBookActivity.class);
            intent.putExtra("ID", book.getID_Book());
            intent.putExtra("Name", book.getName());
            intent.putExtra("Author", book.getAuthor());
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public TextView Author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.b_name);
            Author = itemView.findViewById(R.id.b_author);
        }
    }
}
