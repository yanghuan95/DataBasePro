package com.own.yh.databashpro.BorrowBook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;

import com.own.yh.databashpro.DataBase.DbSchema;

import static com.own.yh.databashpro.DataBase.DbSchema.*;

/**
 * Created by yh on 2017/6/21.
 */

public class BorrowBookCursorWrapper extends CursorWrapper{


    public BorrowBookCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public String[] getRecord() {
        String reader_id = getString(getColumnIndex(BorrowRecordTable.Cols.BOOK_ID));
        String book_id = getString(getColumnIndex(BorrowRecordTable.Cols.READER_ID));
        Long borrow_date = getLong(getColumnIndex(BorrowRecordTable.Cols.READER_ID));
        String[] result = new String[3];
        result[0] = reader_id;
        result[1] = book_id;
        result[2] = borrow_date.toString();

        return result;
    }
}
