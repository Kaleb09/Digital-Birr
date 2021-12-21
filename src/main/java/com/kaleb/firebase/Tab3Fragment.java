package com.kaleb.firebase;

import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {
    TextView t3,t,t1,t2; FirebaseDatabase fdd;
    DatabaseReference drr,drr0,drr1,dr;
    Toolbar tol;
    String s1;
    String s2;MainActivity1 m;
    String saa;
    public static String balance="";

    TabLayout tabLayout; ViewPager viewPager;TabPagerAdapter padapter;
    Point po;

    public Tab3Fragment() {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3Fragment newInstance(String param1, String param2) {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_tab3, container, false);
        m=new MainActivity1();
        ((MainActivity1)getActivity()).title("Home");
        t1=(TextView)root.findViewById(R.id.textView8);
t=(TextView)root.findViewById(R.id.textView10);
        fdd=FirebaseDatabase.getInstance();
        drr=FirebaseDatabase.getInstance().getReference();
        drr1=FirebaseDatabase.getInstance().getReference();
        tabLayout = (TabLayout)root.findViewById(R.id.tab_layout2);
      //  viewPager = (ViewPager)root.findViewById(R.id.pager);
      // padapter = new TabPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        ass();
        Fragment fragment=new sent();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.framelay,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.commit();
       // tabLayout.setupWithViewPager(viewPager);
       confiureTabLayout();
        // Inflate the layout for this fragment
        return root;
    }

    private void confiureTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Sent"));
        tabLayout.addTab(tabLayout.newTab().setText("Recieved"));
        /* viewPager.setAdapter(padapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));*/
        tabLayout.addOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                          // viewPager.setCurrentItem(tab.getPosition());
                                                           Fragment fragment=new sent();
                                                           switch (tab.getPosition()){
                                                               case 0:
                                                                   fragment=new sent();
                                                                   break;
                                                               case 1:
                                                                   fragment=new recieved();
                                                                   break;
                                                           }
                                                           FragmentManager fm=getFragmentManager();
                                                           FragmentTransaction ft=fm.beginTransaction();
                                                           ft.replace(R.id.framelay,fragment);
                                                           ft.setTransition(FragmentTransaction.TRANSIT_NONE);
                                                           ft.commit();
                                                       }
                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {
                                                       }
                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {
                                                       }
                                                   });

            }

            public void ass(){
        s1=m.sa+"/First name";
        drr0=drr1.child(s1);
        ValueEventListener ec=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String st=dataSnapshot.getValue(String.class);
                t1.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };drr0.addListenerForSingleValueEvent(ec);
        s2=m.sa+"/Balance";
        dr=drr.child(s2);
        ValueEventListener el=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sst=dataSnapshot.getValue(String.class);
                t.setText(sst);assi(sst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };dr.addListenerForSingleValueEvent(el);

    }
    public static String encodeUserEmail(String userEmail){
        String a,b,c,d,e, r= userEmail.replace(".",",");
        a=r.replace("#","*");
        b=a.replace("$","+");
        c=b.replace("[","(");
        d=c.replace("]",")");
        e=d.replace("/","|");
        return e;
    }   public static void assi(String  n){
        balance=n;
    }
}