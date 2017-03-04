package com.siddharthkumar.restaurantdeals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Siddharth Kumar on 3/4/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RestaurantHolder> {
    List<MainActivity.Restaurant> list;
    View.OnClickListener listener;
    Context context;
    public RVAdapter(List<MainActivity.Restaurant> list1 , Context context){
        list=list1;
        this.listener=listener;
        this.context = context;
    }

    @Override
    public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout, parent, false);
        RestaurantHolder vh=new RestaurantHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RestaurantHolder holder, final int position) {
        holder.distance.setText(list.get(position).distance+"");
        holder.deal.setText(list.get(position).deal);
        holder.name.setText(list.get(position).name);
        holder.type.setText(list.get(position).type);
        holder.hours.setText(list.get(position).hours);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,MapActivity.class);
                i.putExtra("caller",list.get(position).name);
                context.startActivity(i);
            }
        });

        //convert the string url to image
        try{

            holder.imageView.setImageBitmap(new ImageTask().execute(list.get(position).ImageURL).get());
        }catch(Exception e){
            Log.e("ADAPTER",e.toString()+" ");}



    }

    public class ImageTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            for(String str:params)
                try{
                    URL url = new URL(str);
                    return (BitmapFactory.decodeStream(url.openConnection().getInputStream()));
                }catch(Exception e){
                    Log.e("ADAPTER",e.toString()+" ");}


            return null;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RestaurantHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        TextView distance;
        TextView deal;
        TextView type;
        TextView hours;
        ImageView imageView;
        public RestaurantHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.card);
            name=(TextView)itemView.findViewById(R.id.name);
            distance=(TextView)itemView.findViewById(R.id.dist);
            deal=(TextView)itemView.findViewById(R.id.deal);
            type=(TextView)itemView.findViewById(R.id.type);
            hours=(TextView)itemView.findViewById(R.id.hours);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}
