package com.example.bd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectBookActivity extends AppCompatActivity {
    private EditText editTextName, editTextAuthor;
    private Button updateButton, deleteButton;
    private DataBaseHelper dbHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        updateButton = findViewById(R.id.update);
        deleteButton = findViewById(R.id.delete);

        dbHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("ID", -1);
        String name = intent.getStringExtra("Name");
        String author = intent.getStringExtra("Author");

        if (bookId == -1) {
            Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        editTextName.setText(name);
        editTextAuthor.setText(author);

        updateButton.setOnClickListener(v -> updateBook());
        deleteButton.setOnClickListener(v -> deleteBook());
    }

    private void updateBook() {
        String newName = editTextName.getText().toString().trim();
        String newAuthor = editTextAuthor.getText().toString().trim();

        if (newName.isEmpty() || newAuthor.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int bookId = getIntent().getIntExtra("ID", -1);
        if (bookId != -1) {
            int result = dbHelper.updateBook(bookId, newName, newAuthor);

            if (result > 0) {
                Toast.makeText(this, "Книга успешно обновлена", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Не удалось обновить книгу", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }


    private void deleteBook() {
        int result = dbHelper.deleteBookById(bookId);
        if (result > 0) {
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ошибка удаления книги", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
