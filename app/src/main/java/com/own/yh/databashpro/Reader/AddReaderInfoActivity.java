package com.own.yh.databashpro.Reader;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.own.yh.databashpro.Lab.ReaderLab;
import com.own.yh.databashpro.Model.ReaderModel;
import com.own.yh.databashpro.R;

import java.util.Date;

/**
 * Created by yh on 2017/6/19.
 */

public class AddReaderInfoActivity extends AppCompatActivity {

    private String id;
    private String name;
    private String sex;

    private EditText id_text;
    private EditText name_text;
    private EditText sex_text;
    private Date reg_date;
    private Button submit_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reader_layout);

        id_text = (EditText) findViewById(R.id.add_reader_id_view);
        name_text = (EditText) findViewById(R.id.add_reader_name_view);
        sex_text = (EditText) findViewById(R.id.add_reader_sex_view);
        submit_button = (Button) findViewById(R.id.add_sub_button);
        reg_date = new Date();
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = id_text.getText().toString();
                name = name_text.getText().toString();
                sex = sex_text.getText().toString();

                if(id.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "please enter a id", Toast.LENGTH_SHORT).show();
                }else if(name.equals("")){
                    Toast.makeText(AddReaderInfoActivity.this,
                            "please enter a name", Toast.LENGTH_SHORT).show();
                } else if (sex.equals("")) {
                    Toast.makeText(AddReaderInfoActivity.this,
                            "please enter your sex(man or woman)", Toast.LENGTH_SHORT).show();
                }else{
                    ReaderModel reader = new ReaderModel();
                    reader.setReader_id(id);
                    reader.setReader_sex(sex);
                    reader.setReader_name(name);
                    reader.setReader_reg_date(reg_date);

                    ReaderLab lab = new ReaderLab(AddReaderInfoActivity.this);
                    lab.addReader(reader);
                    Toast.makeText(AddReaderInfoActivity.this,
                            "insert success", Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });

    }
}
