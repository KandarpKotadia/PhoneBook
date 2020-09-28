package com.example.phonebook;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_number);
        TextView number = findViewById(R.id.etDisplayNumber);
        number.setText(getIntent().getStringExtra("Number"));
    }
}
