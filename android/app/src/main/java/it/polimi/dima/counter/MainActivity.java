package it.polimi.dima.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CounterLogic counter;
    private TextView counterTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alt);

        counter = new CounterLogic();

        counterTextView = findViewById(R.id.counterTV);
        counterTextView.setText(String.valueOf(counter.getCurrent()));

    }


    public void onAddClick(View view){
        counter.addOne();
        counterTextView.setText(String.valueOf(counter.getCurrent()));
    }

    public void onZeroClick(View view){
        counter.zero();
        counterTextView.setText(String.valueOf(counter.getCurrent()));
    }

    public void onTotalClick(View view){
        Intent intent = new Intent(this, SimpleDataActivity.class);
        intent.putExtra(SimpleDataActivity.DATA_KEY, String.valueOf(counter.getTotal()));
        startActivity(intent);
    }
}