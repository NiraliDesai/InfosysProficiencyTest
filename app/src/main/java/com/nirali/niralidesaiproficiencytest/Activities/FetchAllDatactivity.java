package com.nirali.niralidesaiproficiencytest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nirali.niralidesaiproficiencytest.Adapters.CanadaDetailsAdapter;
import com.nirali.niralidesaiproficiencytest.Interfaces.AddAllAPIs;
import com.nirali.niralidesaiproficiencytest.Models.CanadaDetailsModel;
import com.nirali.niralidesaiproficiencytest.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchAllDatactivity extends AppCompatActivity {

    private ListView lvShowContent;
    private CanadaDetailsAdapter canadaDetailsAdapter;
    private ArrayList<CanadaDetailsModel.RowsBean> canadaDetailsModelArrayList;
    private TextView tvHeader, tvRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_all_datactivity);

        // setIDs() for Initialize all the basic widgets
        setIDs();

        // call this method to fetch all data from url
        fetchDataFromUrl();
        setOnClickListener();
    }
    private void setIDs() {

        tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        lvShowContent = (ListView) findViewById(R.id.lvShowContent);
    }

    public void fetchDataFromUrl() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AddAllAPIs.BASE_URL) // Added Basic Url here
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AddAllAPIs request = retrofit.create(AddAllAPIs.class);
        Call<CanadaDetailsModel> call = request.grtAllDetails();
        call.enqueue(new Callback<CanadaDetailsModel>() {
            @Override
            public void onResponse(Call<CanadaDetailsModel> call, Response<CanadaDetailsModel> response) {

                Log.e("Response from URL: " , new Gson().toJson(response));

                canadaDetailsModelArrayList = response.body().getRows();

                // set Header Text (So it will be Dynamic)
                tvHeader.setText(response.body().getTitle());

                // set Listview Items (so it will be Dynamic)
                canadaDetailsAdapter = new CanadaDetailsAdapter(FetchAllDatactivity.this, canadaDetailsModelArrayList);
                lvShowContent.setAdapter(canadaDetailsAdapter);
            }

            @Override
            public void onFailure(Call<CanadaDetailsModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void setOnClickListener() {

        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchAllDatactivity.this.recreate();
            }
        });


    }
}
