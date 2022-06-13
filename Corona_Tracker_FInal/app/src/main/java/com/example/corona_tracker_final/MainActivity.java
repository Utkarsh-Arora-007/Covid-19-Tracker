package com.example.corona_tracker_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.compose.ui.graphics.Color;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corona_tracker_final.api.ApiUtilities;
import com.example.corona_tracker_final.api.CountryData;
import com.google.android.material.navigation.NavigationView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView TotalConfirm,TotalActive,TotalRecovered,TotalDeaths,TotalTests;
    private TextView TodayConfirm,TodayRecovered,TodayDeaths,updatedDate;
    private PieChart pieChart;
    private List<CountryData> list;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    String country ="India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView) findViewById(R.id.nav_view);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/

//        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch(menuItem.getItemId())
//                {
//                    case R.id.menu_home:
//                        Intent i = new Intent(MainActivity.this,CountryActivity.class);
//                        startActivity(i);
//                        Toast.makeText(getApplicationContext(),"Home Panel Opens",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                         break;
//                    case R.id.menu_bot:
//                        Intent l = new Intent(MainActivity.this,CountryActivity.class);
//                        startActivity(l);
//                        Toast.makeText(getApplicationContext(),"Chatbot Panel Opens",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case R.id.menu_vaccination:
//                        Intent k = new Intent(MainActivity.this,CountryActivity.class);
//                        startActivity(k);
//                        Toast.makeText(getApplicationContext(),"Vaccination Center Finder Panel Opens",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case R.id.menu_about_team:
//                        Intent j = new Intent(MainActivity.this,CountryActivity.class);
//                        startActivity(j);
//                        Toast.makeText(getApplicationContext(),"Team Introduction Panel Opens",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//
//                }
//
//                return true;
//            }
//        });

        //getSupportActionBar().hide();

        list= new ArrayList<>();
        if(getIntent().getStringExtra("country")!=null)
            country=getIntent().getStringExtra("country");


        init();

        TextView cname=findViewById(R.id.cname);
        cname.setText(country);
        cname.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,CountryActivity.class)));
        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {

                        list.addAll(response.body());
                        for(int i=0;i<list.size();i++)
                        {
                           if(list.get(i).getCountry().equals(country)){
                              int confirm=Integer.parseInt(list.get(i).getCases());
                               int active=Integer.parseInt(list.get(i).getActive());
                               int recovered=Integer.parseInt(list.get(i).getRecovered());
                               int death=Integer.parseInt(list.get(i).getDeaths());

                               TotalConfirm.setText(NumberFormat.getInstance().format(confirm));
                               TotalActive.setText(NumberFormat.getInstance().format(active));
                               TotalRecovered.setText(NumberFormat.getInstance().format(recovered));
                               TotalDeaths.setText(NumberFormat.getInstance().format(death));
                               Log.e("DATA",NumberFormat.getInstance().format(death).toString());
                               TodayDeaths.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                               TodayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                               TotalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));
                               TodayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));

                               setText(list.get(i).getUpdated());

                               pieChart.addPieSlice(new PieModel("Confirm",confirm,getResources().getColor(R.color.yellow)));
                               pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.blue_pie)));
                               pieChart.addPieSlice(new PieModel("Recovered",recovered,getResources().getColor(R.color.green)));
                               pieChart.addPieSlice(new PieModel("Deaths",death,getResources().getColor(R.color.red_pie)));

                           }
                        }
                        
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void setText(String updated){
        DateFormat format= new SimpleDateFormat("MMM dd, yyyy");
        long milliseconds = Long.parseLong(updated);
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        updatedDate.setText("Updated at "+format.format(calendar.getTime()));


    }

    private void init(){

        TotalConfirm=findViewById(R.id.TotalConfirm);
        TotalActive=findViewById(R.id.TotalActive);
        TotalDeaths=findViewById(R.id.TotalDeaths);
        TotalRecovered=findViewById(R.id.TotalRecovered);
        TodayConfirm=findViewById(R.id.TodayConfirm);
        TodayDeaths=findViewById(R.id.TodayDeaths);
        TodayRecovered=findViewById(R.id.TodayRecovered);
        TotalTests=findViewById(R.id.TodayTests);
        pieChart=findViewById(R.id.pieChart);
        updatedDate=findViewById(R.id.updatedDate);

    }
}