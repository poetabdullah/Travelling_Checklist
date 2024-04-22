package com.example.pack_your_bag.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pack_your_bag.CheckList;
import com.example.pack_your_bag.Constants.MyConstants;
import com.example.pack_your_bag.R;

import java.security.AccessController;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder>{
    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    Activity activity;

    public Adapter(Context context, List<String> titles, List<Integer> images, Activity activity) {
        this.titles = titles;
        this.images = images;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(titles.get(position));
        holder.img.setImageResource(images.get(position));
        holder.linearLayout.setAlpha(0.8F);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, "Clicked on card.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.itemView.getContext(), CheckList.class);
                intent.putExtra(MyConstants.HEADER_SMALL, titles.get(position));
                if (MyConstants.MY_SELECTIONS.equals(titles.get(position))) {
                    intent.putExtra(MyConstants.SHOW_SMALL, MyConstants.FALSE_STRING);
                    //intent.putExtra("show", "false");
                } else {
                    intent.putExtra(MyConstants.SHOW_SMALL, MyConstants.TRUE_STRING);
                }
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        LinearLayout linearLayout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
