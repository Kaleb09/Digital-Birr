package com.kaleb.firebase;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.kaleb.firebase.Tab3Fragment.balance;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2Fragment extends Fragment {
    EditText et,et1; Button b1,b2; IntentIntegrator intentIntegrator;String qrr,saa,sa1,am,ad,ba,ba1;
    FirebaseDatabase fd,fd2;public static boolean n=false;
    DatabaseReference dr,dr0,dr1,dr2,dr3,dr4,dr5,dr6,dr7,dr8,dr9;int i,i1,i2,i3,i4,i5;
    private View view;
    ImageButton imq,ims;LayoutInflater layoutInflater;
    TextView tx,dia;View layout,dialog;Toast toast; customdialog cd;public  static Tab2Fragment instance;Tab4Fragment t;MainActivity1 p;
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

    public Tab2Fragment() {
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
    public static Tab2Fragment newInstance(String param1, String param2) {
        Tab2Fragment fragment = new Tab2Fragment();
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

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root=inflater.inflate(R.layout.fragment_tab2, container, false);
        ((MainActivity1)getActivity()).title("Send");
imq=(ImageButton)root.findViewById(R.id.qr);
ims=(ImageButton) root.findViewById(R.id.send);
dialog=inflater.inflate(R.layout.customdialog,(ViewGroup)root.findViewById(R.id.textView6));
//tx=(TextView)root.findViewById(R.id.textView5);
//layoutInflater=getLayoutInflater();
t=new Tab4Fragment();
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        intentIntegrator=new IntentIntegrator(getActivity());
        imq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator = IntentIntegrator.forSupportFragment(Tab2Fragment.this);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("scan");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.initiateScan();
            }
        });
        et=(EditText)root.findViewById(R.id.adress) ;
        et1=(EditText)root.findViewById(R.id.amount);
        fd=FirebaseDatabase.getInstance();
        dr=FirebaseDatabase.getInstance().getReference();
        dr1=FirebaseDatabase.getInstance().getReference();
        dr5=FirebaseDatabase.getInstance().getReference();
        ims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et1.getText().toString())){
                    ((MainActivity1)getActivity()).toast("enter the amount");
                    //tx.setText("enter the amount");
                        //Toast.makeText(getActivity().getApplicationContext(), "enter the amount", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(et.getText().toString())){
                    //Toast.makeText(getActivity().getApplicationContext(), "enter adress", Toast.LENGTH_SHORT).show();
                    ((MainActivity1)getActivity()).toast("enter the adress");
                    //
                }else{
                    am=encodeUserEmail(et1.getText().toString());
                    ad=encodeUserEmail(et.getText().toString());
                    saa="75";//balance;
                    sa1=t.s;
                    i1=Integer.parseInt(saa);i3=Integer.parseInt(am);
                    dr0=dr.child(ad);
                    dr2=dr1.child(ad+"/Balance");
                    dr6=dr5.child(sa1+"/Balance");
                    dr3 = fd.getReference(ad+"/Balance");
                    dr4=fd.getReference(sa1+"/Balance");

                    if(i1>i3) {
                        ((TextView)dialog.findViewById(R.id.textView6)).setText(am+" birr will be transferred to "+ad);
                        cd = new customdialog(getActivity());
                        cd.show();
                       // remained();
                      }else{
                        ((MainActivity1)getActivity()).toast("Insufficient Balance");
                    }
                }
            }
        });
        instance=this;
        return root;
    }
    public static String encodeUserEmail(String userEmail){
        String a,b,c,d,e, r= userEmail.replace(".",",");
        a=r.replace("#","*");
        b=a.replace("$","+");
        c=b.replace("[","(");
        d=c.replace("]",")");
        e=d.replace("/","|");
        return e;
    }
    public static int inc(int  n){
        return n++;
    }
    public static void bool(boolean c){
        n=c;
    }public static Tab2Fragment getInstance(){
        return instance;
    }
    public void remained(){

        ValueEventListener vl = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ValueEventListener vv = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ba = dataSnapshot.getValue().toString();

                            i = Integer.parseInt(ba);
                            i1 = Integer.parseInt(am);
                            final String re = String.valueOf(i + i1);
                            dr3.setValue(re).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dr6 = fd.getReference(ad + "/Transaction/Recieved");
                                    String key = dr6.push().getKey();
                                    dr6.child(key).setValue(i1 + " Birr is Recieved From Adress " + sa1);

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    dr2.addListenerForSingleValueEvent(vv);
                    ValueEventListener vvl = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ba1 = dataSnapshot.getValue().toString();
                            i2 = Integer.parseInt(ba1);
                            i4 = Integer.parseInt(am);
                            String r = String.valueOf(i2 - i4);
                            dr4.setValue(r);
                            int v = 0;
                            v = inc(v);
                            dr7 = fd.getReference(sa1 + "/Transaction/Sent");
                            String key = dr7.push().getKey();
                            dr7.child(key).setValue(i4 + " Birr is Sent To Adress " + ad);
                            ((MainActivity1)getActivity()).toast(i4 + " Birr is Succsesfully Sent To Adress " + ad);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    dr6.addListenerForSingleValueEvent(vvl);

                } else {
                    ((MainActivity1)getActivity()).toast("Adress Incorrect");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dr0.addListenerForSingleValueEvent(vl);
    }
}
