package it.polimi.dima.movies.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import it.polimi.dima.movies.R;
import it.polimi.dima.movies.model.Movie;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(@NonNull Context context, List<Movie> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Movie movie = getItem(position);

        MovieViewHolder holder;

        if (convertView == null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.movie_item, null);
            holder = new MovieViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
            holder = (MovieViewHolder) convertView.getTag();

        holder.titleTV.setText(movie.getTitle());
        holder.directorTV.setText(movie.getDirector());

        return convertView;
    }


    private static class MovieViewHolder {
        TextView titleTV;
        TextView directorTV;
        public MovieViewHolder(View view){
            titleTV = view.findViewById(R.id.movieTitleTV);
            directorTV = view.findViewById(R.id.movieDirectorTV);
        }
    }
}
