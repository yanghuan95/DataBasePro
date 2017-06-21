package com.own.yh.databashpro.Lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.own.yh.databashpro.BorrowBook.BorrowBookCursorWrapper;
import com.own.yh.databashpro.DataBase.DbHelper;
import com.own.yh.databashpro.DataBase.DbSchema;
import com.own.yh.databashpro.DataBase.DbSchema.BorrowRecordTable;

import java.util.Date;

/**
 * Created by yh on 2017/6/21.
 */

public class
BorrowLab {

    private SQLiteDatabase db;

    public BorrowLab(Context context) {
        db = new DbHelper(context)
                .getWritableDatabase();
    }

    public long addBorrowBookInfo(String readerId, String bookId) {
        return db.insert(BorrowRecordTable.NAME, null
                , getContentValues(readerId, bookId));
    }

    public int deleteBorrowBookInfo(String readerId, String bookId) {
       return db.delete(BorrowRecordTable.NAME, BorrowRecordTable.Cols.READER_ID + " = ? " + "AND "
                       + BorrowRecordTable.Cols.BOOK_ID + " = ? ",
               new String[]{readerId, bookId});
    }

    public BorrowBookCursorWrapper queryBorrowBookInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                BorrowRecordTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new BorrowBookCursorWrapper(cursor);
    }

    public String[] getBorrowInfo(String readerId, String bookId) {

        BorrowBookCursorWrapper cursor = queryBorrowBookInfo(
                BorrowRecordTable.Cols.READER_ID + " = ? " + "AND "
                        + BorrowRecordTable.Cols.BOOK_ID + " = ? ",
                new String[]{readerId, bookId}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getRecord();

        }finally {
            cursor.close();
        }
    }

    public ContentValues getContentValues(String readerId, String bookId) {
        Date date = new Date();

        ContentValues values = new ContentValues();
        values.put(BorrowRecordTable.Cols.BOOK_ID, bookId);
        values.put(BorrowRecordTable.Cols.READER_ID, readerId);
        values.put(BorrowRecordTable.Cols.BORROWED_DATE, date.getTime());

        return values;
    }
}
