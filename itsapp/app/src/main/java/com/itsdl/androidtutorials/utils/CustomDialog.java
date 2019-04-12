package com.itsdl.androidtutorials.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itsdl.androidtutorials.R;

public class CustomDialog extends Dialog {
    private Button btnCloseHint;
    private TextView lblMessageHint;
    private TextView lblTitleHint;
    private ImageView imgIconHint;

    public CustomDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_hint);
        addControls();
        addEvents();
    }

    public void setLblTitleHint(String titleHint) {
        this.lblTitleHint.setText(titleHint);
    }
    public void setLblMessageHint(String hintMessage) {
        this.lblMessageHint.setText(hintMessage);
    }
    public void setImgIconHint(int drawableResource){
        this.imgIconHint.setBackgroundResource(drawableResource);
    }

    public void  setBtnCloseHint(int drawableResource){
        this.btnCloseHint.setBackgroundResource(drawableResource);
    }

    private void addControls() {
        btnCloseHint = findViewById(R.id.btnCloseHint);
        lblMessageHint = findViewById(R.id.lblMessageHint);
        lblTitleHint = findViewById(R.id.lblTitleHint);
        imgIconHint = findViewById(R.id.imgIconHint);
        setCanceledOnTouchOutside(false);
    }

    private void addEvents() {
        btnCloseHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
