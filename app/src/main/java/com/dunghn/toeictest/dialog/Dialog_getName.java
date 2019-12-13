package com.dunghn.toeictest.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.dunghn.toeictest.DAO.ResultDAO;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.Result;


@SuppressLint("ValidFragment")
public class Dialog_getName extends DialogFragment {
    Result mresult;
    public Dialog_getName(Result result) {
        mresult = result;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo");
        final View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_name, null);
        builder.setView(v);
        final EditText edtName = (EditText) v.findViewById(R.id.edtName);
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        builder.setPositiveButton("Lưu ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edtName.getText().toString();
                final ResultDAO resultDAO = new ResultDAO(getActivity());
                resultDAO.InsertKQ(name, mresult.getTime(), mresult.getScore());
                Toast.makeText(getActivity(), "Đã lưu", Toast.LENGTH_SHORT).show();
            }


        });
        return builder.create();
    }


}
