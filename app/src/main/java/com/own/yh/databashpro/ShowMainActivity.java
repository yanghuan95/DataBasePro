package com.own.yh.databashpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.own.yh.databashpro.Book.AddBookInfoActivity;
import com.own.yh.databashpro.Book.ShowBookInfoActivity;
import com.own.yh.databashpro.Lab.BookLab;
import com.own.yh.databashpro.Lab.ReaderLab;
import com.own.yh.databashpro.Reader.AddReaderInfoActivity;
import com.own.yh.databashpro.Reader.ShowReaderInfoActivity;

/**
 * Created by yh on 2017/6/18.
 */

public class ShowMainActivity extends AppCompatActivity{

    private Button query_reader_button;
    private Button add_reader_button;
    private Button alter_reader_button;
    private Button delete_reader_button;
    private EditText get_reader_id_text;

    private Button query_book_button;
    private Button add_book_button;
    private Button alter_book_button;
    private Button delete_book_button;
    private EditText get_book_id_text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_container);

        initialReader();
        initalBook();

    }

    private void initalBook(){
        query_book_button = (Button) findViewById(R.id.query_book_main_button);
        get_book_id_text = (EditText) findViewById(R.id.get_book_id_edit_text);
        query_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = get_book_id_text.getText().toString();
                Intent intent = ShowBookInfoActivity.newIntent(ShowMainActivity.this, id);
                startActivity(intent);
            }
        });

        add_book_button = (Button) findViewById(R.id.add_book_main_button);
        add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowMainActivity.this, AddBookInfoActivity.class);
                startActivity(i);
            }
        });

        delete_book_button = (Button) findViewById(R.id.delet_book_main_button);
        delete_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id  = get_book_id_text.getText().toString();
                if (!id.equals("")) {
                    BookLab lab = new BookLab(ShowMainActivity.this);
                    lab.deleteBook(id);
                }
            }
        });

        alter_book_button = (Button) findViewById(R.id.alter_book_main_button);
        alter_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    private void initialReader() {
        get_reader_id_text = (EditText) findViewById(R.id.get_reader_id_edit_text);
        query_reader_button = (Button) findViewById(R.id.query_reader_button);
        query_reader_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = get_reader_id_text.getText().toString();
                Intent i = ShowReaderInfoActivity.newIntent(ShowMainActivity.this, id);
                startActivity(i);
            }
        });



        add_reader_button = (Button) findViewById(R.id.add_reader_button);
        add_reader_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowMainActivity.this, AddReaderInfoActivity.class);
                startActivity(i);
            }
        });

        delete_reader_button = (Button) findViewById(R.id.delete_reader_button);
        delete_reader_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = get_reader_id_text.getText().toString();
                if (!id.equals("")) {
                    ReaderLab lab = new ReaderLab(ShowMainActivity.this);
                    if(!lab.deleteReader(id)){
                        Toast.makeText(ShowMainActivity.this, "can't delete the reader"
                                , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ShowMainActivity.this, "delete the reader "
                                + id + " success", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        alter_reader_button = (Button) findViewById(R.id.alter_reader_button);
        alter_reader_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowMainActivity.this, AddReaderInfoActivity.class);
                startActivity(i);
            }
        });
    }
}
