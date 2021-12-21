package com.kaleb.firebase;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.zip.Inflater;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout draw;public ActionBarDrawerToggle ac;Tab2Fragment t;String sa="k"; Inflater inflater;
    TextView to;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    { super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        t=new Tab2Fragment();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        to=findViewById(R.id.textView9);
        title("Home");
        draw=findViewById(R.id.drawer_layout);
        NavigationView navv=findViewById(R.id.nav_view);
        ac=new ActionBarDrawerToggle(this,draw,toolbar,R.string.nav_open,R.string.nav_close);
        draw.addDrawerListener(ac);
        ac.syncState();
        navv.setNavigationItemSelectedListener(this);
        Fragment fragment=new Tab3Fragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.layout,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.commit();
        confiureTabLayout();
    }

    private void confiureTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_baseline_home_24));
        tabLayout.addTab(tabLayout.newTab().setText("send").setIcon(R.drawable.ic_baseline_send_24));
        tabLayout.addTab(tabLayout.newTab().setText("Recieve").setIcon(R.drawable.recieve));
        tabLayout.addTab(tabLayout.newTab().setText("Deposit").setIcon(R.drawable.deposit));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment=new Tab1Fragment();
                int  tabi= ContextCompat.getColor(getBaseContext(),R.color.tabicon);
                tab.getIcon().setColorFilter(tabi, PorterDuff.Mode.SRC_IN);
                switch (tab.getPosition()){
                    case 0:
                        fragment=new Tab3Fragment();
                        break;
                    case 1:
                        fragment=new Tab2Fragment();
                        break;
                    case 2:
                        fragment=new Tab1Fragment();
                        break;
                    case 3:
                        fragment=new Tab4Fragment();
                        break;
                }
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.layout,fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_NONE);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int  tabi= ContextCompat.getColor(getBaseContext(),R.color.tabiconun);
                tab.getIcon().setColorFilter(tabi, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });}
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.nav_share:{
                Intent share=new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT,"subject here");
                share.putExtra(Intent.EXTRA_TEXT,"text here");
      startActivity(Intent.createChooser(share,"Share via"));
            }
            break;
            case R.id.nav_about:{
                Intent inten = new Intent(getApplicationContext(), about.class);
                startActivity(inten);
            }break;
            case R.id.nav_contactus:{
                Intent inten = new Intent(getApplicationContext(), contactus.class);
                startActivity(inten);
            }
            break;
        }
        DrawerLayout d=findViewById(R.id.drawer_layout);
        d.closeDrawer(GravityCompat.START);
        return true;
    }protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data!=null){
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            String qrr=intentResult.getContents();
            t.et.setText(qrr);}else
            super.onActivityResult(requestCode, resultCode, data);
    }
    public void title(String s){
        to.setText(s);
    }
public void toast(String s){
    LayoutInflater li=getLayoutInflater();
    View layout=li.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.framelayout));
    ((TextView)layout.findViewById(R.id.textView5)).setText(s);
    Toast toast=new Toast(getApplicationContext());
    toast.setView(layout);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER,0,-600);
    toast.show();
}
}


