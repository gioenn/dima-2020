package it.polimi.dima.movies.service;

import it.polimi.dima.movies.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRestInterface {

    @GET("/?")
    Call<Movie> getMovie(@Query("i") String imdbId, @Query("apiKey") String apiKey);
}
