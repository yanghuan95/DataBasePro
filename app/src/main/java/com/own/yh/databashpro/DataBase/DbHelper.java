package com.own.yh.databashpro.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.own.yh.databashpro.DataBase.DbSchema.BookInfoTable;
import com.own.yh.databashpro.DataBase.DbSchema.BorrowRecordTable;
import com.own.yh.databashpro.DataBase.DbSchema.ReaderTable;

/**
 * Created by yh on 2017/6/18.
 */

public class DbHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "manager.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL("create table " + ReaderTable.NAME + "("
                + ReaderTable.Cols.READER_ID + " primary key, "
                + ReaderTable.Cols.READER_NAME + " NOT NULL, "
                + ReaderTable.Cols.READER_SEX + " NOT NULL, "
                + ReaderTable.Cols.REG_DATE + " NOT NULL" + ")");
        db.execSQL("create table " + BookInfoTable.NAME + "("
                + BookInfoTable.Cols.BOOK_ID + " primary key, "
                + BookInfoTable.Cols.BOOK_NAME + " NOT NULL, "
                + BookInfoTable.Cols.BOOK_AUTHOR + " NOT NULL, "
                + BookInfoTable.Cols.BOOK_PUB + " NOT NULL, "
                + BookInfoTable.Cols.BOOK_PUB_DATA + " NOT NULL, "
                + BookInfoTable.Cols.BOOK_REG_DATE + " NOT NULL, "
                + BookInfoTable.Cols.BOOK_IS_BORROWED + " NOT NULL)");
        db.execSQL("create table " + BorrowRecordTable.NAME
                + "("
                        + BorrowRecordTable.Cols.READER_ID + ", "
                        + BorrowRecordTable.Cols.BOOK_ID + ", "
                        + BorrowRecordTable.Cols.BORROWED_DATE + " DATE, "
                        + "FOREIGN KEY (" + BorrowRecordTable.Cols.READER_ID + ") REFERENCES "
                                + ReaderTable.NAME + " (" + ReaderTable.Cols.READER_ID + ") "
                        + "FOREIGN KEY (" + BorrowRecordTable.Cols.BOOK_ID + ") REFERENCES "
                                + BookInfoTable.NAME + " (" + BookInfoTable.Cols.BOOK_ID + ")"
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
