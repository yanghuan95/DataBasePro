package com.own.yh.databashpro.Reader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.own.yh.databashpro.Lab.ReaderLab;
import com.own.yh.databashpro.Model.ReaderModel;
import com.own.yh.databashpro.R;

/**
 * Created by yh on 2017/6/18.
 */

public class ShowReaderInfoActivity extends AppCompatActivity{

    private final static String ARGS = "ARGS";

    private String info_args;

    private TextView showID;
    private TextView showName;
    private TextView showReaderSex;
    private TextView showReaderRegDate;

    public static Intent newIntent(Context context, String args) {
        Intent intent = new Intent(context, ShowReaderInfoActivity.class);
        intent.putExtra(ARGS, args);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_reader_layout);

        showID = (TextView) findViewById(R.id.show_reader_id_view);
        showName = (TextView) findViewById(R.id.show_reader_name_view);
        showReaderRegDate = (TextView) findViewById((R.id.show_reader_reg_view));
        showReaderSex = (TextView) findViewById(R.id.show_reader_sex_view);

        info_args =  getIntent().getStringExtra(ARGS);
        if (info_args != null) {
            ReaderLab lab = new ReaderLab(ShowReaderInfoActivity.this);
            ReaderModel reader = lab.getReader(info_args);
            if (reader != null) {
                showID.setText(reader.getReader_id());
                showName.setText(reader.getReader_name());
                showReaderSex.setText(reader.getReader_sex());

                showReaderRegDate.setText(reader.getReader_reg_date().toString());
            }else{
                Toast.makeText(ShowReaderInfoActivity.this, "can't query the reader"
                        , Toast.LENGTH_SHORT).show();
            }

        }
    }
}
