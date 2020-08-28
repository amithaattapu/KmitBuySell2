package com.example.kmitbuysell;

public class Book {
    String imageURL;
    String bdesc;
    String bookName;
    String price;
    String branch;
    String year,type;
    String uid;
    Book()
    {

    }
    Book(String imageURL,String bookName,String price,String branch,String bdesc,String type,String uid,String year)
    {
        this.bookName=bookName;
        this.imageURL=imageURL;
        this.price=price;
        this.year=year;
        this.branch=branch;
        this.bdesc=bdesc;
        this.type=type;
        this.uid=uid;
    }

    public String getType() {
        return type;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBdesc() {
        return bdesc;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;}
}
