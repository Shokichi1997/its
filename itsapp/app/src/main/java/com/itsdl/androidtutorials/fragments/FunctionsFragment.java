package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.Functions_adapter;
import com.itsdl.androidtutorials.utils.FunctionUser;

import java.util.ArrayList;

public class FunctionsFragment extends Fragment {
    private GridView gridFunction;
    private Toolbar toolbar;
    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_functions,container,false);

        gridFunction = root.findViewById(R.id.gridFunction);

//        toolbar = root.findViewById(R.id.function_toolbar);
//        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("   Android Tutorials");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setLogo(R.drawable.ic_menu_24dp);
//        drawer = root.findViewById(R.id.drawer_layout);

//        View logo = toolbar.getChildAt(1);
//        logo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //mDrawerLayout.openDrawer(Gravity.LEFT);
//
//            }
//        });

        ArrayList<FunctionUser> function = new ArrayList<>();
        function.add(new FunctionUser(1,"Add lesson",true,R.drawable.ic_menu_gallery));
        function.add(new FunctionUser(1,"Add lesson",true,R.drawable.ic_menu_gallery));
        function.add(new FunctionUser(1,"Add lesson",true,R.drawable.ic_menu_gallery));
        function.add(new FunctionUser(1,"Add lesson",true,R.drawable.ic_menu_gallery));
        function.add(new FunctionUser(1,"Add lesson",true,R.drawable.ic_menu_gallery));
        function.add(new FunctionUser(1,"Add lesson",true,R.drawable.ic_menu_gallery));

        ArrayAdapter adapter = new Functions_adapter(root.getContext(),function);
        gridFunction.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridFunction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new AddLessonFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.frContainer,fragment,"ADD_LESSON")
                        .addToBackStack(null)
                        .commit();

            }
        });
    }


}
