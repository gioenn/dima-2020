package it.polimi.deib.deepse.moviesdiary.service;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import it.polimi.deib.deepse.moviesdiary.R;
import it.polimi.deib.deepse.moviesdiary.data.MovieCursor;
import it.polimi.deib.deepse.moviesdiary.data.MovieSQLiteRepository;
import it.polimi.deib.deepse.moviesdiary.model.Movie;
import it.polimi.deib.deepse.moviesdiary.model.MovieSearch;
import it.polimi.deib.deepse.moviesdiary.rest.MovieRESTInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {

    private static MovieService instance;
    private static final String apiKey = "b8c79639";

    private MovieSQLiteRepository repository;
    private MovieRESTInterface restInterface;

    private MovieService(Context context){

        repository = new MovieSQLiteRepository(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restInterface = retrofit.create(MovieRESTInterface.class);

        fillWithDefault(context);
    }

    public static synchronized MovieService getInstance(Context context){
        if (instance == null){
            instance = new MovieService(context);
        }
        return instance;
    }

    public void getAllMovies(final Callback callback){

        MovieCursor cursor = repository.findAll();
        final int count = cursor.getCount();

        final List<Movie> movies = new ArrayList<>();
        final AtomicBoolean alreadyFailed = new AtomicBoolean(false);

        while (cursor.moveToNext()){

            final String imdbId = cursor.getImdbId();
            final String userReview = cursor.getUserReview();
            final float userRating = cursor.getUserRating();

            restInterface.getMovie(imdbId, MovieService.apiKey)
                    .enqueue(new retrofit2.Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call,
                                               Response<Movie> response) {
                            Movie movie = response.body();
                            movie.setUserRating(userRating);
                            movie.setUserReview(userReview);

                            movies.add(movie);

                            if (movies.size() == count){
                                callback.onLoad(movies);
                            }
                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {
                            if (!alreadyFailed.getAndSet(true)){
                                callback.onFailure();
                            }
                        }
                    });

        }
    }

    public void searchMovies(String query, final Callback callback){

        final List<String> ids = new ArrayList<>();

        MovieCursor cursor = repository.findAll();

        while (cursor.moveToNext()){
            ids.add(cursor.getImdbId());
        }

        cursor.close();

        restInterface.searchMovies(MovieService.apiKey, query).enqueue(new retrofit2.Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call,
                                   Response<MovieSearch> response) {

                if (response.body() == null || response.body().getResults() == null){

                    callback.onLoad(new ArrayList<Movie>());
                    return;
                }

                List<Movie> searchResult = response.body().getResults();
                List<Movie> result = new ArrayList<>();

                for (Movie m : searchResult){
                    if (!ids.contains(m.getImdbId())){
                        result.add(m);
                    }
                }

                callback.onLoad(result);



            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {
                callback.onFailure();
            }
        });

    }

    public void addMovie(Movie movie){
        repository.add(movie);
    }

    private void fillWithDefault(Context context){

        if (repository.findAll().getCount() != 0)
            return;

        TypedArray movieIds = context.getResources()
                    .obtainTypedArray(R.array.movies_imdb_ids);
        TypedArray userRatings = context.getResources()
                .obtainTypedArray(R.array.movies_user_ratings);
        TypedArray userReview = context.getResources()
                .obtainTypedArray(R.array.movies_user_reviews);

        for (int i = 0; i < movieIds.length(); i++){
            Movie movie = new Movie(movieIds.getString(i),
                    userRatings.getFloat(i, 0.0f),
                    userReview.getString(i));
            repository.add(movie);
        }

    }

    public static interface Callback {
        public void onLoad(List<Movie> movies);
        public void onFailure();
    }

}
