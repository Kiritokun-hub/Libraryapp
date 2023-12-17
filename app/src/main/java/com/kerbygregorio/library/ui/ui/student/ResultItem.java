package com.kerbygregorio.library.ui.ui.student;

public class ResultItem {
    private long itemId;
    private String itemName;
    private String itemStudentId;
    private String itemBorrowedBooks;

    public ResultItem(long itemId, String itemName, String itemStudentId, String itemBorrowedBooks) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemStudentId = itemStudentId;
        this.itemBorrowedBooks = itemBorrowedBooks;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemStudentId() {
        return itemStudentId;
    }

    public String getItemBorrowedBooks() {
        return itemBorrowedBooks;
    }
}
