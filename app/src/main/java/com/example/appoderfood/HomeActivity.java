package com.example.appoderfood;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.fragment.CaiDatFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.NhanVienFragment;
import com.example.fragment.ThongKeFragment;
import com.example.fragment.ThucDonFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControls();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Home);
        }
    }

    private void addControls() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar
        ,R.string.open_navigation,R.string.close_navigation);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_Home :
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.goup,R.anim.godown).replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_CaiDat:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.goup,R.anim.godown).replace(R.id.fragment_container,new CaiDatFragment()).commit();
                break;
            case R.id.nav_NhanVien :
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.goup,R.anim.godown).replace(R.id.fragment_container,new NhanVienFragment()).commit();
                break;
            case R.id.nav_ThongKe:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.goup,R.anim.godown).replace(R.id.fragment_container,new ThongKeFragment()).commit();
                break;
            case R.id.nav_thucdon:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.goup,R.anim.godown).replace(R.id.fragment_container,new ThucDonFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
