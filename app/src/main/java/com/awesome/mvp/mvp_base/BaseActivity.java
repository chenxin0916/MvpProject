package com.awesome.mvp.mvp_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    private P mPresenter;
    private ImmersionBar mImmersionBar;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mImmersionBar = ImmersionBar.with(this);
        initStateBar(mImmersionBar);
//        mImmersionBar.init();
        mPresenter = initPresent();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initData();
    }

    protected abstract int getView();
    protected abstract void initStateBar(ImmersionBar immersionBar);
    protected abstract P initPresent();
    protected abstract void initData();

    public P getmPresenter(){
        return mPresenter;
    }

    /**
     * 无参数跳转界面
     */
    public void startActivity(Class clazz,boolean isClose){
        startActivity(new Intent(this,clazz));
        if (isClose){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        if (mImmersionBar !=null){
            mImmersionBar.destroy();
        }

    }

}
