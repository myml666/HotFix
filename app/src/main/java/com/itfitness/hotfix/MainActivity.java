package com.itfitness.hotfix;


import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.itfitness.hotfix.utils.TestUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestUtil.test();
    }
}
