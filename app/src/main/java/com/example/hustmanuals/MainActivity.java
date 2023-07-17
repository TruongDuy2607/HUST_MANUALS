package com.example.hustmanuals;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hustmanuals.fragment.ChangePasswordFragment;
import com.example.hustmanuals.fragment.FavoriteFragment;
import com.example.hustmanuals.fragment.HomeFragment;
import com.example.hustmanuals.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int MY_REQUEST_CODE = 10;
    private LinearLayout carAboutus, carMap, carManuals, carContact;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVORITE = 1;
    private static final int FRAGMENT_PROFILE = 2;
    private static final int FRAGMENT_CHANGE_PASSWORD = 3;
    private int mCurrentFragment = FRAGMENT_HOME;
    private ImageView imgAvatar;
    private TextView tvName, tvEmail;
    final private  ProfileFragment mProfileFragment = new ProfileFragment();
    final private ActivityResultLauncher<Intent> mActvityResultLancher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                if(intent == null){
                    return;
                }
                Uri uri = intent.getData();
                mProfileFragment.setUri(uri);
                try {
                    @SuppressWarnings("deprecation")
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    mProfileFragment.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
        initUi();
        initListener();
        mDrawerLayout = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState()
        mNavigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setCheckable(true);
        showUserInfomation();
    }

    private void initUi(){ // Anh xa thong tin dang nhap
        mNavigationView = findViewById(R.id.naviagtion_view);
        imgAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);
        carAboutus = findViewById(R.id.car_aboutus);
        carMap = findViewById(R.id.car_map);
        carManuals = findViewById(R.id.car_manuals);
        carContact = findViewById(R.id.car_contact);
    }
    private void initListener() {
        carAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeAboutActivity.class);
                startActivity(intent);
            }
        });
        carMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        carManuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManualsActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){ //Chuyen qua HomeFragment khi nhap Home
            if (mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }else if(id == R.id.nav_favorite){
            if (mCurrentFragment != FRAGMENT_FAVORITE) {
                replaceFragment(new FavoriteFragment());
                mCurrentFragment = FRAGMENT_FAVORITE;
            }
        }else if(id == R.id.nav_change_password){
            if (mCurrentFragment != FRAGMENT_CHANGE_PASSWORD) {
                replaceFragment(new ChangePasswordFragment());
                mCurrentFragment = FRAGMENT_CHANGE_PASSWORD;
            }
        }else if(id == R.id.nav_profile){
            if (mCurrentFragment != FRAGMENT_PROFILE) {
                replaceFragment(mProfileFragment);
                mCurrentFragment = FRAGMENT_PROFILE;
            }
        }else if(id == R.id.nav_signout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            finish();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer((GravityCompat.START));
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
    public void showUserInfomation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if(name == null){
            tvName.setVisibility(View.GONE);
        }else {
            tvName.setVisibility(View.VISIBLE);
            tvName.setText(name);
        }
        tvName.setText(name);
        tvEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.img_default).into(imgAvatar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActvityResultLancher.launch(Intent.createChooser(intent, "Select Picture"));
    }
}