package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;
import com.example.appoderfood.ThemBanAnActivity;
import com.example.appoderfood.ThemThucDonActivity;

public class ThucDonFragment extends Fragment {

    private static final int REQUEST_CODE_THEM = 112;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_thucdon,container,false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(R.string.thucdon);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThemBanAn = menu.add(1,R.id.id_ThemThucDon,1,"Thêm thực đơn");
        itemThemBanAn.setIcon(R.drawable.ic_add);
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.id_ThemThucDon :
                Intent intent = new Intent(getActivity(), ThemThucDonActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEM);
                break;
        }
        return true;
    }
}
