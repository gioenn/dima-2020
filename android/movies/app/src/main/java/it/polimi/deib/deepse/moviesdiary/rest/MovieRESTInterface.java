package it.polimi.deib.deepse.moviesdiary.rest;

import java.util.List;

import it.polimi.deib.deepse.moviesdiary.model.Movie;
import it.polimi.deib.deepse.moviesdiary.model.MovieSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRESTInterface {


    @GET("/?")
    public Call<Movie> getMovie(@Query("i") String imdbId,
                                @Query("apikey") String apiKey);

    @GET("/?")
    public Call<MovieSearch> searchMovies(@Query("s") String query,
                                          @Query("apikey") String apiKey);

}
