package com.example.hikaioffline.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hikaioffline.MainActivity;
import com.example.hikaioffline.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  {
    Boolean success;
    TextInputLayout lPass,lUserName;
    EditText email, pwd;
    Button login,registration,forgotPassword;
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.uname);
        pwd = findViewById(R.id.pwd);
        remember = findViewById(R.id.checkbox);

        login = findViewById(R.id.login_button);
        lPass=findViewById(R.id.lPass);
        lUserName=findViewById(R.id.lUsername);


        if (SharedPrefManager.getInstance(this).isLoggedIn()) {

            //Storing the url in shared prefrernces
//            SharedPrefManager.getInstance(this).setUrl(URLs.URL_LOGIN);
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validaName() | !validaPassword()){
                    return;
                }
                else{

                    userLogin();

                }
            }
        });
    }


    private boolean validaName(){


        String strname=lUserName.getEditText().getText().toString().trim();
        if(strname.isEmpty()){
            lUserName.setError("Field cannot be empty");
            return false;
        }
        else {
            lUserName.setError(null);
            return true;
        }
    }
    //password

    private boolean validaPassword(){
        String check="^[0-9]{8,}$";
        String strpass=lPass.getEditText().getText().toString().trim();
        if(strpass.isEmpty()){
            lPass.setError("Field cannot be empty");
            return false;
        }else if(!strpass.matches(check)){
            lPass.setError("Please enter a validate Password");
            return false;
        }else {
            lPass.setError(null);
            return true;
        }
    }

    private void userLogin() {

        //first getting the values
        final String username = email.getText().toString();
        final String password = pwd.getText().toString();





        ///if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            Log.d("ServerResponse",obj.toString());

                            //if no error in response
                            if (obj.getInt("success")==1)
                            {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting data array by creating an object
                                JSONArray dataArray = obj.getJSONArray("data");

                                Toast.makeText(getApplicationContext(), dataArray.getJSONObject(0).getString("participantID"), Toast.LENGTH_LONG).show();

                                //getting the user from the response


                                //creating a new user object(in bracket pass the column name)
                                User user = new User(
                                        dataArray.getJSONObject(0).getInt("id"),
                                        dataArray.getJSONObject(0).getString("firstName")+" "+dataArray.getJSONObject(0).getString("lastName"),
                                        dataArray.getJSONObject(0).getString("class"),
                                        dataArray.getJSONObject(0).getString("stream"),
                                        dataArray.getJSONObject(0).getString("phone"),
                                        dataArray.getJSONObject(0).getString("email")
                                        //userJson.getInt("id"),
                                        //userJson.getString("firstName"),
                                        //Log.d("Lo","Sucess")
                                        //userJson.getString("password"),
                                        //userJson.getString("gender")

                                );
                                Log.d("ouruser",user.getName().toString());

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //Storing the url in shared prefrernces
//                                SharedPrefManager.getInstance(getApplicationContext()).setUrl(URLs.URL_LOGIN);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        Toast.makeText( LoginActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                String creds = String.format("%s:%s","USERNAME","PASSWORD");
//                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
//                params.put("Authorization", auth);
//                return params;
//            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


   }


