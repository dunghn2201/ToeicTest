package com.dunghn.toeictest.model;

import android.graphics.Bitmap;

public class Part {
    private String Stt;
    private String namePart;
    private Bitmap pic;

    public String getStt() {
        return Stt;
    }

    public void setStt(String stt) {
        Stt = stt;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }
}
