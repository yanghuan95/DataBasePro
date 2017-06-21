package com.own.yh.databashpro.Book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.own.yh.databashpro.Lab.BookLab;
import com.own.yh.databashpro.Model.BookModel;
import com.own.yh.databashpro.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by yh on 2017/6/21.
 */

public class AlterBookInfoActivity extends AppCompatActivity{

    private String id;
    private String name;
    private String author;
    private String public_name;
    private String public_date;
    private String reg_date;
    boolean isDate = false;
    private EditText id_text;
    private EditText name_text;
    private EditText author_text;
    private EditText public_name_text;
    private EditText public_date_text;
    private Button submit_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_layout);

        id_text = (EditText) findViewById(R.id.add_book_id_view);
        name_text = (EditText) findViewById(R.id.add_book_name_view);
        author_text = (EditText) findViewById(R.id.add_book_author_view);
        submit_button = (Button) findViewById(R.id.add_book_sub_button);
        public_date_text = (EditText) findViewById(R.id.add_book_public_date_view);
        public_name_text = (EditText) findViewById(R.id.add_book_public_view);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = id_text.getText().toString();
                name = name_text.getText().toString();
                author = author_text.getText().toString();
                public_name = public_name_text.getText().toString();
                public_date = public_date_text.getText().toString();

                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat(getResources()
                            .getString(R.string.date_format_string));
                    //日期匹配格式严格
                    dateFormat.setLenient(false);
                    dateFormat.parse(public_date);

                } catch (ParseException e) {
                    Toast.makeText(AlterBookInfoActivity.this,
                            "the pulic date format is a error format", Toast.LENGTH_SHORT).show();
                }

                reg_date = (String) DateFormat.format(getResources()
                        .getString(R.string.date_format_string), new Date());

                if(!dealId()){
                    Toast.makeText(AlterBookInfoActivity.this,
                            "please enter a id", Toast.LENGTH_SHORT).show();
                }else if(name.equals("")){
                    Toast.makeText(AlterBookInfoActivity.this,
                            "please enter a name", Toast.LENGTH_SHORT).show();
                } else if (author.equals("")) {
                    Toast.makeText(AlterBookInfoActivity.this,
                            "please enter book author name", Toast.LENGTH_SHORT).show();
                } else if (public_date.equals("")) {
                    Toast.makeText(AlterBookInfoActivity.this,
                            "please enter public date", Toast.LENGTH_SHORT).show();
                } else if (public_name.equals("")) {
                    Toast.makeText(AlterBookInfoActivity.this,
                            "please enter public name", Toast.LENGTH_SHORT).show();
                } else if (reg_date.equals("")) {
                    Toast.makeText(AlterBookInfoActivity.this,
                            "please enter register date", Toast.LENGTH_SHORT).show();
                }  else {
                    BookModel book = new BookModel();
                    book.setBook_author(author);
                    book.setBook_id(id);
                    book.setBook_name(name);
                    book.setBook_public(public_name);
                    book.setBook_public_date(public_date);
                    book.setBook_reg_date(reg_date);
                    book.setIsborrowed(false);


                    BookLab lab = new BookLab(AlterBookInfoActivity.this);
                    if (lab.alterBook(book) != -1) {
                        Toast.makeText(AlterBookInfoActivity.this,
                                "insert success, please enter back button", Toast.LENGTH_SHORT).show();
                        submit_button.setEnabled(false);
                    } else {
                        Toast.makeText(AlterBookInfoActivity.this,
                                "insert false", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean dealId() {
        boolean result = true;
        Pattern pattern = Pattern.compile("[A-Z]{2}-[0-9]{3}");
        if (!pattern.matcher(id).matches()) {
            result = false;
        }

        return result;
    }
}
