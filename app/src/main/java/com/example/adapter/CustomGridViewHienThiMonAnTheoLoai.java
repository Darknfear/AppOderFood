package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appoderfood.R;
import com.example.dto.MonAnDTO;

import java.util.List;

public class CustomGridViewHienThiMonAnTheoLoai extends BaseAdapter {
    Context context;
    int layout;
    List<MonAnDTO> listMonAnDTO;
    ViewHolderHienThiDanhSachMonAn viewHolderHienThiDanhSachMonAn;

    public CustomGridViewHienThiMonAnTheoLoai(Context context, int layout, List<MonAnDTO> listMonAnDTO) {
        this.context = context;
        this.layout = layout;
        this.listMonAnDTO = listMonAnDTO;
    }

    @Override
    public int getCount() {
        return listMonAnDTO.size();
    }

    @Override
    public Object getItem(int position) {
        return listMonAnDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMonAnDTO.get(position).getId();
    }

    public class ViewHolderHienThiDanhSachMonAn{
        ImageView imgHinhMon;
        TextView txtTenMon,txtGiaTien;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderHienThiDanhSachMonAn = new ViewHolderHienThiDanhSachMonAn();
            view = inflater.inflate(layout,parent,false);

            viewHolderHienThiDanhSachMonAn.imgHinhMon = view.findViewById(R.id.img_HinhAnhMonAn);
            viewHolderHienThiDanhSachMonAn.txtTenMon = view.findViewById(R.id.txt_TenMon);
            viewHolderHienThiDanhSachMonAn.txtGiaTien = view.findViewById(R.id.txt_GiaMon);
            view.setTag(viewHolderHienThiDanhSachMonAn);
        }else {
            viewHolderHienThiDanhSachMonAn = (ViewHolderHienThiDanhSachMonAn) view.getTag();
        }

        MonAnDTO monAnDTO = listMonAnDTO.get(position);

        byte[] hinhAnh = monAnDTO.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);

        viewHolderHienThiDanhSachMonAn.imgHinhMon.setImageBitmap(bitmap);
        viewHolderHienThiDanhSachMonAn.txtTenMon.setText(monAnDTO.getTenMon());
        viewHolderHienThiDanhSachMonAn.txtGiaTien.setText("Giá :"+monAnDTO.getGia());

        return view;
    }
}
