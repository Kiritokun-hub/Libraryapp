package com.kerbygregorio.library.ui.ui.student;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kerbygregorio.library.R;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    TextView textViewItemId, textViewItemName, textViewItemStudentId, textViewItemBorrowedBooks;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewItemId = itemView.findViewById(R.id.textViewItemId);
        textViewItemName = itemView.findViewById(R.id.textViewItemName);
        textViewItemStudentId = itemView.findViewById(R.id.textViewItemStudentId);
        textViewItemBorrowedBooks = itemView.findViewById(R.id.textViewItemBorrowedBooks);
    }
}