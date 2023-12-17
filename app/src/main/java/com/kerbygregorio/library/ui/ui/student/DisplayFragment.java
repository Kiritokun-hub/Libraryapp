package com.kerbygregorio.library.ui.ui.student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kerbygregorio.library.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayFragment extends Fragment {

    private RecyclerView recyclerViewResults;
    private ResultAdapter resultAdapter;

    public DisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        recyclerViewResults = view.findViewById(R.id.recyclerViewResults);
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(requireContext()));


        readData();


        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }

    private void readData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String studentName = bundle.getString("studentName", "");
            String studentEmail = bundle.getString("studentEmail", "");
            String borrowedBooks = bundle.getString("borrowedBooks", "");




            System.out.println("Student Name: " + studentName);
            System.out.println("Student Email: " + studentEmail);
            System.out.println("Borrowed Books: " + borrowedBooks);
        }

        DatabaseHelperss dbHelper = new DatabaseHelperss(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelperss.COLUMN_ID,
                DatabaseHelperss.COLUMN_NAME,
                DatabaseHelperss.COLUMN_STUDENTID,
                DatabaseHelperss.COLUMN_BORROWED_BOOKS
        };

        Cursor cursor = db.query(
                DatabaseHelperss.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<ResultItem> resultList = new ArrayList<>();

        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelperss.COLUMN_ID));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperss.COLUMN_NAME));
            String itemStudentId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperss.COLUMN_STUDENTID));
            String itemBorrowedBooks = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperss.COLUMN_BORROWED_BOOKS));

            ResultItem resultItem = new ResultItem(itemId, itemName, itemStudentId, itemBorrowedBooks);
            resultList.add(resultItem);
        }

        resultAdapter = new ResultAdapter(requireContext(), resultList);
        recyclerViewResults.setAdapter(resultAdapter);

        cursor.close();
        db.close();
    }
}
