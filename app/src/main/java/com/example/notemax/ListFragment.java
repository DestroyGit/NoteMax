package com.example.notemax;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListFragment extends Fragment {

    private boolean isLandscape;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
//        if (isLandscape){
//            showLandNote(0);
//        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }


    @SuppressLint("ResourceAsColor")
    public void initList(View view){
        LinearLayout layoutView = (LinearLayout) view;
        String [] titles = getResources().getStringArray(R.array.titles);
        String [] dates = getResources().getStringArray(R.array.dates);
        int textSize = 25;

//      прописать условие о том, что длины массивов dates и titles равны

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            String date = dates[i];
            TextView tv = new TextView(getContext());
            tv.setText(String.format("%s\n%s", title, date));
            tv.setTextSize(textSize);
            if (i%2 == 0){
                tv.setBackgroundResource(R.color.first_line);
            } else{
                tv.setBackgroundResource(R.color.second_line);
            }
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(view1 -> showNote(fi));
        }
    }

    private void showNote(int index){
        if(isLandscape){
            showLandNote(index);
        } else{
            showPortNote(index);
        }
    }

    private void showLandNote(int index){
        NoteFragment nf = NoteFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, nf);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNote(int index) {
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, NoteActivity.class);
            intent.putExtra(NoteFragment.ARG_INDEX, index);
            startActivity(intent);
        }
    }
}