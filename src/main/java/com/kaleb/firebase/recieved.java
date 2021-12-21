package com.kaleb.firebase;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recieved#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recieved extends Fragment implements RemoveClickListner{
    private static final String TAG ="fgh" ;
    TextView t;
    Button b; FirebaseDatabase f;
    DatabaseReference d;private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnAddItem,but;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etTitle, etDescription;
    String title = "",description = "";
    ImageView crossImage;  public static List<String> list=new ArrayList<String>();
    // RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;RecyclerView.Adapter adapter;

    @Override
    public void OnRemoveClick(int index) {
        d=f.getReference("k/Transaction/Recieved/"+list.get(index));
        d.removeValue();
        list.remove(index);
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
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

    public recieved() {
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
    public static recieved newInstance(String param1, String param2) {
        recieved fragment = new recieved();
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
        View root=inflater.inflate(R.layout.fragment_recieved, container, false);
        mRecyclerView = (RecyclerView)root.findViewById(R.id.recycler_view);
        mRecyclerAdapter = new RecyclerAdapter(myList,this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        f= FirebaseDatabase.getInstance();
        d=f.getReference("k/Transaction/Recieved");
        final ValueEventListener va=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        String s=ds.getValue(String.class);
                        String ss=ds.getKey();
                        lista(ss);
                        // t.setText(s);
                        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                        RecyclerData mLog = new RecyclerData();
                        mLog.setDescription(s);
                        myList.add(mLog);
                        mRecyclerAdapter.notifyData(myList);
                    }}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };d.addValueEventListener(va);  d.removeEventListener(va);
        ChildEventListener childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String sa=dataSnapshot.getValue(String.class);
                String ss=dataSnapshot.getKey();
                lista(ss);
                RecyclerData mLog = new RecyclerData();
                mLog.setDescription(sa);
                myList.add(mLog);
                mRecyclerAdapter.notifyData(myList);

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };d.addChildEventListener(childEventListener);
         return root;
    }
    public static void lista(String k){
        list.add(k);
    }
}