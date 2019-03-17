package com.awesome.mvp.screen.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.awesome.mvp.R;
import com.awesome.mvp.mvp_base.BaseActivity;
import com.awesome.mvp.mvp_presenter.LoginPresenter;
import com.awesome.mvp.mvp_view.LoginView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView{


    private EditText mCount;
    private EditText mPassword;

    @Override
    protected int getView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initStateBar(ImmersionBar immersionBar) {
        immersionBar.init();
    }
    @Override
    protected LoginPresenter initPresent() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        mCount = findViewById(R.id.et_count);
        mPassword = findViewById(R.id.et_password);
        AppCompatButton btnToLogin = findViewById(R.id.btn_login);
        AppCompatButton btnCamera = findViewById(R.id.btn_camera);
        btnToLogin.setOnClickListener(mBtnOnclickListener);
        btnCamera.setOnClickListener(mBtnOnclickListener);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener mBtnOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_login:
                        LoginActivityPermissionsDispatcher.loginToWithPermissionCheck(LoginActivity.this);
                        break;
                    case R.id.btn_camera:
                        LoginActivityPermissionsDispatcher.startCameraViewWithPermissionCheck(LoginActivity.this);
                        break;
                }
        }
    };

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    public void loginTo(){
        startActivity(new Intent(this,WeatherActivity.class));
//        getmPresenter().login(mCount.getText().toString(),mPassword.getText().toString());
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    public  void  startCameraView(){
        startActivity(WeatherActivity.class,false);
    }


    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void onPermissionDeny(){
        Toast.makeText(this,"拒绝camera权限",Toast.LENGTH_SHORT).show();
    }

}
