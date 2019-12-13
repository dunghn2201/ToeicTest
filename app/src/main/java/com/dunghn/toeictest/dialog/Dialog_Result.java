package com.dunghn.toeictest.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.R;
import com.dunghn.toeictest.adapter.StatusAdapter;
import com.dunghn.toeictest.model.Status;

@SuppressLint("ValidFragment")
public class Dialog_Result extends DialogFragment {
    Status mstatus;
    Status originStatus;
    RecyclerView recyclerViewStatus;

    public Dialog_Result(Status status) {
        mstatus = status;
        originStatus = status;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Các câu bạn đã chọn");
        mstatus = new Status();
        final View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_status, null);
        builder.setView(v);
        recyclerViewStatus = (RecyclerView) v.findViewById(R.id.recyclerViewStatus);
        mstatus = originStatus;
        StatusAdapter ketQuaAdapter = new StatusAdapter(getActivity(), mstatus);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewStatus.setHasFixedSize(true);
        recyclerViewStatus.setLayoutManager(layoutManager);
        recyclerViewStatus.setAdapter(ketQuaAdapter);
        return builder.create();
    }

}
