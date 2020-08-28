package com.example.kmitbuysell;

public class SellingThings {
   private String sname;
public static final SellingThings sthings[]=
        {
                new SellingThings("Books"),
                new SellingThings("Aprons"),
                new SellingThings("Drawing Kits"),
                new SellingThings("Other")
        };
       SellingThings(String sname)
{
    this.sname=sname;
}

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    public String toString()
    {
        return sname;
    }
}
