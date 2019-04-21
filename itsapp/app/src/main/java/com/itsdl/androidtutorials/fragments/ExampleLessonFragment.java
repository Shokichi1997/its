package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.ExamplesAdapter;
import com.itsdl.androidtutorials.utils.Example;

import java.util.ArrayList;
import java.util.List;

public class ExampleLessonFragment extends Fragment {
    private RecyclerView recExamples;
    private ExamplesAdapter adapter;
    private List<Example> examples;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_examples,container,false);
        addControls(root);
        return root;
    }

    private void addControls(View root) {
        examples = new ArrayList<>();
        recExamples = root.findViewById(R.id.recExample);
        recExamples.setHasFixedSize(true);
        prepareListExample();
        adapter = new ExamplesAdapter(getContext(),examples);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recExamples.setLayoutManager(layoutManager);
        recExamples.setItemAnimator(new DefaultItemAnimator());
        recExamples.setAdapter(adapter);
    }

    private void prepareListExample() {
        examples.add(new Example("TextView","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("EditText","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("RadioButton","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("CheckBox","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("RatingBar","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("Progressbar","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("AutoComplete TextView","abcxyz","zzzz",R.drawable.ic_launcher_background));
        examples.add(new Example("Switch","abcxyz","zzzz",R.drawable.ic_launcher_background));
    }
}
