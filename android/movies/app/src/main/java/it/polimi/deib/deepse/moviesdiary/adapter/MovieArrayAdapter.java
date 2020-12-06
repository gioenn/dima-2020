package it.polimi.deib.deepse.moviesdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import it.polimi.deib.deepse.moviesdiary.R;
import it.polimi.deib.deepse.moviesdiary.model.Movie;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context,
                             List<Movie> movies){
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent){

        Movie movie = getItem(position);
        MovieViewHolder holder;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = li.inflate(R.layout.movie_item, null);
            holder = new MovieViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (MovieViewHolder) convertView.getTag();
        }

        holder.titleTV.setText(movie.getTitle());
        holder.directorTV.setText(movie.getDirectorName());

        return convertView;
    }

    static class MovieViewHolder {

        TextView titleTV;
        TextView directorTV;

        public MovieViewHolder(View view){
            titleTV = view.findViewById(R.id.movieTitleTextView);
            directorTV = view.findViewById(R.id.movieDirectorTextView);
        }

    }

}
