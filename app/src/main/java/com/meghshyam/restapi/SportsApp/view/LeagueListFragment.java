package com.meghshyam.restapi.SportsApp.view;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meghshyam.restapi.SportsApp.R;
import com.meghshyam.restapi.SportsApp.databinding.FragmentAlBinding;
import com.meghshyam.restapi.SportsApp.viewmodel.LeagueViewModel;

public class LeagueListFragment extends Fragment {
    LeagueViewModel leagueModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=bind(inflater,container);
        initRecyclerView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        leagueModel.setUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        leagueModel.tearDown();
    }

    private View bind(LayoutInflater inflater,ViewGroup con) {
        FragmentAlBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_al,con,false);
        leagueModel = new LeagueViewModel(getContext());
        binding.setViewModel(leagueModel);
        return binding.getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }
}
