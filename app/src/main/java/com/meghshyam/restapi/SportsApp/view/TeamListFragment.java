package com.meghshyam.restapi.SportsApp.view;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.meghshyam.restapi.SportsApp.App;
import com.meghshyam.restapi.SportsApp.R;
import com.meghshyam.restapi.SportsApp.databinding.FragmentTeamBinding;
import com.meghshyam.restapi.SportsApp.viewmodel.TeamListViewModel;

public class TeamListFragment  extends Fragment {
    TeamListViewModel teamListViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=bind(inflater,container);
        initRecyclerView(v);
        initSearchView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        teamListViewModel.setUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        teamListViewModel.tearDown();
    }

    private View bind(LayoutInflater inflater,ViewGroup con) {
        FragmentTeamBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team,con,false);
        teamListViewModel = new TeamListViewModel(getContext());
        binding.setViewModel(teamListViewModel);
        return binding.getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }


    private void initSearchView(View v)
    {
        SearchView searchView = v.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                App.searchIsOn = query;
                teamListViewModel.tearDown();
                teamListViewModel.setUp();
                Toast.makeText(getActivity(), ""+query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
