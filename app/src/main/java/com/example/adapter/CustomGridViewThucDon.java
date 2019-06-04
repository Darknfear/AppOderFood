package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appoderfood.R;
import com.example.dao.LoaiMonAnDAO;
import com.example.dto.LoaiMonAnDTO;

import java.io.IOException;
import java.util.List;

public class CustomGridViewThucDon extends BaseAdapter {

    Context context;
    int layout;
    List<LoaiMonAnDTO> dsLoaiMonAn;
    ViewHolder viewHolder;
    LoaiMonAnDAO loaiMonAnDAO;

    public CustomGridViewThucDon(Context context, int layout, List<LoaiMonAnDTO> dsLoaiMonan) {
        this.context = context;
        this.layout = layout;
        this.dsLoaiMonAn = dsLoaiMonan;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return dsLoaiMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return dsLoaiMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsLoaiMonAn.get(position).getMaLoai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            //viewHolder.txtTenLoaiThucDon = view.findViewById(R.id.txt_TenLoaiThucDon);
            viewHolder.imgHinhLoaiThucDon = view.findViewById(R.id.img_HienThiMonAn);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = dsLoaiMonAn.get(position);
        Bitmap bitmap = loaiMonAnDAO.getHinhMonAn(loaiMonAnDTO.getMaLoai());
        viewHolder.imgHinhLoaiThucDon.setImageBitmap(bitmap);
       // viewHolder.txtTenLoaiThucDon.setText(loaiMonAnDTO.getTenLoai());

        return view;
    }

    public class ViewHolder{
        ImageView imgHinhLoaiThucDon;
        TextView txtTenLoaiThucDon;
    }
}
