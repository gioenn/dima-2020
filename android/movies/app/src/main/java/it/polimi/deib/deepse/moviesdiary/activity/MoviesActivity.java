package it.polimi.deib.deepse.moviesdiary.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.polimi.deib.deepse.moviesdiary.R;
import it.polimi.deib.deepse.moviesdiary.adapter.MovieArrayAdapter;
import it.polimi.deib.deepse.moviesdiary.adapter.MovieRecyclerAdapter;
import it.polimi.deib.deepse.moviesdiary.data.MovieCursor;
import it.polimi.deib.deepse.moviesdiary.data.MovieSQLiteRepository;
import it.polimi.deib.deepse.moviesdiary.model.Movie;
import it.polimi.deib.deepse.moviesdiary.rest.MovieRESTInterface;
import it.polimi.deib.deepse.moviesdiary.service.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {

    private List<Movie> movies;
    private MovieRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton b = findViewById(R.id.addButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MoviesActivity.this,
                        AddMovieActivity.class);
                startActivity(intent);

            }
        });


        retrieveMovies();

    }

    private void retrieveMovies(){

        final ProgressDialog progressDialog =
                ProgressDialog.show(this, "Wait", "Your library is loading...", true, false);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        MovieService.getInstance(this).getAllMovies(new MovieService.Callback() {
            @Override
            public void onLoad(List<Movie> movies) {
                setupMoviesList(movies);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure() {
                progressDialog.dismiss();
                Toast.makeText(MoviesActivity.this,
                        "Something went wrong...",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupMoviesList(List<Movie> movies){
        this.movies = movies;

        RecyclerView rView = findViewById(R.id.listView1);
        adapter = new MovieRecyclerAdapter(movies);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 3);

        rView.setLayoutManager(gridLayoutManager);
        rView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movies, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adapter.getFilter().filter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return onQueryTextSubmit(newText);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
