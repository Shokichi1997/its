package com.itsdl.androidtutorials.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.itsdl.androidtutorials.R;


public class AddStudentDialog extends AppCompatDialogFragment {
    private EditText edtStudentCode;
    private EditText edtEmail;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                        String student_code = edtStudentCode.getText().toString();
                        String email = edtEmail.getText().toString();
                        try {
                            listener.applyTexts(student_code, email);
                        }catch (Exception e){

                        }


                    }
                });
        edtStudentCode = view.findViewById(R.id.edt_student_code);
        edtEmail = view.findViewById(R.id.edit_email);
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
         /*   throw new ClassCastException(context.toString() +
              "must implement ExampleDialogListener");*/
        }
    }
    public interface ExampleDialogListener {
        void applyTexts(String student_code, String email);
    }
}
