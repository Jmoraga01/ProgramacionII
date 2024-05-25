package com.ugb.controlesbasicos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
// PersonAdapter.java
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context context;
    private List<Person> personList;

    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.personName.setText(person.getName());
        holder.personDescription.setText(person.getDescription());
        holder.personImage.setImageResource(person.getImageResource());

        if (person.isLiked()) {
            holder.likeButton.setImageResource(R.drawable.ic_like);
            holder.likeButton.setColorFilter(ContextCompat.getColor(context, R.color.red)); // Cambiar color a rojo
        } else {
            holder.likeButton.setImageResource(R.drawable.ic_like_inactive);
            holder.likeButton.setColorFilter(ContextCompat.getColor(context, R.color.gray)); // Cambiar color a gris
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MoreInfoActivity.class);
            intent.putExtra("person", person);
            context.startActivity(intent);
        });

        holder.likeButton.setOnClickListener(v -> {
            if (person.isLiked()) {
                person.setLiked(false);
                holder.likeButton.setImageResource(R.drawable.ic_like_inactive);
                holder.likeButton.setColorFilter(ContextCompat.getColor(context, R.color.gray)); // Cambiar color a gris
            } else {
                person.setLiked(true);
                holder.likeButton.setImageResource(R.drawable.ic_like);
                holder.likeButton.setColorFilter(ContextCompat.getColor(context, R.color.red)); // Cambiar color a rojo
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        ImageView personImage;
        TextView personName;
        TextView personDescription;
        ImageView likeButton;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            personImage = itemView.findViewById(R.id.person_image);
            personName = itemView.findViewById(R.id.person_name);
            personDescription = itemView.findViewById(R.id.person_description);
            likeButton = itemView.findViewById(R.id.like_button);
        }
    }
}

