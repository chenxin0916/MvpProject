package com.awesome.mvp.mvp_presenter;

import com.awesome.mvp.mvp_base.BasePresenter;
import com.awesome.mvp.mvp_view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {


    public void login(String account, String password){

        if (null == account || account.length() <= 0 ){
            getView().showToast("用户名不能为空");
            return;
        }

        if (null == password|| password.length() <= 0 ){
            getView().showToast("密码不能为空");
            return;
        }
        getView().showToast("登陆成功");
    }

}
