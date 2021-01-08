package com.sprint2.backend.entity;

public class Image {
    private long id;
    private String src;
    public long getId(){
        return this.id;
    }
    public String getSrc(){
        return this.src;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setSrc(String src){
        this.src = src;
    }
}
