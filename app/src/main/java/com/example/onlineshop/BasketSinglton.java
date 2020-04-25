package com.example.onlineshop;

import java.util.ArrayList;

public class BasketSinglton {

    private static BasketSinglton basketsinglton;
    private  ArrayList<String> mImages = new ArrayList<>();
    private  ArrayList<String> mNameProd = new ArrayList<>();
    private  ArrayList<String> mDescriptionProd = new ArrayList<>();


    public static BasketSinglton getBasketsinglton() {
        if (basketsinglton == null) {
            basketsinglton = new BasketSinglton();
        }


        return basketsinglton;
    }



    public void addmImages(String images){
        mImages.add(images);
    }

    public void addmNameProd(String nameProd){
        mNameProd.add(nameProd);
    }

    public void addmDescriptionProd(String DescriptionProd){
        mDescriptionProd.add(DescriptionProd);
    }

    public ArrayList getmNameProd(){
        return mImages;

    }

    public void clearmImage(int count){
        mImages.remove(count);
    }

    public void clearmNameProd(int count){
        mNameProd.remove(count);
    }

    public void clearmDescriptionProd(int count){
        mDescriptionProd.remove(count);
    }

    public int getSizes(){
        return mNameProd.size();
    }

    public ArrayList getmImages(){
        return mNameProd;
    }

    public ArrayList getmDescriptionProd(){
        return mDescriptionProd;
    }
}
