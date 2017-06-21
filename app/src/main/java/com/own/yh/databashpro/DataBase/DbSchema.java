package com.own.yh.databashpro.DataBase;

/**
 * Created by yh on 2017/6/18.
 */

public class DbSchema {

   public static final class ReaderTable{
       public static final String NAME = "reader_table";

       public static final class Cols {
           public static final String READER_ID = "reader_id";
           public static final String READER_NAME = "reader_name";
           public static final String READER_SEX = "reader_sex";
           public static final String REG_DATE = "reg_date";    //登记日期
       }

   }

   public static final class BookInfoTable{
       public static final String NAME = "book_info_table";


       public static final class Cols {
           public static final String BOOK_ID = "book_id";
           public static final String BOOK_NAME = "book_name";
           public static final String BOOK_AUTHOR = "book_author";
           public static final String BOOK_PUB = "book_public"; //出版社名称
           public static final String BOOK_PUB_DATA = "book_public_date";   //出版日期
           public static final String BOOK_REG_DATE = "book_reg_date";  //等级日期
           public static final String BOOK_IS_BORROWED = "is_borrowed";     //是否被借出
       }

   }

   //借阅记录
    public static final class BorrowRecordTable {
        public static final String NAME = "borrow_record_table";

        public static final class Cols {
            public static final String READER_ID = "reader_id";
            public static final String BOOK_ID = "book_id";
            public static final String BORROWED_DATE = "borrowed_date";
        }

    }
}
