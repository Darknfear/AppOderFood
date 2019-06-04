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
import android.widget.GridView;

import com.example.adapter.CustomGridViewThucDon;
import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;
import com.example.appoderfood.ThemBanAnActivity;
import com.example.appoderfood.ThemThucDonActivity;
import com.example.dao.LoaiMonAnDAO;
import com.example.dto.LoaiMonAnDTO;

import java.util.ArrayList;
import java.util.List;

public class ThucDonFragment extends Fragment {
    GridView gridView;
    CustomGridViewThucDon adapter;
    List<LoaiMonAnDTO> dsLoaiMonAn;
    LoaiMonAnDAO loaiMonAnDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_thucdon,container,false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(R.string.thucdon);
        gridView = v.findViewById(R.id.gv_ThucDon);
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        dsLoaiMonAn = loaiMonAnDAO.getListLoaiMonAn();
        adapter = new CustomGridViewThucDon(getActivity(),R.layout.custom_layout_thucdon,dsLoaiMonAn);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
                startActivity(intent);
                break;
        }
        return true;
    }
}
