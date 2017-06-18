package com.own.yh.databashpro.Lab;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.own.yh.databashpro.DataBase.DbSchema;
import com.own.yh.databashpro.DataBase.DbSchema.BookInfoTable;
import com.own.yh.databashpro.Model.BookModel;

import java.util.Date;

/**
 * Created by yh on 2017/6/18.
 */

public class BookCursorWrapper extends CursorWrapper{
    public BookCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public BookModel getBook(){
        String book_id = getString(getColumnIndex(BookInfoTable.Cols.BOOK_ID));
        String book_name = getString(getColumnIndex(BookInfoTable.Cols.BOOK_NAME));
        String book_author = getString(getColumnIndex(BookInfoTable.Cols.BOOK_AUTHOR));
        String book_public = getString(getColumnIndex(BookInfoTable.Cols.BOOK_PUB));
        long book_public_date = getLong(getColumnIndex(BookInfoTable.Cols.BOOK_PUB_DATA));
        long book_reg_date = getLong(getColumnIndex(BookInfoTable.Cols.BOOK_REG_DATE));
        int isborrowed = getInt(getColumnIndex(BookInfoTable.Cols.BOOK_IS_BORROWED));

        BookModel model = new BookModel();
        model.setBook_id(book_id);
        model.setBook_author(book_author);
        model.setBook_name(book_name);
        model.setBook_public(book_public);
        model.setBook_public_date(new Date(book_public_date));
        model.setBook_reg_date(new Date(book_reg_date));
        model.setIsborrowed(isborrowed == 1);

        return model;
    }
}
