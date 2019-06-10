package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appoderfood.R;
import com.example.dto.ThanhToanDTO;

import java.util.List;

public class AdapterThanhToan extends BaseAdapter {
    Context context;
    int layout;
    List<ThanhToanDTO> thanhToanDTOList;

    ViewHolderThanhToan viewHolderThanhToan;

    public AdapterThanhToan(Context context, int layout, List<ThanhToanDTO> thanhToanDTOList) {
        this.context = context;
        this.layout = layout;
        this.thanhToanDTOList = thanhToanDTOList;
    }

    @Override
    public int getCount() {
        return thanhToanDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return thanhToanDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolderThanhToan{
        TextView txtTenMon,txtGiaTien,txtSoLuong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderThanhToan = new ViewHolderThanhToan();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolderThanhToan.txtTenMon = view.findViewById(R.id.txt_TenMonAnThanhToan);
            viewHolderThanhToan.txtGiaTien = view.findViewById(R.id.txt_GiaTienMonAnThanhToan);
            viewHolderThanhToan.txtSoLuong = view.findViewById(R.id.txt_SoLuongMonAnCanThanhToan);

            view.setTag(viewHolderThanhToan);

        }else {
            viewHolderThanhToan = (ViewHolderThanhToan) view.getTag();
        }

        ThanhToanDTO thanhToanDTO = thanhToanDTOList.get(position);

        viewHolderThanhToan.txtTenMon.setText(thanhToanDTO.getTenMon());
        viewHolderThanhToan.txtSoLuong.setText(String.valueOf(thanhToanDTO.getSoLuong()));
        viewHolderThanhToan.txtGiaTien.setText(String.valueOf(thanhToanDTO.getGiaTien()));

        return view;
    }
}
