package com.example.buyandselling;

public class Student {

private String mName;
private String mImageUrl;
private String mSellernumber;
private String mSelleremail;
private String mSelleraddress;
private String Bookdescpt;
private String Bookname;
private String price;
public Student()
{




}




    public Student(String mName, String mImageUrl, String mSellernumber, String mSelleremail, String mSelleraddress, String Bookdescpt, String Bookname, String price)
{if(mName.trim().equals(""))
{mName="No Name";}
   this. mName=mName;
this.mImageUrl=mImageUrl;
this.mSellernumber=mSellernumber;
this.mSelleremail=mSelleremail;
this.mSelleraddress=mSelleraddress;
this.Bookdescpt=Bookdescpt;
this.Bookname = Bookname;
this.price = price;

}

    public String getmName() {
        return mName;
    }
    public String getmImageUrl() {
        return mImageUrl;
    }
    public String getmSellernumber() {
        return mSellernumber;
    }
    public String getmSelleremail() {
        return mSelleremail;
    }
    public String getmSelleraddress() {
        return mSelleraddress;
    }
    public String getBookdescpt() {
        return Bookdescpt;
    }
    public String getBookname() { return Bookname; }
    public String getPrice() { return price; }

    public void setmName(String mName) {
        this.mName = mName;
    }
    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
    public void setmSellernumber(String mSellernumber) { this.mSellernumber=mSellernumber; }
    public void setmSelleremail(String mSelleremail) {
        this.mSelleremail=mSelleremail;
    }
    public void setmSelleraddress(String mSelleraddress) {
        this.mSelleraddress=mSelleraddress;
    }
   public  void setBookdescpt(String Bookdescpt){this.Bookdescpt=Bookdescpt;}
    public void setBookname(String bookname) { Bookname = bookname; }
    public void setPrice(String price) { this.price = price; }
}
