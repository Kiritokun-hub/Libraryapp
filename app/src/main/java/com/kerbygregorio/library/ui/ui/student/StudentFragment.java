package com.kerbygregorio.library.ui.ui.student;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kerbygregorio.library.R;

public class StudentFragment extends Fragment {

    private EditText nameEditText, emailEditText, editTextBorrowedBooks;
    private DatabaseHelperss dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        nameEditText = view.findViewById(R.id.editTextName);
        emailEditText = view.findViewById(R.id.editTextEmail);
        editTextBorrowedBooks = view.findViewById(R.id.editTextBorrowedBooks);
        dbHelper = new DatabaseHelperss(requireContext());

        Button addButton = view.findViewById(R.id.buttonAdd);
        Button readButton = view.findViewById(R.id.buttonRead);
        Button updateButton = view.findViewById(R.id.buttonUpdate);
        Button deleteButton = view.findViewById(R.id.buttonDelete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use Navigation Component to navigate to nav_display
                Navigation.findNavController(v).navigate(R.id.nav_display);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        return view;
    }

    private void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperss.COLUMN_NAME, nameEditText.getText().toString());
        values.put(DatabaseHelperss.COLUMN_STUDENTID, emailEditText.getText().toString());
        values.put(DatabaseHelperss.COLUMN_BORROWED_BOOKS, editTextBorrowedBooks.getText().toString());

        long newRowId = db.insert(DatabaseHelperss.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(requireContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Error inserting data", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void updateData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperss.COLUMN_STUDENTID, emailEditText.getText().toString());

        String selection = DatabaseHelperss.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {nameEditText.getText().toString()};

        int count = db.update(
                DatabaseHelperss.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count > 0) {
            Toast.makeText(requireContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Error updating data", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void deleteData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = DatabaseHelperss.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {nameEditText.getText().toString()};

        int count = db.delete(DatabaseHelperss.TABLE_NAME, selection, selectionArgs);

        if (count > 0) {
            Toast.makeText(requireContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Error deleting data", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
