package com.samprama.milkrecieptapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.samprama.milkrecieptapp.model.LoginData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferenceUtil {

    //region declarations and variables


    private static String TAG="appDataPreferences";
    public static String appPreferences="appPreferences";

    public static String appLoginPreferences="appLoginPreferences";;

    public static String appLoginPreferencesKey="appLoginPreferencesKey";
    public static String appSettingPreferences="appSettingPreferences";
    public static String appAutoJobPreferences="appAutoJobPreferences";
    public  static String appFoundTrayDataPreferences = "appFoundTrayDataPreferences";


    public static SharedPreferences sharedLoginPreferences;
    public static SharedPreferences sharedSettingPreferences;
    public static SharedPreferences sharedAppPreferences;
    private SharedPreferences.Editor preferenceEditor;
    Context context;


    public static SharedPreferences getAppSharedPreferences(Context context){
        if(sharedAppPreferences==null){
            sharedAppPreferences=context.getSharedPreferences(PreferenceUtil.appPreferences
                    , Context.MODE_PRIVATE);
        }
        return sharedAppPreferences;
    }

    public static SharedPreferences getLoginSharedPreferences(Context context){
        if(sharedLoginPreferences==null){
            sharedLoginPreferences=context.getSharedPreferences(PreferenceUtil.appLoginPreferences, Context.MODE_PRIVATE);
        }
        return sharedLoginPreferences;
    }

    public static SharedPreferences getSettingSharedPreferences(Context context){
        if(sharedSettingPreferences==null){
            sharedSettingPreferences=context.getSharedPreferences(PreferenceUtil.appSettingPreferences, Context.MODE_PRIVATE);
        }
        return sharedSettingPreferences;
    }

    public static void setLoginDataPreferences(Context context, LoginData userModel){
        Gson gson = new Gson();
        String data=gson.toJson(userModel);
        Log.i(TAG,"Set user model data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getLoginSharedPreferences(context).edit();
        editor.putString(appLoginPreferencesKey,data);
        editor.commit();
    }

    public static LoginData getLoginDataPreferences(Context context){
        String data=getLoginSharedPreferences(context).getString(appLoginPreferencesKey,"");
        Gson gson = new Gson();
        LoginData userModel= gson.fromJson(data,LoginData.class);
        Log.i(TAG,"get user model data");
        Log.i(TAG,data);
        return userModel;
    }





    public static boolean clearLoginDataPreferences(Context context){
        SharedPreferences.Editor editor=getLoginSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
        editor.commit();
        Log.i(TAG,"Clear login Preferences");
        return true;
    }
    public static boolean clearDataPreferences(Context context){
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
        editor.commit();
        Log.i(TAG,"Clear login Preferences");
        return true;
    }

    public static List<String> getFoundTraDataPreferences(Context context){
        String data=getAppSharedPreferences(context).getString(appFoundTrayDataPreferences,null);
        Gson gson = new Gson();
        List newReducedStockModels=  Arrays.asList( new Gson().fromJson(data, String[].class));
        Log.i(TAG,"get found trays  data "+ data);

        return  newReducedStockModels;
    }

    public static void setFoundTraysDataPreferences(Context context,
                                                         ArrayList<String> foundTraysList){
        Gson gson = new Gson();
        String data=gson.toJson(foundTraysList);
        Log.i(TAG,"Set found trays data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.putString(appFoundTrayDataPreferences,data);
        editor.commit();
    }


}
