package it.polimi.dima.movies.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import it.polimi.dima.movies.R;
import it.polimi.dima.movies.adapter.MovieArrayAdapter;
import it.polimi.dima.movies.model.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupListView();
    }

    private void setupListView(){
        ListView listView = findViewById(R.id.movieList);
        MovieArrayAdapter adapter = new MovieArrayAdapter(this, Movie.getMovies());
        listView.setAdapter(adapter);
    }
}