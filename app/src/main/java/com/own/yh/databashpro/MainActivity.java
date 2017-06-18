package com.own.yh.databashpro;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return ShowMainFragment.newInstance();
    }
}
