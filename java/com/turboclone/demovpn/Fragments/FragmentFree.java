package com.turboclone.demovpn.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anchorfree.hydrasdk.HydraSdk;
import com.anchorfree.hydrasdk.api.data.Country;
import com.anchorfree.hydrasdk.callbacks.Callback;
import com.anchorfree.hydrasdk.exceptions.HydraException;
import com.turboclone.demovpn.R;
import com.turboclone.demovpn.adapter.ServerListAdapterFree;

import java.util.ArrayList;
import java.util.List;

public class FragmentFree extends Fragment implements ServerListAdapterFree.RegionListAdapterInterface {
    private RecyclerView recyclerView;
    private ServerListAdapterFree adapter;
    private ArrayList<Country> countryArrayList;
    private FragmentVip.RegionChooserInterface regionChooserInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = view.findViewById(R.id.region_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        countryArrayList = new ArrayList<>();
        adapter = new ServerListAdapterFree(countryArrayList, getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadServers();
    }

    private void loadServers() {
        HydraSdk.countries(new Callback<List<Country>>() {
            @Override
            public void success(List<Country> countries) {
                for (int i = 0; i < countries.size(); i++)
                {
                    if (i%2==0)
                    {
                        countryArrayList.add(countries.get(i));
                    }
                }
                //int server=countries.get().getServers();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(HydraException e) {

            }
        });
    }

    @Override
    public void onCountrySelected(Country item) {
        regionChooserInterface.onRegionSelected(item);
        // dismiss();
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        if (ctx instanceof FragmentVip.RegionChooserInterface) {
            regionChooserInterface = (FragmentVip.RegionChooserInterface) ctx;
        }
    }
}
