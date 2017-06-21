package com.own.yh.databashpro.Model;

import java.util.Date;

/**
 * Created by yh on 2017/6/18.
 */

public class ReaderModel {
    private String reader_id;
    private String reader_name;
    private String reader_sex;
    private String reader_reg_date;

    public String getReader_id() {
        return reader_id;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getReader_sex() {
        return reader_sex;
    }

    public void setReader_sex(String reader_sex) {
        this.reader_sex = reader_sex;
    }


    public String getReader_reg_date() {
        return reader_reg_date;
    }

    public void setReader_reg_date(String reader_reg_date) {
        this.reader_reg_date = reader_reg_date;
    }
}
