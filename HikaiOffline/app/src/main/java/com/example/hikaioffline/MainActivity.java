package com.example.hikaioffline;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hikaioffline.login.SharedPrefManager;
import com.example.hikaioffline.login.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    TextView tvUserName, tvStandard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);




        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_classroom, R.id.nav_contact, R.id.nav_about,R.id.web)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView = findViewById(R.id.nav_view);



        View hView = navigationView.getHeaderView(0);
        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();
        TextView nav_user =  hView.findViewById(R.id.pname);
        TextView nav_email =  hView.findViewById(R.id.txtemail);
        TextView nav_standard = hView.findViewById(R.id.txtstandard);
        TextView nav_stream = hView.findViewById(R.id.txtstream);
        TextView nav_password = hView.findViewById(R.id.txtphone);

        nav_user.setText(String.valueOf(user.getName()));
        nav_email.setText(String.valueOf(user.getEmail()));
        nav_standard.setText(String.valueOf(user.getStandard()));
        nav_stream.setText(String.valueOf(user.getStream()));
        nav_password.setText(String.valueOf(user.getPassword()));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}