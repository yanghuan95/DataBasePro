package com.own.yh.databashpro.BorrowBook;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.own.yh.databashpro.Lab.BookLab;
import com.own.yh.databashpro.Lab.BorrowLab;
import com.own.yh.databashpro.Lab.ReaderLab;
import com.own.yh.databashpro.Model.BookModel;
import com.own.yh.databashpro.Model.ReaderModel;
import com.own.yh.databashpro.R;
import com.own.yh.databashpro.ShowMainActivity;

/**
 * Created by yh on 2017/6/21.
 */

public class ShowBorrowInfoActivity extends AppCompatActivity{

    private final static String READER_ID = "reader";
    private final static String BOOK_ID = "book";

    private String reader_id;
    private String book_id;
    private TextView show_reader_id;
    private TextView show_book_id;
    private TextView show_reader_name;
    private TextView show_book_name;
    private TextView show_borrow_time;

    public static Intent newIntent(Context context, String reader_id, String book_id) {
        Intent i = new Intent(context, ShowBorrowInfoActivity.class);
        i.putExtra(READER_ID, reader_id);
        i.putExtra(BOOK_ID, book_id);

        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_borrow_book_layout);

        reader_id = getIntent().getStringExtra(READER_ID);
        book_id = getIntent().getStringExtra(BOOK_ID);

        show_book_id = (TextView) findViewById(R.id.show_borrow_book_id_view);
        show_book_name = (TextView) findViewById(R.id.show_book_borrow_name_view);
        show_reader_id = (TextView) findViewById(R.id.show_borrow_reader_id_view);
        show_reader_name = (TextView) findViewById(R.id.show_reader_borrow_name_view);
        show_borrow_time = (TextView) findViewById(R.id.show_borrow_time_view);

        String[] qery = new BorrowLab(ShowBorrowInfoActivity.this)
                .getBorrowInfo(reader_id, book_id);
        if (qery != null) {
            ReaderModel reader = new ReaderLab(ShowBorrowInfoActivity.this)
                    .getReader(reader_id);
            BookLab lab = new BookLab(ShowBorrowInfoActivity.this);
            BookModel book = lab.getBook(book_id);

            show_book_id.setText(book_id);
            show_book_name.setText(book.getBook_name());
            show_borrow_time.setText(qery[2]);
            show_reader_name.setText(reader.getReader_name());
            show_reader_id.setText(reader_id);

            book.setIsborrowed(true);
            lab.alterBook(book);

        } else {
            Toast.makeText(ShowBorrowInfoActivity.this,
                    "can't query the borrow info"
                    , Toast.LENGTH_SHORT).show();
        }
    }
}
