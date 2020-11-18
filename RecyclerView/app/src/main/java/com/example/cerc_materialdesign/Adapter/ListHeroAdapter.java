package com.example.cerc_materialdesign.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cerc_materialdesign.Activity.DetailActivity;
import com.example.cerc_materialdesign.Data.HeroesData;
import com.example.cerc_materialdesign.Model.Hero;
import com.example.cerc_materialdesign.R;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHero;
    private Context mContext;


    public ListHeroAdapter(Context context, ArrayList<Hero> list) {

        this.listHero = list;
        mContext = context;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_hero, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {

        final Hero hero = listHero.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.tvName.setText(hero.getName());
        holder.tvFrom.setText(hero.getFrom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("index", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Hero data);
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPhoto;
        TextView tvName, tvFrom;
        private final Context context;
        ListViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
        }

        @Override
        public void onClick(View v) {

            final Intent intent;
            switch (getAdapterPosition()){
                /*
                case 1:
                    intent =  new Intent(context, SecondActivity.class);
                    break;

 */

                default:
                    intent =  new Intent(context, DetailActivity.class);
                    break;
            }
            context.startActivity(intent);
        }
    }
}
