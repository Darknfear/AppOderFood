package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;

public class CaiDatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_caidat,container,false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(R.string.caidat);
        return v;
    }
}
