package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.dommy.tab.R;
import com.dommy.tab.utils.SQLiteManager;
import com.dommy.tab.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText accountInput;
    private EditText passwordInput;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private SQLiteManager sqLiteManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
