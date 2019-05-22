package com.example.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.appoderfood.R;

import java.util.Calendar;

public class FragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,day,thang,nam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        EditText editText = getActivity().findViewById(R.id.edNgaySinh);
        String ngaySinh = dayOfMonth+"/"+month+"/"+year;
        editText.setText(ngaySinh);

    }
}
