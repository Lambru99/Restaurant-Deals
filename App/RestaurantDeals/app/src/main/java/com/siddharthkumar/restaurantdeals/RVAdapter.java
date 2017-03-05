package com.siddharthkumar.restaurantdeals;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Siddharth Kumar on 3/4/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RestaurantHolder> {
    List<MainActivity.Restaurant> list;
    View.OnClickListener listener;
    Context context;
    double currentlat;
    double currentlong;
    boolean gps_enabled=false;
    boolean network_enabled=false;
    LocationManager locationManager;


    public RVAdapter(List<MainActivity.Restaurant> list1, Context context) {
        list = list1;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout, parent, false);
        RestaurantHolder vh = new RestaurantHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RestaurantHolder holder, final int position) {
        double d = list.get(position).distance/1609.344;
        DecimalFormat f = new DecimalFormat("##.00");
        holder.distance.setText(f.format(d) + " miles");
        holder.deal.setText(list.get(position).deal);
        holder.name.setText(list.get(position).name);
        holder.type.setText(list.get(position).type);
        holder.hours.setText(list.get(position).hours);
        holder.url.setText(list.get(position).url);
        holder.url.setMovementMethod(LinkMovementMethod.getInstance());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latlong=list.get(position).latlong;

                String[] str = latlong.split(",");
                double lat = Double.parseDouble(str[0]);
                double lng = Double.parseDouble(str[1]);

                SingleShotLocationProvider.requestSingleUpdate(context,
                        new SingleShotLocationProvider.LocationCallback() {
                            @Override
                            public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                                currentlat=location.latitude;
                                currentlong=location.longitude;

                            }


                        });

                Uri uri = Uri.parse("http://maps.google.com/maps?saddr="+currentlat+","+currentlong+"&daddr="+lat+","+lng);


                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                if(currentlat!=0&&currentlong!=0)
                context.startActivity(intent);
            }
        });



    }

    @Override
    public void onViewRecycled(RestaurantHolder holder) {
        if(holder!=null) {

            holder.url.setText(null);
            holder.distance.setText(null);

            holder.deal.setText(null);
            holder.hours.setText(null);
            holder.name.setText(null);
            holder.type.setText(null);
        }


        super.onViewRecycled(holder);
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

        TextView url;
        public RestaurantHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.card);
            name=(TextView)itemView.findViewById(R.id.name);
            distance=(TextView)itemView.findViewById(R.id.dist);
            deal=(TextView)itemView.findViewById(R.id.deal);
            type=(TextView)itemView.findViewById(R.id.type);
            hours=(TextView)itemView.findViewById(R.id.hours);

            url = (TextView)itemView.findViewById(R.id.url);
        }
    }
}
