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
import net.fortinity.moviecatalogueapi.activity.TVShowActivity;
import net.fortinity.moviecatalogueapi.model.TVShow;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> mTVShows = new ArrayList<>();

    public void setTVShows(ArrayList<TVShow> items) {
        mTVShows.clear();
        mTVShows.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tvshow, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        holder.bind(mTVShows.get(position));
    }

    @Override
    public int getItemCount() {
        return mTVShows.size();
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvTitle, tvDescription;

        public TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int i = getAdapterPosition();
            TVShow tvShow = mTVShows.get(i);

            tvShow.setTitle(tvShow.getTitle());
            tvShow.setDescription(tvShow.getDescription());

            Intent moveObject = new Intent(itemView.getContext(), TVShowActivity.class);
            moveObject.putExtra(TVShowActivity.EXTRA_TVSHOW, tvShow);
            itemView.getContext().startActivity(moveObject);
        }

        void bind(TVShow tvShows) {
            String url_photo = "https://image.tmdb.org/t/p/w185" + tvShows.getPhoto();

            tvTitle.setText(tvShows.getTitle());
            tvDescription.setText(tvShows.getDescription());

            Glide.with(itemView.getContext())
                    .load(url_photo)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);
        }
    }
}
