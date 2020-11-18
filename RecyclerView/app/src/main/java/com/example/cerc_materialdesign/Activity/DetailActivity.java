package com.example.cerc_materialdesign.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cerc_materialdesign.Data.HeroesData;
import com.example.cerc_materialdesign.Model.Hero;
import com.example.cerc_materialdesign.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<Hero> heroeslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        heroeslist.addAll(HeroesData.getListData());

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("index")){

            int index = getIntent().getIntExtra("index", 0);

            setHero(index);
        }
    }


    private void setHero(int position){
        Hero hero = heroeslist.get(position);
        String name = hero.getName();
        String from = hero.getFrom();
        String photo = hero.getPhoto();

        TextView tvname = findViewById(R.id.image_name);
        tvname.setText(name);

        TextView tvdesc = findViewById(R.id.image_description);
        tvdesc.setText(from);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(photo)
                .into(image);
    }
}