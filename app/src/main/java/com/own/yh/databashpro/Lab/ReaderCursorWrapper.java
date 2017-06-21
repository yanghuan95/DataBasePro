package com.own.yh.databashpro.Lab;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.own.yh.databashpro.DataBase.DbSchema;
import com.own.yh.databashpro.DataBase.DbSchema.ReaderTable;
import com.own.yh.databashpro.Model.ReaderModel;
import com.own.yh.databashpro.R;

import java.util.Date;

/**
 * Created by yh on 2017/6/18.
 */

public class ReaderCursorWrapper extends CursorWrapper{
    public ReaderCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ReaderModel getReader(){
        String reader_id = getString(getColumnIndex(ReaderTable.Cols.READER_ID));
        String reader_name = getString(getColumnIndex(ReaderTable.Cols.READER_NAME));
        String reader_sex = getString(getColumnIndex(ReaderTable.Cols.READER_SEX));
        String reg_date = getString(getColumnIndex(ReaderTable.Cols.REG_DATE));

        ReaderModel reader = new ReaderModel();
        reader.setReader_id(reader_id);
        reader.setReader_name(reader_name);
        reader.setReader_reg_date(reg_date);
        reader.setReader_sex(reader_sex);

        return reader;
    }
}
