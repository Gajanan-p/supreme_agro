package com.bright.supreme.webservices;

import com.bright.supreme.model.CheckMobileModel;
import com.bright.supreme.model.CreateMilkRectModel;
import com.bright.supreme.model.DipReadingModel;
import com.bright.supreme.model.GetAllAgencyModel;
import com.bright.supreme.model.GetAllTesterModel;
import com.bright.supreme.model.GetAllVehicleModel;
import com.bright.supreme.model.RecResponseModel;
import com.bright.supreme.model.RectNoModel;
import com.bright.supreme.model.ReportReqModel;
import com.bright.supreme.model.ReportViewModel;
import com.bright.supreme.model.TesterNameModel;
import com.bright.supreme.model.VehicleNoModel;

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
    Call<ArrayList<GetAllAgencyModel>> getAllAgencyDataFromServer(@Query("_Agencycode") String agencyCode);

    @Headers("Content-Type: application/json")
    @GET("api/Tester/GetAllTester")
    Call<ArrayList<GetAllTesterModel>> getAllTesterDataFromServer();

    @Headers("Content-Type: application/json")
    @GET("api/Vehicle/GetAllVehicle")
    Call<ArrayList<GetAllVehicleModel>> getAllVehicleDataFromServer();

    @Headers("Content-Type: application/json")
    @GET("api/MilkRect/GetRectNo")
    Call<RectNoModel> getRectNoDataFromServer();

    @Headers("Content-Type: application/json")
    @POST("api/MilkRect/CreateMilkRect")
    Call<RecResponseModel> sendReceiptDataFromServer(@Body CreateMilkRectModel milkRectModel);

    @Headers("Content-Type: application/json")
    @GET("api/Tester/CheckMobileExist")
    Call<CheckMobileModel> fetchMobileNoDataFromServer(@Query("m_MobileNo") String mobileNo);

    @Headers("Content-Type: application/json")
    @GET("api/Tester/GetLastTesterbyMobile")
    Call<TesterNameModel>fetchLastTesterDataFromServer(@Query("m_MobileNo") String mobileNo);


    @Headers("Content-Type: application/json")
    @GET("api/MilkRect/GetLastVehicleByMobile")
    Call<VehicleNoModel>fetchVehicleNoDataFromServer (@Query("m_Mobile") String mobileNo);

    @Headers("Content-Type: application/json")
    @GET("api/MilkRect/GetDipWeightValue")
    Call<DipReadingModel>fetchDipReadingDataFromServer (@Query("m_Agency") String agency, @Query("m_DipValue") int dipValue);

    @Headers("Content-Type: application/json")
    @GET("api/Agency/GetAllAgencyManual")
    Call<ArrayList<GetAllAgencyModel>> getAllAgencyForManualDataFromServer();

    @Headers("Content-Type: application/json")
    @POST("api/MilkRect/GetAllMilkReceipt")
    Call<ReportViewModel> fetchReportFromServer(@Query("m_FDate")String fromDate,@Query("m_TDate")String toDate,@Query("m_Mobile")String mobileNo );
}
