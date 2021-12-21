package com.kaleb.firebase;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab4Fragment extends Fragment {
     TextView t;public static boolean n=false;
    Button b,bu; FirebaseDatabase f;
    DatabaseReference d;customdialog cd;EditText e;public static String s="k";MainActivity1 m;
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

    public Tab4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab4Fragment newInstance(String param1, String param2) {
        Tab4Fragment fragment = new Tab4Fragment();
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
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_tab4, container, false);
/*t=(TextView) root.findViewById(R.id.textView4);
b=(Button)root.findViewById(R.id.button4);*/
m=new MainActivity1();
        ((MainActivity1)getActivity()).title("Deposit");
        f=FirebaseDatabase.getInstance();
        d=f.getReference("post");
       e=(EditText)root.findViewById(R.id.acc);
       bu=(Button)root.findViewById(R.id.butacc);
       bu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               account(e.getText().toString());
           }
       });
 //show();
/*b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        cd=new customdialog(getActivity());
        cd. show();if(n){shoow();}bool(false);
         d.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String s=dataSnapshot.getValue(String.class);
              t.setText(s);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
    }
});*/
        return root;  }
        public  void shoow(){

          /*  d.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String s=dataSnapshot.getValue(String.class);
                    t.setText(s);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
        }
        public static void bool(boolean c){
        n=c;
        }
public static void account(String st){
        s=st;
}
}
