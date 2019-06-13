package com.example.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.adapter.CustomGridViewHienThiMonAnTheoLoai;
import com.example.appoderfood.R;
import com.example.appoderfood.SoLuongActivity;
import com.example.appoderfood.SuaMonAnActivity;
import com.example.dao.MonAnDAO;
import com.example.dto.MonAnDTO;

import java.util.List;

public class DanhSachMonAnFragment extends Fragment {
    GridView gridView;
    CustomGridViewHienThiMonAnTheoLoai adapter;
    List<MonAnDTO> listMonAnDTO;

    MonAnDAO monAnDAO;

    int maBan;
    int maMonAn;
    int maloai;

    public static final int REQUEST_CODE_SUAMON = 98;

    int maQuyen = 0;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_thucdon,container,false);

        gridView = v.findViewById(R.id.gv_ThucDon);

        sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maQuyen = sharedPreferences.getInt("maquyen",0);

        Bundle bundle = getArguments();
        monAnDAO = new MonAnDAO(getActivity());
        if(bundle != null){
            maloai = bundle.getInt("maloai");
            maBan = bundle.getInt("maban");

            hienThiGiaoDien();

            if (maQuyen == 1){
                registerForContextMenu(gridView);
            }


            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (maBan != 0){
                        Intent intent = new Intent(getActivity(), SoLuongActivity.class);
                        intent.putExtra("maban",maBan);
                        intent.putExtra("mamoan",listMonAnDTO.get(position).getId());
                        startActivity(intent);
                    }
                }
            });

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

    private void hienThiGiaoDien(){

        listMonAnDTO = monAnDAO.layDanhSachMonAnTheoLoai(maloai);
        adapter = new CustomGridViewHienThiMonAnTheoLoai(getActivity(),R.layout.custom_layout_hienthidanhsachmonan,listMonAnDTO);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int viTri = menuInfo.position;
        maMonAn = listMonAnDTO.get(viTri).getId();

        switch (id){
            case R.id.item_Sua:

                Intent intentSuaMon = new Intent(getActivity(), SuaMonAnActivity.class);
                intentSuaMon.putExtra("MaMon",maMonAn);
                intentSuaMon.putExtra("TenMon",listMonAnDTO.get(viTri).getTenMon());
                intentSuaMon.putExtra("GiaMon",listMonAnDTO.get(viTri).getGia());
                startActivityForResult(intentSuaMon,REQUEST_CODE_SUAMON);

                break;
            case R.id.item_Xoa:

                boolean kiemTraXoaMon = monAnDAO.xoaMonTheoMaMon(maMonAn,maloai);

                if (kiemTraXoaMon){
                    hienThiGiaoDien();
                    Toast.makeText(getActivity(),"Xóa món thành công",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getActivity(),"Có lỗi khi xóa món ăn",Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intentGetData = data;
        boolean check = intentGetData.getBooleanExtra("check",false);
        if(requestCode == REQUEST_CODE_SUAMON && resultCode == Activity.RESULT_OK){
            if (check){
                hienThiGiaoDien();
                Toast.makeText(getActivity(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getActivity(),"Cập nhật thất bại",Toast.LENGTH_SHORT).show();
        }
    }
}
