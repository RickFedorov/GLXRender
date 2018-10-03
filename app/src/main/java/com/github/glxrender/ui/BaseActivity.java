package com.github.glxrender.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    public static Context CONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        CONTEXT = this;
    }

    protected abstract int getActivityLayout();

}
