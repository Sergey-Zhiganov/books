package com.example.bd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    private EditText editTextName, editTextAuthor;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_add_book);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        Button addButton = findViewById(R.id.add);

        dbHelper = new DataBaseHelper(this);

        addButton.setOnClickListener(v -> {
             addBookToDatabase();
        });
    }

    private void addBookToDatabase() {
        String name = editTextName.getText().toString().trim();
        String author = editTextAuthor.getText().toString().trim();

        if (name.isEmpty() || author.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addBook(name, author);

        if (result > 0) {
            Toast.makeText(this, "Книга добавлена.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddBookActivity.this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(this, "Не удалось добавить книгу", Toast.LENGTH_SHORT).show();
        }
    }
}
