package com.awesome.mvp.mvp_base;

import java.lang.ref.WeakReference;

public class BasePresenter <V extends IView> {

    private WeakReference<V> mWeakReference;

    public void attachView(V view) {
        mWeakReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mWeakReference != null){
            mWeakReference.clear();
            mWeakReference = null;
        }
    }
    public V getView() {
        return mWeakReference.get();
    }

    protected String getTAG(){
        return this.getClass().getSimpleName();
    }
}
