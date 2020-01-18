package com.example.buyandselling;

public class Student {

private String mName;
private String mImageUrl;
public Student()
{




}
public Student(String name,String url)
{if(name.trim().equals(""))
{name="No Name";}
    mName=name;
mImageUrl=url;

}

    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmName(String Name) {
        mName = Name;
    }

    public void setmImageUrl(String url) {
        mImageUrl = url;
    }
}
