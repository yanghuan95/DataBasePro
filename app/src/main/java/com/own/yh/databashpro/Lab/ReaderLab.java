package com.own.yh.databashpro.Lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.own.yh.databashpro.DataBase.DbHelper;
import com.own.yh.databashpro.DataBase.DbSchema;
import com.own.yh.databashpro.DataBase.DbSchema.ReaderTable;
import com.own.yh.databashpro.Model.ReaderModel;

import java.util.List;

/**
 * Created by yh on 2017/6/18.
 */

public class ReaderLab {
    private SQLiteDatabase db;

    public ReaderLab(Context context){
        db = new DbHelper(context)
                .getWritableDatabase();
    }

    /*
     *常规的增删改查
     */

    public long addReader(ReaderModel readerModel) {
        ContentValues value = getContentValues(readerModel);

        return db.insert(ReaderTable.NAME, null, value);
    }

    public boolean deleteReader(String id) {
        int result = db.delete(ReaderTable.NAME,
                ReaderTable.Cols.READER_ID + " = ?"
                , new String[]{id});
        return result >= 0;
    }

    //更新
    public int alterReader(ReaderModel readerModel) {
        String id = readerModel.getReader_id();
        ContentValues values = getContentValues(readerModel);

        return db.update(ReaderTable.NAME, values
                , ReaderTable.Cols.READER_ID + " = ?",
                new String[]{id});
    }

    private ReaderCursorWrapper queryReader(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                ReaderTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ReaderCursorWrapper(cursor);
    }

    public ReaderModel getReader(String id) {

        ReaderCursorWrapper cursor = queryReader(
                ReaderTable.Cols.READER_ID + " = ?",
                new String[]{id}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getReader();

        }finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(ReaderModel reader) {
        ContentValues values = new ContentValues();
        values.put(ReaderTable.Cols.READER_ID, reader.getReader_id());
        values.put(ReaderTable.Cols.READER_NAME, reader.getReader_name());
        values.put(ReaderTable.Cols.READER_SEX, reader.getReader_sex());
        values.put(ReaderTable.Cols.REG_DATE, reader.getReader_reg_date());

        return values;
    }
}
