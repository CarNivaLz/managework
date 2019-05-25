package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dommy.tab.MainActivity;
import com.dommy.tab.R;
import com.dommy.tab.module.UserBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.model.Response;


import okhttp3.Cookie;
import okhttp3.HttpUrl;

import static com.dommy.tab.utils.ApiConfig.URL_LOGIN;

public class LoginActivity extends AppCompatActivity  {

    private Button loginButton;
    private EditText accountInput;
    private EditText passwordInput;
    private SharedPreferences sharedPreferences;
    private ProgressDialog progressDialog;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String flag = sharedPreferences.getString("haslogged", "");
        if(flag.equals("true")){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }


        setContentView(R.layout.activity_login);

        initView();
        progressDialog = new ProgressDialog(this);//进度条
        progressDialog.setCancelable(false);
        //登录状态和数据库操作
        //跳转
        loginButton.setOnClickListener(new loginOnClickListener());

    }

    public void initView(){
        loginButton=(Button)findViewById(R.id.btn_login);
        accountInput=(EditText)findViewById(R.id.login_account);
        passwordInput=(EditText)findViewById(R.id.login_password);
    }



    private class loginOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String usr_account = accountInput.getText().toString();
            String usr_password = passwordInput.getText().toString();
            //判断输入是否为空
            if (usr_account.trim().length() > 0 && usr_password.trim().length() > 0) {
                checkLogin(usr_account, usr_password);
            }else {
                Toast.makeText(getApplicationContext(), " 请输入账号或密码", Toast.LENGTH_LONG).show();
            }
        }
    }



    private void checkLogin(final String account, final String password){
        String tag_string_req = "req_login";
        progressDialog.setMessage("登陆中...");
        showDiaglog();

        OkGo.<String>post(URL_LOGIN)
                .tag(this)
                .params("name",account)
                .params("password",password)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //响应数据
                hideDialog();
                UserBean userBean = new Gson().fromJson(response.body().toString(), UserBean.class);
                if (userBean.getCode()==1) {

                    HttpUrl httpUrl = HttpUrl.parse("http://120.78.151.240:8080");
                    Cookie.Builder builder = new Cookie.Builder();
                    Cookie cookie = builder.name("peopleId").value(userBean.getMsg()).domain(httpUrl.host()).build();
                    CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
                    cookieStore.saveCookie(httpUrl, cookie);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("haslogged", "true");
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //显示进度条
    private void showDiaglog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    //隐藏进度条
    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }


    }

}
