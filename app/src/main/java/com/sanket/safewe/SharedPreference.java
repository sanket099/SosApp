package com.sanket.safewe;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static final String SharedPreference_name = "my_SharedPreference";

    private static SharedPreference instance;
    private Context context;

    SharedPreference(Context context) {
        this.context = context;
    }

    public static synchronized SharedPreference getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreference(context);

        }
        return instance;
    }

    public void setLoggedIn(boolean flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", flag);
        editor.apply();

    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean("login", false);
    }

    public void setContacts(boolean flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("contacts", flag);
        editor.apply();

    }
    public boolean isContactSaved(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean("contacts", false);
    }

    public void saveUid(String uid){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uid", uid);
        editor.apply();

    }
    public String getUid(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);

        return sharedPreferences.getString("uid", "0");

    }
    public void setFirst(boolean flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first", flag);
        editor.apply();

    }
    public boolean isFirst(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean("first", true);
    }

    public void save_lat(String lat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("latti", lat);

        editor.apply();
    }

    public void save_longi(String longi) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("longi", longi);

        editor.apply();
    }

    public String get_saved_lat() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        String loc_lat = sharedPreferences.getString("latti", "0");

        return loc_lat;

    }

    public String get_saved_longi() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        String loc_longi = sharedPreferences.getString("longi", "0");

        return loc_longi;
    }
    public void save_lattitude_event(String lat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("latti_event", lat);

        editor.apply();
    }
    public void save_longitude_event(String lon) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("longi_event", lon);

        editor.apply();
    }
    public String get_saved_lattitude_event() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        String loc_lat = sharedPreferences.getString("latti_event", "0");

        return loc_lat;

    }

    public String get_saved_longi_event() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        String loc_longi = sharedPreferences.getString("longi_event", "0");

        return loc_longi;
    }







}
