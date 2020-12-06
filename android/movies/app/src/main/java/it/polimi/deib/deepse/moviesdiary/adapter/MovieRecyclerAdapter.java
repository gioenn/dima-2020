package it.polimi.deib.deepse.moviesdiary.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import it.polimi.deib.deepse.moviesdiary.R;
import it.polimi.deib.deepse.moviesdiary.model.Movie;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder> implements Filterable {

    private List<Movie> movies;
    private List<Movie> allMovies;
    private Filter filter;
    private Context context;

    public MovieRecyclerAdapter(List<Movie> movies) {
        this.allMovies = new ArrayList<>(movies);
        this.movies = new ArrayList<>(movies);
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null){
            context = parent.getContext();
        }

        View card = LayoutInflater.from(context).inflate(R.layout.movie_card,
                parent, false);

        return new MovieHolder(card);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.vTitle.setText(movie.getTitle());
        holder.vDirector.setText(movie.getDirectorName());
        holder.vUserRating.setText(movie.getUserRating()+"");
        Glide.with(context).load(movie.getPosterUrl())
                .centerCrop().crossFade().into(holder.vPoster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public Filter getFilter(){
        if (filter == null)
            filter = new MovieFilter();
        return filter;
    }


    class MovieFilter extends Filter {

        List<Movie> filteredResult = new ArrayList<>();

        @Override
        protected FilterResults performFiltering(CharSequence query) {

            filteredResult.clear();

            FilterResults results = new FilterResults();

            if (query.length() == 0 ) {
                filteredResult.addAll(allMovies);
            }
            else {

                query = query.toString().toLowerCase();

                List<Movie> titleMatches = new ArrayList<>();
                List<Movie> directorMatches = new ArrayList<>();

                for (Movie movie : allMovies) {

                    if (movie.getTitle().toLowerCase().contains(query)){
                        titleMatches.add(movie);
                    }

                    if (movie.getDirectorName().toLowerCase().contains(query)){
                        directorMatches.add(movie);
                    }
                }

                filteredResult.addAll(titleMatches);
                filteredResult.addAll(directorMatches);

            }

            results.count = filteredResult.size();
            results.values = filteredResult;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            movies.clear();
            movies.addAll((List<Movie>)results.values);
            notifyDataSetChanged();
        }
    }


    static class MovieHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;
        protected TextView vDirector;
        protected TextView vUserRating;
        protected ImageView vPoster;

        public MovieHolder(View card) {
            super(card);
            vTitle = card.findViewById(R.id.movieTitle);
            vDirector = card.findViewById(R.id.directorName);
            vUserRating = card.findViewById(R.id.movieUserRating);
            vPoster = card.findViewById(R.id.moviePosterImage);
        }
    }

}
