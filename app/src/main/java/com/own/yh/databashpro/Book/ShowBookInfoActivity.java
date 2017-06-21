package com.own.yh.databashpro.Book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.own.yh.databashpro.Lab.BookLab;
import com.own.yh.databashpro.Model.BookModel;
import com.own.yh.databashpro.R;

/**
 * Created by yh on 2017/6/18.
 */

public class ShowBookInfoActivity extends AppCompatActivity {

    private final static String ARGS = "ARGS";

    private TextView showBookID;
    private TextView showBookName;
    private TextView showBookAuthor;
    private TextView showBookPublic;
    private TextView showBookPublicDate;
    private TextView showBookRegDate;
    private TextView showIsBorrowed;

    public static Intent newIntent(Context context, String args) {
        Intent intent = new Intent(context, ShowBookInfoActivity.class);
        intent.putExtra(ARGS, args);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_book_layout);

        showBookID = (TextView) findViewById(R.id.show_book_id_view);
        showBookName = (TextView) findViewById(R.id.show_book_name_view);
        showBookAuthor = (TextView) findViewById(R.id.show_book_author_view);
        showBookPublic = (TextView) findViewById(R.id.show_book_public_view);
        showBookPublicDate = (TextView) findViewById(R.id.show_book_public_date_view);
        showBookRegDate = (TextView) findViewById(R.id.show_book_reg_date_view);
        showIsBorrowed = (TextView) findViewById(R.id.show_book_is_borrowed_view);

        String id = getIntent().getStringExtra(ARGS);
        if (!id.equals("")) {
            BookLab lab = new BookLab(ShowBookInfoActivity.this);
            BookModel book = lab.getBook(id);
            if (book != null) {
                showBookID.setText(book.getBook_id());
                showBookName.setText(book.getBook_name());
                showBookAuthor.setText(book.getBook_author());
                showBookPublic.setText(book.getBook_public());
                showBookPublicDate.setText(book.getBook_public_date());
                showIsBorrowed.setText(book.isborrowed() ? "是" : "否");
                showBookRegDate.setText(book.getBook_reg_date());
            } else {
                Toast.makeText(ShowBookInfoActivity.this, "can't query this book "
                        + id, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
