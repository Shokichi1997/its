package com.itsdl.androidtutorials.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.activities.MainActivity;
import com.itsdl.androidtutorials.networks.AddStudentRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.Global;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.HashMap;
import java.util.Map;


public class AddStudentDialog extends AppCompatDialogFragment  {
    private EditText edtStudentCode;
    private EditText edtEmail;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Global.EMAIL="";
        Global.STUDENT_CODE="";
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_student_dialog, null);

        builder.setView(view)
                .setTitle("Add student")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email="";
                        try {
                            if(!edtEmail.getText().toString().isEmpty()){
                                email= edtEmail.getText().toString();
                            }
                            if(!edtStudentCode.getText().toString().isEmpty()){
                                String student_code = edtStudentCode.getText().toString();
                                Global.STUDENT_CODE = student_code;
                                Global.EMAIL        = email;
                               // addStudent(student_code,email);
                            }
                            else{
                                showDialog("Student code not is empty");
                            }

                        }catch (Exception e){
                        }
                    }
                });
        edtStudentCode = view.findViewById(R.id.edt_student_code);
        edtEmail = view.findViewById(R.id.edit_email);
        return builder.create();
    }


    private void showDialog(String message) {
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setLblMessageHint(message);
        dialog.setLblTitleHint("Notification");
        dialog.setImgIconHint(R.drawable.tick_green);
        dialog.setBtnCloseHint(R.drawable.background_card);
        dialog.setEventsClose(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
