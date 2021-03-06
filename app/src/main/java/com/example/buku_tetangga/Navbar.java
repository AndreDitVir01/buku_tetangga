package com.example.buku_tetangga;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.buku_tetangga.activities.SearchAddBookActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class Navbar extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private KeranjangFragment keranjangFragment;
    private RiwayatFragment riwayatFragment;
    private AkunFragment akunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Set BottomNavigationView
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        keranjangFragment = new KeranjangFragment();
        riwayatFragment = new RiwayatFragment();
        akunFragment = new AkunFragment();

        Intent i = getIntent();
        String data = i.getStringExtra("intentTo");
        if(data!=null && data.contentEquals("2")){
            setFragment(keranjangFragment);
        }else{
            setFragment(homeFragment);
        }

        ArrayList<String> profile = new ArrayList<>();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.nav_beranda:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_sewa:
                        setFragment(keranjangFragment);
                        return true;
                    case R.id.nav_riwayat:
                        setFragment(riwayatFragment);
                        return true;
                    case R.id.nav_akun:
                        setFragment(akunFragment);
                        return true;
                    default: return false;
                }
            }
        });
    }

    private void setFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    public void toAddBook(View view) {
        startActivity(new Intent(this, AddBookActivity.class));
        Animatoo.animateSlideUp(this);
    }

    public void toPetunjukPengguna(View view) {
        startActivity(new Intent(this, PetunjukPenggunaActivity.class));
        Animatoo.animateSlideUp(this);
    }

    public void toWebBukuTetanga(View view) {
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://bukutetangga.000webhostapp.com"));
        startActivity(openURL);
    }

    public void toGetShareAbleLinkButang(View view) {
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Mau beli buku? Tapi mahal? Padahal cuman buat dibaca satu kali? Eitss, yuk Sewa Buku di Buku Tetangga, download aplikasi di https://bukutetangga.000webhostapp.com, sekarang juga!");
            try {
                Objects.requireNonNull(this).startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
            }
        }

    public void toKebijakanPrivasi(View view) {
        startActivity(new Intent(this, KebijakanPrivasiActivity.class));
        finish();
        Animatoo.animateSlideUp(this);
    }

    public void toPencarian(View view) {
        startActivity(new Intent(this, MainActivity2.class));
        finish();
        Animatoo.animateSlideUp(this);
    }
}
