package com.example.mobappproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import static com.example.mobappproject.SignInActivity.userName;
public class MenuUser extends Fragment {


    TextView txtName;
    private Button  btnAdmin, btnlogout, btnAboutComp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_menu_user, container, false);
        txtName = v.findViewById(R.id.txtName);
        txtName.setText(userName);





        //btn acc settings
        Button btnAccountSetting  = v.findViewById(R.id.btnAccountSetting);
        btnAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MenuUserSettings.class));
            }
        });

        //button logout
        btnlogout = v.findViewById(R.id.btnLogOut);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Sign.class));
            }
        });



        //button admin
        btnAdmin = v.findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AdminLogin.class));

                    }
                });


        //button admin
        btnAboutComp = v.findViewById(R.id.btnAboutComp);
        btnAboutComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), aboutcompany.class));

            }
        });
        return v;
    }}

