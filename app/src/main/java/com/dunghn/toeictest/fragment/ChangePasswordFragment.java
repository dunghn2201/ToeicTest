package com.dunghn.toeictest.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dunghn.toeictest.DAO.NguoiDungDAO;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.NguoiDung;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {
    private View rootView;
    NguoiDungDAO nguoiDungDAO;
    EditText edNewPass, edReNewPass;
    Button btnChangePass;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_change_password, container, false);
//anhxa
        edNewPass = (EditText) rootView.findViewById(R.id.edPass);
        edReNewPass = (EditText) rootView.findViewById(R.id.edNewPass);
        btnChangePass = (Button) rootView.findViewById(R.id.btnChange);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE_CHANGEPASS", MODE_PRIVATE);
                    String strUserName = pref.getString("USERNAME", "");
                    String sedNewPass = edNewPass.getText().toString().trim();
                    nguoiDungDAO = new NguoiDungDAO(getActivity());
                    int s2 = nguoiDungDAO.getIdFromUserName(strUserName);
                    NguoiDung user = new NguoiDung(s2, sedNewPass);
                    if (validateForm() > 0) {
                        if (nguoiDungDAO.changePasswordNguoiDung(user) > 0) {
                            Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "Username: " + strUserName + " \nID là: " + s2 + " \nMật khẩu mới là: " + sedNewPass, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                    Toast.makeText(getActivity(), "Có điều gì đó không ổn \nVui lòng kiểm tra lại tài khoản", Toast.LENGTH_SHORT).show();
                }
            }

            private int validateForm() {
                int check = 1;
                if (edNewPass.getText().length() == 0 || edReNewPass.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    check = -1;
                } else {
                    String NewPass = edNewPass.getText().toString();
                    String ReNewPass = edReNewPass.getText().toString();
                    if (!NewPass.equals(ReNewPass)) {
                        Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        check = -1;
                    }
                }
                return check;
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

}
