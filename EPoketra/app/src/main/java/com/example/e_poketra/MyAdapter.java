package com.example.e_poketra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList date,desc,nom,type;

    public MyAdapter(Context context, ArrayList date, ArrayList desc, ArrayList nom, ArrayList type) {
        this.context = context;
        this.date = date;
        this.desc = desc;
        this.nom = nom;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dateText.setText(String.valueOf(date.get(position)));
        holder.descText.setText(String.valueOf(desc.get(position)));
        holder.nomText.setText(String.valueOf(nom.get(position)));
        holder.typeText.setText(String.valueOf(type.get(position)));
        String img= String.valueOf(type.get(position));
        switch (img){
            case "Expence":
                holder.imageView.setImageResource(R.drawable.arrow_right);
                break;
            case "Income":
                holder.imageView.setImageResource(R.drawable.arrow_left);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + img);
        }
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateText,descText,nomText,typeText;
        public ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText=itemView.findViewById(R.id.dateText);
            descText=itemView.findViewById(R.id.descText);
            nomText=itemView.findViewById(R.id.nominalText);
            typeText=itemView.findViewById(R.id.typeText);
            imageView=itemView.findViewById(R.id.arrow);
        }
    }
}
