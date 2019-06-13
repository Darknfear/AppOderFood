package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.AdapterDAnhSachNhanVien;
import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;
import com.example.appoderfood.SuaNhanVienActivity;
import com.example.dao.NhanVienDAO;
import com.example.dto.NhanVienDTO;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class NhanVienFragment extends Fragment{

    ListView lv_NhanVien;
    AdapterDAnhSachNhanVien adapterDAnhSachNhanVien;
    List<NhanVienDTO> listNhanVien;

    NhanVienDAO nhanVienDAO;

    public static final int REQUEST_CODE_SUANHANVIEN = 1998;
    public static final int REQUEST_CODE_ANH = 13;

    AdapterDAnhSachNhanVien.ViewHolderNhanVien viewHolderNhanVien;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_nhavien,container,false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(R.string.nhanvien);

        lv_NhanVien = v.findViewById(R.id.lv_NhanVien);
        hienThiDanhSachNhanVien();
        registerForContextMenu(lv_NhanVien);


        return v;
    }

    private void hienThiDanhSachNhanVien(){
        nhanVienDAO = new NhanVienDAO(getActivity());
        listNhanVien = nhanVienDAO.listNhanVien();
        adapterDAnhSachNhanVien = new AdapterDAnhSachNhanVien(getActivity(),R.layout.item_nhanvien,listNhanVien);
        lv_NhanVien.setAdapter(adapterDAnhSachNhanVien);
        adapterDAnhSachNhanVien.notifyDataSetChanged();
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
        int maNhanVien = listNhanVien.get(viTri).getMaNV();
        switch (id){
            case R.id.item_Sua:
                Intent intentSua = new Intent(getActivity(), SuaNhanVienActivity.class);
                intentSua.putExtra("manhanvien",maNhanVien);
                startActivityForResult(intentSua,REQUEST_CODE_SUANHANVIEN);
                break;
            case R.id.item_Xoa:
                boolean kiemTraXoaNhanVien = nhanVienDAO.xoaNhanVienTheoMa(maNhanVien);
                if (kiemTraXoaNhanVien){
                    hienThiDanhSachNhanVien();
                    Toast.makeText(getActivity(),"Xóa nhân viên thành công",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getActivity(),"Có lỗi khi xóa nhân viên",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intentGetData = data;
        if(requestCode == REQUEST_CODE_SUANHANVIEN && resultCode == Activity.RESULT_OK){
            boolean check = intentGetData.getBooleanExtra("kiemtraupdate",false);
            if(check){
                hienThiDanhSachNhanVien();
                Toast.makeText(getActivity(),"Update thành công ",Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getActivity(),"Update thất bại",Toast.LENGTH_SHORT).show();
        }
        
    }

}
