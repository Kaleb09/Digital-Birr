package com.kaleb.firebase;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.kaleb.firebase.R;

public class customdialog extends Dialog implements android.view.View.OnClickListener {
    public Activity c;public Dialog d;public ImageButton yes,no;Tab2Fragment t2;
    public customdialog(Activity a) {
        super(a);
        this.c=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customdialog);
        yes=findViewById(R.id.imageButton3);
        no=findViewById(R.id.imageButton2);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        t2=new Tab2Fragment();
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.imageButton3:
        Tab2Fragment.getInstance().remained();
        break;
    case R.id.imageButton2:
        dismiss();
        break;
    default:
        break;
}
dismiss();
    }
}
