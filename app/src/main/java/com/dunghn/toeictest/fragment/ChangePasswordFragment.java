package com.dunghn.toeictest.fragment;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dunghn.toeictest.DAO.UserDAO;
import com.dunghn.toeictest.LoginActivity;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {
    private View rootView;
    UserDAO userDAO;
    User user;
    EditText edNewPass, edReNewPass;
    TextView tvUserNameChangePass, tvEmailChangePass;
    Button btnChangePass;
    int s3, s2;
    SharedPreferences pref;
    String strUserName, sedNewPass;
    String fullnamechangepass, emailchangepass;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_change_password, container, false);

//anhxa

        edNewPass = (EditText) rootView.findViewById(R.id.edPass);
        edReNewPass = (EditText) rootView.findViewById(R.id.edNewPass);
        btnChangePass = (Button) rootView.findViewById(R.id.btnChange);

        tvUserNameChangePass = (TextView) rootView.findViewById(R.id.tvUserChangePass);
        tvEmailChangePass = (TextView) rootView.findViewById(R.id.tvEmailChangePass);

        pref = getActivity().getSharedPreferences("USER_FILE_CHANGEPASS", MODE_PRIVATE);
        strUserName = pref.getString("USERNAME", "");
        sedNewPass = edNewPass.getText().toString().trim();
        userDAO = new UserDAO(getActivity());
        try {

            s3 = userDAO.getIdFromUserName(strUserName);
            Cursor rs = userDAO.getDataFromId(s3);

            rs.moveToFirst();
            fullnamechangepass = rs.getString(rs.getColumnIndex(UserDAO.COL_FULLN));
            emailchangepass = rs.getString(rs.getColumnIndex(UserDAO.COL_EMAIL));

            if (!rs.isClosed()) {
                rs.close();
            }
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
        if (fullnamechangepass != null && emailchangepass != null) {
            tvUserNameChangePass.setText(fullnamechangepass);
            tvEmailChangePass.setText(emailchangepass);
        }
        if (strUserName.equalsIgnoreCase("Administrator")) {
            tvUserNameChangePass.setText("Administrator");
            tvEmailChangePass.setVisibility(View.INVISIBLE);
        }
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    s2 = userDAO.getIdFromUserName(strUserName);
                    user = new User(s2, sedNewPass);
                    if (validateForm() > 0) {
                        if (userDAO.changePasswordUser(user) > 0) {
                            Toast.makeText(getActivity(), "Save successfully", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getActivity(), "Username: " + strUserName + " \nID là: " + s2 + " \nMật khẩu mới là: " + sedNewPass, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Save failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                    Toast.makeText(getActivity(), "Something went wrong \n\" +\n" +
                            "                                \"Please check the data input", Toast.LENGTH_SHORT).show();
                }
            }

            private int validateForm() {
                int check = 1;
                if (edNewPass.getText().length() == 0 || edReNewPass.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Please complete all the information", Toast.LENGTH_SHORT).show();
                    check = -1;
                } else {
                    String NewPass = edNewPass.getText().toString();
                    String ReNewPass = edReNewPass.getText().toString();
                    if (!NewPass.equals(ReNewPass)) {
                        Toast.makeText(getActivity(), "Password is not allowed to match", Toast.LENGTH_SHORT).show();
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
