package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.CustomDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
   View view;
   Button btnLogin;
   EditText edtEmail,edtPssword;
   CheckBox ckbRemember;
   Toolbar toolbar;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        getViews();
        addEvents();
        return view;
    }
    public void getViews(){

        edtEmail=view.findViewById(R.id.edtStudentCode);
        edtPssword=view.findViewById(R.id.edtPassword);
        btnLogin=view.findViewById(R.id.btnLogin);
        ckbRemember=view.findViewById(R.id.ckbRemember);
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***Kiểm tra email và password trống
                 trông => call showMessage(message) và return*/

                /**request => thành công call goToFunctionsMain()*/
            }
        });
    }

    public void goToFunctionsMain(){
        Fragment fragment = new FunctionsFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frContainer2,fragment,"FUNCTIONS")
                .addToBackStack("FUNCTIONS")
                .commit();
    }

    private void showMessage(String message) {
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
