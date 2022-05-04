package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        navController= Navigation.findNavController(this,R.id.frame_layout);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_drawer_layout);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.navigation_video:
            {
                Toast.makeText(this, "Video clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_ebook:
            {
                Toast.makeText(this, "Ebook clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_theme:
            {
                Toast.makeText(this, "Theme clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_website:
            {
                Toast.makeText(this, "Web clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_share:
            {
                Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_rate_us:
            {
                Toast.makeText(this, "Review clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navigation_developer:
            {
                Toast.makeText(this, "developer clicked", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
}