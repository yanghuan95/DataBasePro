package com.own.yh.databashpro.Model;

import java.util.Date;

/**
 * Created by yh on 2017/6/18.
 */

public class BookModel {
    private String book_id;
    private String book_name;
    private String book_author;
    private String book_public;
    private Date book_public_date;
    private Date book_reg_date;
    private boolean isborrowed;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_public() {
        return book_public;
    }

    public void setBook_public(String book_public) {
        this.book_public = book_public;
    }

    public Date getBook_public_date() {
        return book_public_date;
    }

    public void setBook_public_date(Date book_public_date) {
        this.book_public_date = book_public_date;
    }

    public Date getBook_reg_date() {
        return book_reg_date;
    }

    public void setBook_reg_date(Date book_reg_date) {
        this.book_reg_date = book_reg_date;
    }

    public boolean isborrowed() {
        return isborrowed;
    }

    public void setIsborrowed(boolean isborrowed) {
        this.isborrowed = isborrowed;
    }
}
