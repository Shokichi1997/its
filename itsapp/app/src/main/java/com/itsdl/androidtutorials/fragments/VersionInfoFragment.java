package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.DrawerLocker;

/**
 * A simple {@link Fragment} subclass.
 */
public class VersionInfoFragment extends Fragment {

    private Toolbar version_toolbar;

    public VersionInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_version_infor, container, false);
        version_toolbar = root.findViewById(R.id.version_toolbar);

        //set tool bar app
        ((AppCompatActivity )getActivity()).setSupportActionBar(version_toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Version Info");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        version_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        return root;
    }

}
