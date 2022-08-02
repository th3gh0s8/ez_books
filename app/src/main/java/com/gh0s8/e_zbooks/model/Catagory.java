package com.gh0s8.e_zbooks.model;

public class Catagory {


    private String id,name,imagePath;


//    private String name,imagePath;
//    private int id;



    public String getId() {return id; }

//    public int getId() {return id; }

    public void setId(String id) {
        this.id = id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Catagory() {
    }

    public Catagory(String id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

//    public Catagory(int id, String name, String imagePath) {
//        this.id = id;
//        this.name = name;
//        this.imagePath = imagePath;
//    }
}
