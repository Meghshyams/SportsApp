package com.meghshyam.restapi.SportsApp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.meghshyam.restapi.SportsApp.App;
import com.meghshyam.restapi.SportsApp.R;
import com.meghshyam.restapi.SportsApp.databinding.ItemDataBinding;
import com.meghshyam.restapi.SportsApp.model.DataModel;
import com.meghshyam.restapi.SportsApp.view.FragmentEvent;
import com.meghshyam.restapi.SportsApp.view.LeagueListFragment;
import com.meghshyam.restapi.SportsApp.view.TeamListFragment;
import com.meghshyam.restapi.SportsApp.viewmodel.DataItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private static final String TAG = "DataAdapter";
    private List<DataModel> data;
    View itemView;
    public DataAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,
                new FrameLayout(parent.getContext()), false);

        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        final DataModel dataModel = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Log.w("onClick",""+dataModel.getStrFormat());
                App.path="First";
                ((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new SportsListFragment())
                        .commit();*/
                Log.w("onClick",""+dataModel.getIdSport()+" "+App.path);
                if(App.path=="")
                {
                    App.path="First";
                    App.selectedSport = dataModel.getStrSprot();
                    ((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, new LeagueListFragment())
                            .addToBackStack(LeagueListFragment.class.getCanonicalName())
                            .commit();
                }
                else if(App.path=="First")
                {
                    App.path="Second";
                    App.selectedTeamId = dataModel.getIdSport();
                    ((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, new TeamListFragment())
                            .addToBackStack(TeamListFragment.class.getCanonicalName())
                            .commit();
                }
                else if(App.path=="Second")
                {
                    App.path="Third";
                    App.selectedMatchId = dataModel.getIdSport();
                    ((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, new FragmentEvent())
                            .addToBackStack(FragmentEvent.class.getCanonicalName())
                            .commit();
                }

            }
        });
        holder.setViewModel(new DataItemViewModel(dataModel));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onViewAttachedToWindow(DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    /* package */ static class DataViewHolder extends RecyclerView.ViewHolder {
        /* package */ ItemDataBinding binding;

        /* package */ DataViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        /* package */ void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        /* package */ void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        /* package */ void setViewModel(DataItemViewModel viewModel) {
            if (binding != null) {
                binding.setViewModel(viewModel);
            }
        }
    }
}
