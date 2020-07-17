package com.meghshyam.restapi.SportsApp.view;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meghshyam.restapi.SportsApp.R;
import com.meghshyam.restapi.SportsApp.databinding.FragmentEventlistBinding;
import com.meghshyam.restapi.SportsApp.viewmodel.EventListViewModel;

public class FragmentEvent extends Fragment {

    EventListViewModel eventListModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=bind(inflater,container);
        return v;
    }

    private View bind(LayoutInflater inflater,ViewGroup con) {
        FragmentEventlistBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_eventlist,con,false);
        eventListModel = new EventListViewModel(getContext());
        binding.setViewModel(eventListModel);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        eventListModel.setUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        eventListModel.tearDown();
    }
}
