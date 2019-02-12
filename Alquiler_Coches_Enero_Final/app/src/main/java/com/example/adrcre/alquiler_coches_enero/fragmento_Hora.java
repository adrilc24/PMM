package com.example.adrcre.alquiler_coches_enero;



import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;

import java.util.Calendar;


/**
 *
 */
public class fragmento_Hora extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int min = c.get(Calendar.MINUTE);
        int h = c.get(Calendar.HOUR_OF_DAY);
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(), h, min, DateFormat.is24HourFormat(getActivity()));
    }
}
