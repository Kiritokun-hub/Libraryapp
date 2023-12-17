package com.kerbygregorio.library.ui.ui.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kerbygregorio.library.R;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> implements Filterable {

    private List<ResultItem> resultList; // Change this to ResultItem
    private List<ResultItem> resultListFull; // This will hold the full list for filtering
    private Context context;

    public ResultAdapter(Context context, List<ResultItem> resultList) {
        this.context = context;
        this.resultList = resultList;
        this.resultListFull = new ArrayList<>(resultList); // Initialize resultListFull
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        ResultItem resultItem = resultList.get(position);

        // Set data to the views in the ViewHolder
        holder.textViewItemId.setText("ID: " + resultItem.getItemId());
        holder.textViewItemName.setText("Name: " + resultItem.getItemName());
        holder.textViewItemStudentId.setText("Student Id: " + resultItem.getItemStudentId());
        holder.textViewItemBorrowedBooks.setText("Borrowed Books: " + resultItem.getItemBorrowedBooks());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    @Override
    public Filter getFilter() {
        return resultFilter;
    }

    private Filter resultFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ResultItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(resultListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ResultItem item : resultListFull) {
                    if (item.getItemName().toLowerCase().contains(filterPattern)
                            || item.getItemStudentId().toLowerCase().contains(filterPattern)
                            || item.getItemBorrowedBooks().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            resultList.clear();
            resultList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
