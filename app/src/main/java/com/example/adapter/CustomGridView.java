package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appoderfood.R;
import com.example.dto.BanAnDTO;

import java.util.List;

public class CustomGridView extends BaseAdapter implements View.OnClickListener{

    Context context;
    int layout;
    List<BanAnDTO> list;
    ViewHolderBanAn viewHolderBanAn;

    public CustomGridView(Context context, int layout, List<BanAnDTO> list) {
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
        return list.get(position).getMaBan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderBanAn = new ViewHolderBanAn();
            v  = inflater.inflate(R.layout.custom_layout_hienthi_baan,parent,false);

            viewHolderBanAn.imgBanAn = v.findViewById(R.id.img_BanAn);
            viewHolderBanAn.btnOrder = v.findViewById(R.id.btn_Order);
            viewHolderBanAn.btnPay = v.findViewById(R.id.btn_Pay);
            viewHolderBanAn.txtTenBan = v.findViewById(R.id.txt_TenBanAn);

            v.setTag(viewHolderBanAn);
            viewHolderBanAn.imgBanAn.setOnClickListener(this);

        }else {
            viewHolderBanAn = (ViewHolderBanAn) v.getTag();
        }
        BanAnDTO banAnDTO = list.get(position);
        viewHolderBanAn.txtTenBan.setText(banAnDTO.getTenBan());

        return v;
    }


    public class ViewHolderBanAn{
        ImageView imgBanAn;
        ImageButton btnOrder,btnPay;
        TextView txtTenBan;

    }
    @Override
    public void onClick(View v) {
        viewHolderBanAn = (ViewHolderBanAn) ((View)v.getParent()).getTag();
        viewHolderBanAn.btnPay.setVisibility(View.VISIBLE);
        viewHolderBanAn.btnOrder.setVisibility(View.VISIBLE);

    }
}
