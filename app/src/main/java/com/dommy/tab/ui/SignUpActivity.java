package com.dommy.tab.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dommy.tab.BaseActivity;
import com.dommy.tab.R;
import com.dommy.tab.module.UserBean;
import com.dommy.tab.utils.AppManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import static com.dommy.tab.utils.ApiConfig.URL_SIGNUP;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private Button signupButton;
    private EditText inviteCodeInput;
    private EditText nameInput;
    private EditText accountInput;
    private EditText passwordInput;
    private EditText gradeInput;


    private ProgressDialog progressDialog;
    private long exitTime;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        initView();
        progressDialog = new ProgressDialog(this);//进度条
        progressDialog.setCancelable(false);
        //登录状态和数据库操作
        //跳转


    }


    public void initView(){
        signupButton=(Button)findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(this);
        inviteCodeInput=(EditText)findViewById(R.id.signup_invite);
        nameInput=(EditText)findViewById(R.id.signup_name);
        accountInput=(EditText)findViewById(R.id.signup_account);
        passwordInput=(EditText)findViewById(R.id.signup_password);
        gradeInput=(EditText)findViewById(R.id.singup_grade);



    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.btn_signup){
            String inviteCode = inviteCodeInput.getText().toString();
            String name = nameInput.getText().toString();
            String account = accountInput.getText().toString();
            String password = passwordInput.getText().toString();
            String grade = gradeInput.getText().toString();

            //判断输入是否为空
            if (inviteCode.trim().length() > 0 && name.trim().length() > 0 && account.trim().length() > 0 && password.trim().length() > 0 && grade.trim().length() > 0) {
                checkLogin(inviteCode, name,account, password,grade);
            }else {
                Toast.makeText(getApplicationContext(), " 注册信息不能为空", Toast.LENGTH_LONG).show();
            }
        }
    }





    private void checkLogin(final String inviteCode, final String name,final String account, final String password,final String grade){

        int grade_num=0;
        switch (grade){
            case "研一":
                grade_num=1;
                break;
            case "研二":
                grade_num=2;
                break;
            case "研三":
                grade_num=3;
                break;
            case "博士":
                grade_num=4;
                break;
                default:
                    Toast.makeText(this,"请按正确格式填写年级：研一、研二、研三、博士！",Toast.LENGTH_LONG).show();
                    break;

        }
        String tag_string_req = "req_login";
        progressDialog.setMessage("注册中...");
        showDiaglog();

        OkGo.<String>post(URL_SIGNUP)
                .tag(this)
                .params("invitecode",inviteCode)
                .params("number",account)
                .params("name",name)
                .params("password",password)
                .params("status",grade_num)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //响应数据
                        hideDialog();
                        UserBean userBean = new Gson().fromJson(response.body().toString(), UserBean.class);
                        if (userBean.getCode()==1) {

                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(),userBean.getMsg(),Toast.LENGTH_LONG).show();

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

    @Override
    public void onStop(){
        AppManager.getAppManager().finishActivity(this);
        super.onStop();
    }

}
