package com.kaleb.firebase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {
    Bitmap bit;MainActivity1 m;
    WindowManager win;
    QRGEncoder qren;
    ImageView im; Display dis;
    Point po;String adr;
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

    public Tab1Fragment() {
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
    public static Tab1Fragment newInstance(String param1, String param2) {
        Tab1Fragment fragment = new Tab1Fragment();
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
        View root=inflater.inflate(R.layout.fragment_tab1, container, false);
        im=root.findViewById(R.id.imageView);
        m=new MainActivity1();
        ((MainActivity1)getActivity()).title("Recieve");
        //Tab2Fragment.getInstance().toast("sdfgnm");
        win=(WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        dis=win.getDefaultDisplay();
        po=new Point();
        try {
            Recieve();
        } catch (WriterException e) {
            e.printStackTrace();
        } return root;
    }
    public void Recieve() throws WriterException {
        /*Intent iin =getIntent();
        adr=iin.getStringExtra("adress");*/
        adr=m.sa;
        if(!TextUtils.isEmpty(adr)){
            dis.getSize(po);
            int wi,he;
            wi=po.x;he=po.y;int dim=wi<he?wi:he;//dim=dim*3/4;
            qren=new QRGEncoder(adr,null, QRGContents.Type.TEXT,dim);
            bit=qren.encodeAsBitmap();
            im.setImageBitmap(bit);
        }
    }
}