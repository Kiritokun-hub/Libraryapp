package com.kerbygregorio.library;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.kerbygregorio.library.R;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);


        String description = getIntent().getStringExtra("descriptions");
        String _descriptions = getIntent().getStringExtra("_descriptions"); // Retrieve _description


        descriptionTextView.setText(_descriptions);
    }
}