package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appoderfood.R;
import com.example.appoderfood.ThemBanAnActivity;

public class HomeFragment extends Fragment {

    public static final int REQUEST_CODE_THEM = 111;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_home,container,false);
        setHasOptionsMenu(true);
        return v;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_THEM && resultCode== Activity.RESULT_OK){
            Intent intent = data;
            boolean kiemTra = intent.getBooleanExtra("KiemTra",false);
            if(kiemTra){
                Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_LONG).show();
            }
        }
    }
}
