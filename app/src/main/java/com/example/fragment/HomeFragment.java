package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.adapter.CustomGridView;
import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;
import com.example.appoderfood.SuaBanAnActivity;
import com.example.appoderfood.ThemBanAnActivity;
import com.example.dao.BanAnDAO;
import com.example.dto.BanAnDTO;

import java.util.List;

public class HomeFragment extends Fragment {

    public static final int REQUEST_CODE_THEM = 111;
    public static final int REQUEST_CODE_SUA = 16;

    GridView gridView;
    List<BanAnDTO> listBanAnDTO;
    BanAnDAO banAnDAO;
    CustomGridView adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_home,container,false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(R.string.home);
        setHasOptionsMenu(true);

        gridView = v.findViewById(R.id.gv_BanAn);

        banAnDAO = new BanAnDAO(getActivity());
        upDateBanAn();

        registerForContextMenu(gridView);

        return v;
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
        //Toast.makeText(getActivity(),"Vi trí :"+menuInfo.position,Toast.LENGTH_SHORT).show();
        int viTri = menuInfo.position;
        int maBan = listBanAnDTO.get(viTri).getMaBan();
        switch (id){
            case R.id.item_Sua:
                Intent intentTruyMaBan = new Intent(getActivity(), SuaBanAnActivity.class);
                intentTruyMaBan.putExtra("maban",maBan);
                startActivityForResult(intentTruyMaBan,REQUEST_CODE_SUA);

                break;
            case R.id.item_Xoa:
                boolean kiemTraXoaBan = banAnDAO.xoaBanAnTheoMaBanAn(maBan);
                if (kiemTraXoaBan){
                    upDateBanAn();
                    Toast.makeText(getActivity(),"Xóa bàn thành công",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getActivity(),"Có vấn đề trong xóa bàn",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemThemBanAn = menu.add(1,R.id.menu_ThemBanAn,1,"Thêm bàn ăn");
        itemThemBanAn.setIcon(R.drawable.ic_add);
        itemThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_ThemBanAn :
                Intent intent = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEM);
                break;
        }
        return true;
    }
    private void upDateBanAn(){
        listBanAnDTO = banAnDAO.getDanhSachBanAn();
        adapter = new CustomGridView(getActivity(),R.layout.custom_layout_hienthi_baan,listBanAnDTO);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_THEM && resultCode== Activity.RESULT_OK){
            Intent intent = data;
            boolean kiemTra = intent.getBooleanExtra("KiemTra",false);
            if(kiemTra){
                upDateBanAn();
                Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_LONG).show();
            }
        }else if(requestCode == REQUEST_CODE_SUA && resultCode == Activity.RESULT_OK){
            Intent intent = data;
            boolean kiemTra = intent.getBooleanExtra("kiemtra",false);
            if(kiemTra){
                upDateBanAn();
                Toast.makeText(getActivity(),"Sửa thành công",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getActivity(),"Sửa thất bại",Toast.LENGTH_LONG).show();
            }

        }
    }
}
