package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adapter.CustomGridViewHienThiMonAnTheoLoai;
import com.example.appoderfood.R;
import com.example.dao.MonAnDAO;
import com.example.dto.MonAnDTO;

import java.util.List;

public class DanhSachMonAnFragment extends Fragment {
    GridView gridView;
    CustomGridViewHienThiMonAnTheoLoai adapter;
    List<MonAnDTO> listMonAnDTO;

    MonAnDAO monAnDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_thucdon,container,false);

        gridView = v.findViewById(R.id.gv_ThucDon);

        Bundle bundle = getArguments();
        monAnDAO = new MonAnDAO(getActivity());
        if(bundle != null){
            int maloai = bundle.getInt("maloai");

            listMonAnDTO = monAnDAO.layDanhSachMonAnTheoLoai(maloai);
            adapter = new CustomGridViewHienThiMonAnTheoLoai(getActivity(),R.layout.custom_layout_hienthidanhsachmonan,listMonAnDTO);
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthiloaithucdon", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });





        return v;
    }
}
