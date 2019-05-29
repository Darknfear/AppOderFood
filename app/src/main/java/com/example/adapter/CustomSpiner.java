package com.example.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appoderfood.R;
import com.example.dto.LoaiMonAnDTO;

import java.util.List;

public class CustomSpiner extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiMonAnDTO> list;
    ViewHolder viewHolder;

    public CustomSpiner(Context context, int layout, List<LoaiMonAnDTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getMaLoai();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.custom_layout_loaithucdon_spiner,parent,false);

            viewHolder.txtTenLoaiMon = view.findViewById(R.id.sp_txt_TenLoaiMon);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = list.get(position);
        viewHolder.txtTenLoaiMon.setText(loaiMonAnDTO.getTenLoai());
        viewHolder.txtTenLoaiMon.setTag(loaiMonAnDTO.getMaLoai());
        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.custom_layout_loaithucdon_spiner,parent,false);

            viewHolder.txtTenLoaiMon = view.findViewById(R.id.sp_txt_TenLoaiMon);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = list.get(position);
        viewHolder.txtTenLoaiMon.setText(loaiMonAnDTO.getTenLoai());
        viewHolder.txtTenLoaiMon.setTag(loaiMonAnDTO.getMaLoai());
        return view;
    }

    public class ViewHolder{
        TextView txtTenLoaiMon;
    }
}
