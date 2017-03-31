package com.yude.auctionhelp.entity.response;

import java.util.List;

/**
 * Created by hexiang on 17/3/27.
 */
public class TestComplete {

    private String photoUil;
    private  String editText; // 描述文字
    private  String keTing;   // 客厅
    private   String luYing;
    private List <String>  listPhoto;

    public String getKeTing() {
        return keTing;
    }

    public void setKeTing(String keTing) {
        this.keTing = keTing;
    }

    public String getLuYing() {
        return luYing;
    }

    public void setLuYing(String luYing) {
        this.luYing = luYing;
    }

    public List<String> getListPhoto() {
        return listPhoto;
    }

    public void setListPhoto(List<String> listPhoto) {
        this.listPhoto = listPhoto;
    }




    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public String getPhotoUil() {

        return photoUil;
    }

    public void setPhotoUil(String photoUil) {
        this.photoUil = photoUil;
    }



}
