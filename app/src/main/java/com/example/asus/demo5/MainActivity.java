package com.example.asus.demo5;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private CheckBox rem_pw, auto_login;
    private Button btn_login;
    private TextView btnQuit;
    private String userNameValue,passwordValue;
    private SharedPreferences sp;
   /* private CheckBox ck;*/
    private ImageView nosee,see;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        //获得实例对象;
        /*ck=(CheckBox)findViewById(R.id.ck);
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton arg0,boolean arg1) {
                // TODO Auto-generated method stub
                if(ck.isChecked()){
                    //设置EditText的密码为可见的
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //设置密码为隐藏的
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });*/
        see=(ImageView)findViewById(R.id.see);
        nosee=(ImageView)findViewById(R.id.nosee);
        nosee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nosee.setVisibility(View.INVISIBLE);
                see.setVisibility(View.VISIBLE);
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
        userName = (EditText) findViewById(R.id.accountEt);
        password = (EditText) findViewById(R.id.pwdEt);
        rem_pw = (CheckBox) findViewById(R.id.remember);
        auto_login = (CheckBox) findViewById(R.id.auto_login);
        btn_login = (Button) findViewById(R.id.subBtn);
        btnQuit = (TextView) findViewById(R.id.regisiter);
        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            rem_pw.setChecked(true);
            userName.setText(sp.getString("USER_NAME", ""));
            password.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if (sp.getBoolean("AUTO_ISCHECK", false)) {
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                //跳转界面
                Intent intent = new Intent(MainActivity.this, LogoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }
        // 登录监听事件  现在默认为用户名为：liu 密码：123
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userNameValue = userName.getText().toString();
                passwordValue = password.getText().toString();
                if (userNameValue.equals("123") && passwordValue.equals("123")) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //登录成功和记住密码框为选中状态才保存用户信息
                    if (rem_pw.isChecked()) {
                        //记住用户名、密码、
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", userNameValue);
                        editor.putString("PASSWORD", passwordValue);
                        editor.commit();
                    }
                    //跳转界面
                    Intent intent = new Intent(MainActivity.this, LogoActivity.class);
                    MainActivity.this.startActivity(intent);
                    //finish();
                } else {
                    Toast.makeText(MainActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
                }
            }
        });
        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rem_pw.isChecked()) {
                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });
        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (auto_login.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                see.setVisibility(View.INVISIBLE);
                nosee.setVisibility(View.VISIBLE);
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }
}
