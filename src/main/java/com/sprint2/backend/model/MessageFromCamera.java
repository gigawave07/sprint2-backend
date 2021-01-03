package com.sprint2.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MessageFromCamera {
    private long id;
    private String srcImg="";
    private String plateNumber="";
    private LocalDateTime expirationDate;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageFromCamera{" +
                "id=" + id +
                ", srcImg='" + srcImg + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", message='" + message + '\'' +
                '}';
    }
}
