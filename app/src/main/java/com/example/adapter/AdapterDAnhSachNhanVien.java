package com.example.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;
import com.example.dao.NhanVienDAO;
import com.example.dto.NhanVienDTO;
import com.example.fragment.NhanVienFragment;

import java.util.List;

public class AdapterDAnhSachNhanVien extends BaseAdapter {
    Context context;
    int layout;
    List<NhanVienDTO> nhanVienDTOList;

    ViewHolderNhanVien viewHolderNhanVien;

    NhanVienDAO nhanVienDAO;


    public AdapterDAnhSachNhanVien(Context context, int layout, List<NhanVienDTO> nhanVienDTOList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienDTOList = nhanVienDTOList;
        nhanVienDAO = new NhanVienDAO(context);
    }

    @Override
    public int getCount() {
        return nhanVienDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public static class ViewHolderNhanVien{
        public static ImageView imgHinhNhanVien;
        TextView txtTenNhanVien,txtNgaySinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if(view == null){

            viewHolderNhanVien = new ViewHolderNhanVien();
            NhanVienDTO nhanVienDTO = nhanVienDTOList.get(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderNhanVien.imgHinhNhanVien = view.findViewById(R.id.img_HinhNhanVien);
            viewHolderNhanVien.txtTenNhanVien = view.findViewById(R.id.txt_TenNhanVien);
            viewHolderNhanVien.txtNgaySinh = view.findViewById(R.id.txt_NgaySinh);

            viewHolderNhanVien.txtTenNhanVien.setText(nhanVienDTO.getTenDangNhap());
            viewHolderNhanVien.txtNgaySinh.setText(nhanVienDTO.getNgaySinh());



            view.setTag(viewHolderNhanVien);

        }else {
            viewHolderNhanVien = (ViewHolderNhanVien) view.getTag();
        }



        return view;
    }


}
