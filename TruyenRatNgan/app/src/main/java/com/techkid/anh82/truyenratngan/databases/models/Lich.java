package com.techkid.anh82.truyenratngan.databases.models;

/**
 * Created by anh82 on 4/22/2017.
 */

public class Lich {
    String name ;

    @Override
    public String toString() {
        return  "Tran Dau \n" +name +
                "\n Dien Ra Vao Luc    \n" + time  + "" ;

    }

    String time;
    String image;

    public Lich(String name, String time, String image) {
        this.name = name;
        this.time = time;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

}
