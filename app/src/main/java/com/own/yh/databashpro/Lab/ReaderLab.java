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
    private Context mContext;
    private SQLiteDatabase db;

    public ReaderLab(Context context){
        mContext = context.getApplicationContext();
        db = new DbHelper(mContext)
                .getWritableDatabase();
    }

    /*
     *常规的增删改查
     */

    public void addReader(ReaderModel readerModel) {
        ContentValues value = getContentValues(readerModel);

        db.insert(ReaderTable.NAME, null, value);
    }

    public void deleteReader(String id) {
        db.delete(ReaderTable.NAME,
                ReaderTable.Cols.READER_ID + " = ?"
                , new String[]{id});
    }

    //更新
    public void alterReader(ReaderModel readerModel) {
        String id = readerModel.getReader_id();
        ContentValues values = getContentValues(readerModel);

        db.update(ReaderTable.NAME, values
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
        values.put(ReaderTable.Cols.REG_DATE, reader.getReader_reg_date().getTime());

        return values;
    }
}
