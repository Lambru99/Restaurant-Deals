package com.siddharthkumar.restaurantdeals;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements RadiusFragment.OnFragmentInteractionListener{
    RecyclerView recyclerView;
    SwipeRefreshLayout srl;
    ArrayList<Restaurant> restaurants= new ArrayList<Restaurant>();
    double radius=10;
    RVAdapter adapter;
    double currentlat;
    double currentlong;
    RadiusFragment radiusFragment;
    int results = 25;

    final String TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.list);
        srl = (SwipeRefreshLayout) findViewById(R.id.refresh);


        adapter=new RVAdapter(restaurants,getApplicationContext());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                srl.setRefreshing(false);
            }
        });
      //  restaurants.add(new Restaurant("Taco Bell", 34, "da", "sda", " sad00", "23", "rs"));

        refresh();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void setNewRadius(){
        if(radiusFragment==null){

            radiusFragment = RadiusFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putDouble("radius",radius);
            bundle.putInt("results",results);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter,R.anim.pop_enter).add(R.id.main,radiusFragment).commit();


        }
        else{
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit,R.anim.pop_exit).remove(radiusFragment).commit();
            radiusFragment=null;
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){

            case R.id.radius:{
                setNewRadius();
                return true;
            }

            default:{
                return super.onOptionsItemSelected(menuItem);
            }

        }


    }



    public void refresh()  {
        try{
            restaurants.clear();
            SingleShotLocationProvider.requestSingleUpdate(getApplicationContext(),
                    new SingleShotLocationProvider.LocationCallback() {
                        @Override
                        public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                            currentlat=location.latitude;
                            currentlong=location.longitude;

                        }


                    });
            if(currentlat!=0&&currentlong!=0)
                new FetchRestaurantsTask().execute("http://hackjskn.tk/rests/"+currentlat+"/"+currentlong+"/"+radius).get();
        }
        catch(Exception e){Log.e("MAIN", e.toString());}

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFragmentInteraction() {
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        if(seekBar!=null)
          radius = seekBar.getProgress();
        TextView textView = (TextView)findViewById(R.id.radiusnumber);
        textView.setText("Radius = "+radius);

    }

    public class FetchRestaurantsTask extends AsyncTask<String,Void, Void>{


        @Override
        protected Void doInBackground(String... params) {
            for(String str: params){
                URL url;
                String response="";
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(str);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        char current = (char) data;
                        data = isw.read();
                        response+=current;

                    }


                    JSONObject everything = new JSONObject(response);

                    JSONArray restaurantes = everything.getJSONArray("restaurants");
                    for(int x=0; x<restaurantes.length();x++){

                        Restaurant restaurant = new Restaurant(restaurantes.getJSONObject(x).getString("name"),
                                restaurantes.getJSONObject(x).getDouble("distance"),restaurantes.getJSONObject(x).getString("short_title"),
                                restaurantes.getJSONObject(x).getString("title"),restaurantes.getJSONObject(x).getString("fine_print"),restaurantes.getJSONObject(x).getString("address"),restaurantes.getJSONObject(x).getString("url"),
                                restaurantes.getJSONObject(x).getString("location")) ;

                        restaurants.add(restaurant);
                    }





                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }




            }
            return null;
        }
    }

    public class Restaurant{

        String name;

        double distance;

        String deal;

        String type;

        String hours;
        String address;

        String url;

        String latlong;

        public Restaurant(String s1,  double d,String s2, String s3, String s4,String d3,String ur,String k){
            name=s1;
            distance=d;

            deal=s2;
            type=s3;
            hours=s4;

            address=d3;
            url=ur;
            latlong=k;

        }
    }
}
