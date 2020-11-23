package com.example.hikaioffline.login;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "participant";
    private static final String URL="oururl";

    private static final String ID = "keyid";
    private static final String USERNAME = "keyUsername";
    private static final String PASSWORD="password";
    private static final String USER_CLASS="class";
    private static final String USER_STREAM="stream";
    private static final String USER_EMAIL="email";


    private static SharedPrefManager mInstance;// Shared Preference Instance
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ID, user.getId());
        editor.putString(USERNAME, user.getName());
        editor.putString(PASSWORD,user.getPassword());
        editor.putString(USER_CLASS,user.getStandard());
        editor.putString(USER_EMAIL,user.getEmail());
        editor.putString(USER_STREAM,user.getStream());
        editor.apply();
    }

    //url in shared prefrence

    public void setUrl(String url){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String strUrl=url.toString();
        editor.putString(URL,strUrl);
        editor.apply();
    }

    //Getting the url

    public String  getUrl(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(URL,"https://rkmhikai.online/");
    }

    //this method will check whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(ID, -1),
                sharedPreferences.getString(USERNAME, null),

                sharedPreferences.getString(USER_CLASS,null),
                sharedPreferences.getString(USER_STREAM ,null),

                sharedPreferences.getString(PASSWORD,null),
                sharedPreferences.getString(USER_EMAIL,null)
        );
    }
}
