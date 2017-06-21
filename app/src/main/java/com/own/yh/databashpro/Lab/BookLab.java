package com.own.yh.databashpro.Lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.own.yh.databashpro.DataBase.DbHelper;
import com.own.yh.databashpro.DataBase.DbSchema;
import com.own.yh.databashpro.DataBase.DbSchema.BookInfoTable;
import com.own.yh.databashpro.Model.BookModel;

import java.util.List;

/**
 * Created by yh on 2017/6/18.
 */

public class BookLab {
    private SQLiteDatabase db;

    public BookLab(Context context) {
        db = new DbHelper(context)
                .getWritableDatabase();
    }

    /*
     *常规的增删改查
     */
    public void addBook(BookModel bookModel) {
        ContentValues values = getContentValues(bookModel);

        db.insert(BookInfoTable.NAME, null, values);
    }

    public void deleteBook(String id) {
        db.delete(BookInfoTable.NAME,
                BookInfoTable.Cols.BOOK_ID + " = ?"
                , new String[]{id});
    }

    public void alterBook(BookModel bookModel) {
        String id = bookModel.getBook_id();

        ContentValues values = getContentValues(bookModel);

        db.update(BookInfoTable.NAME, values,
                BookInfoTable.Cols.BOOK_ID + " = ?"
                , new String[]{id});

    }

    private BookCursorWrapper queryBook(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                BookInfoTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );

        return new BookCursorWrapper(cursor);
    }

    public BookModel getBook(String id) {
        BookCursorWrapper cursorWrapper = queryBook(
                BookInfoTable.Cols.BOOK_NAME + " = ?",
                new String[] {id}
        );

        try{
            if(cursorWrapper.getCount() == 0)
                return null;

            cursorWrapper.moveToFirst();
            return cursorWrapper.getBook();
        }finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues(BookModel model) {
        ContentValues values = new ContentValues();
        values.put(BookInfoTable.Cols.BOOK_ID, model.getBook_id());
        values.put(BookInfoTable.Cols.BOOK_AUTHOR, model.getBook_author());
        values.put(BookInfoTable.Cols.BOOK_NAME, model.getBook_name());
        values.put(BookInfoTable.Cols.BOOK_PUB, model.getBook_public());
        values.put(BookInfoTable.Cols.BOOK_PUB_DATA, model.getBook_public_date());
        values.put(BookInfoTable.Cols.BOOK_REG_DATE, model.getBook_reg_date());
        values.put(BookInfoTable.Cols.BOOK_IS_BORROWED, model.isborrowed() ? 1:0);

        return values;

    }
}
