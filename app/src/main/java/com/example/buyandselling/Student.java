package com.example.buyandselling;

public class Student {

private String mName;
private String mImageUrl;
public Student()
{




}
public Student(String mName,String mImageUrl)
{if(mName.trim().equals(""))
{mName="No Name";}
   this. mName=mName;
this.mImageUrl=mImageUrl;

}

    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
