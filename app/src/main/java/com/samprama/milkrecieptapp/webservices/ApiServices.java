package com.samprama.milkrecieptapp.webservices;

import com.samprama.milkrecieptapp.model.CheckMobileModel;
import com.samprama.milkrecieptapp.model.CreateMilkRectModel;
import com.samprama.milkrecieptapp.model.GetAllAgencyModel;
import com.samprama.milkrecieptapp.model.GetAllTesterModel;
import com.samprama.milkrecieptapp.model.GetAllVehicleModel;
import com.samprama.milkrecieptapp.model.RecResponseModel;
import com.samprama.milkrecieptapp.model.RectNoModel;
import com.samprama.milkrecieptapp.model.TesterNameModel;
import com.samprama.milkrecieptapp.model.VehicleNoData;
import com.samprama.milkrecieptapp.model.VehicleNoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiServices {
    @Headers("Content-Type: application/json")
    @GET("api/Agency/GetAllAgency")
    Call<ArrayList<GetAllAgencyModel>> getAllAgencyDataFromServer();

    @Headers("Content-Type: application/json")
    @GET("api/Tester/GetAllTester")
    Call<ArrayList<GetAllTesterModel>> getAllTesterDataFromServer();

    @Headers("Content-Type: application/json")
    @GET("api/Vehicle/GetAllVehicle")
    Call<ArrayList<GetAllVehicleModel>> getAllVehicleDataFromServer();

    @Headers("Content-Type: application/json")
    @GET("api/MilkRect/GetRectNo")
    Call<RectNoModel> getRectNoDataFromServer();

    //@Headers("Content-Type: application/json")
    @POST("api/MilkRect/CreateMilkRect")
    Call<RecResponseModel> sendReceiptDataFromServer(@Body CreateMilkRectModel milkRectModel);

    @Headers("Content-Type: application/json")
    @GET("api/Tester/CheckMobileExist")
    Call<CheckMobileModel> fetchMobileNoDataFromServer(@Query("m_mobileno") String mobileNo);

    @Headers("Content-Type: application/json")
    @GET("api/Tester/GetLastTesterbyMobile")
    Call<TesterNameModel>fetchLastTesterDataFromServer(@Query("m_mobileno") String mobileNo);


    @Headers("Content-Type: application/json")
    @GET("api/MilkRect/GetLastVehicleByMobile")
    Call<VehicleNoModel>fetchVehicleNoDataFromServer (@Query("m_mobile") String mobileNo);
}
