package com.nirali.niralidesaiproficiencytest.Interfaces;

import com.nirali.niralidesaiproficiencytest.Models.CanadaDetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AddAllAPIs {

    String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    @GET("facts.json")
    Call<CanadaDetailsModel> grtAllDetails();


}
