package it.polimi.dima.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SimpleDataActivity extends AppCompatActivity {

    public static final String DATA_KEY = "data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_data);

        Intent intent = getIntent();
        String data = intent.getStringExtra(DATA_KEY);
        TextView dataTextView = findViewById(R.id.dataTV);
        dataTextView.setText(data);
    }
}