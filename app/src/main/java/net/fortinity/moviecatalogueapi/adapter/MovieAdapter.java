package net.fortinity.moviecatalogueapi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.fortinity.moviecatalogueapi.R;
import net.fortinity.moviecatalogueapi.activity.MovieActivity;
import net.fortinity.moviecatalogueapi.model.Movies;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movies> mMovies = new ArrayList<>();

    public void setMovies(ArrayList<Movies> items) {
        mMovies.clear();
        mMovies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movies, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvTitle, tvDescription;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int i = getAdapterPosition();
            Movies movie = mMovies.get(i);

            movie.setTitle(movie.getTitle());
            movie.setDescription(movie.getDescription());

            Intent moveObject = new Intent(itemView.getContext(), MovieActivity.class);
            moveObject.putExtra(MovieActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveObject);
        }

        void bind(Movies movies) {
            String url_photo = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();

            tvTitle.setText(movies.getTitle());
            tvDescription.setText(movies.getDescription());

            Glide.with(itemView.getContext())
                    .load(url_photo)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);
        }
    }
}
