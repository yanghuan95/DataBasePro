package com.own.yh.databashpro.Reader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.own.yh.databashpro.Lab.ReaderLab;
import com.own.yh.databashpro.Model.ReaderModel;
import com.own.yh.databashpro.R;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by yh on 2017/6/19.
 */

public class AlterReaderInfoActivity extends AppCompatActivity {

    private String id;
    private String name;
    private String sex;

    private EditText id_text;
    private EditText name_text;
    private EditText sex_text;
    private String reg_date;
    private Button submit_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_reader_layout);

        id_text = (EditText) findViewById(R.id.add_reader_id_view);
        name_text = (EditText) findViewById(R.id.add_reader_name_view);
        sex_text = (EditText) findViewById(R.id.add_reader_sex_view);
        submit_button = (Button) findViewById(R.id.add_sub_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = id_text.getText().toString();
                name = name_text.getText().toString();
                sex = sex_text.getText().toString();

                if(!dealId()){
                    Toast.makeText(AlterReaderInfoActivity.this,
                            "please enter a id like 161420325", Toast.LENGTH_SHORT).show();
                }else if(!dealName()){
                    Toast.makeText(AlterReaderInfoActivity.this,
                            "please enter a name", Toast.LENGTH_SHORT).show();
                } else if (!dealSex()) {
                    Toast.makeText(AlterReaderInfoActivity.this,
                            "please enter your sex(man or woman)", Toast.LENGTH_SHORT).show();
                }else{
                    ReaderModel reader = new ReaderModel();
                    reader.setReader_id(id);
                    reader.setReader_sex(sex);
                    reader.setReader_name(name);

                    reg_date = (String) DateFormat.format(getResources()
                            .getString(R.string.date_format_string), new Date());

                    reader.setReader_reg_date(reg_date);

                    ReaderLab lab = new ReaderLab(AlterReaderInfoActivity.this);
                    if (lab.alterReader(reader) != -1) {
                        Toast.makeText(AlterReaderInfoActivity.this,
                                "insert success, please enter back button", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AlterReaderInfoActivity.this,
                                "insert fails", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    private boolean dealId() {
        boolean result = true;
        //必须是9位数字
        Pattern pattern = Pattern.compile("[0-9]{9}");
        if(!pattern.matcher(id).matches()){
            result = false;
        }
        return result;
    }
    private boolean dealName() {
        boolean result = true;
        if(name.equals("")){
            result = false;
        }
        return result;
    }

    private boolean dealSex() {
        boolean result = true;

        if (!(sex.equals("man") || sex.equals("woman"))) {
            result = false;
        }

        return result;
    }

}
