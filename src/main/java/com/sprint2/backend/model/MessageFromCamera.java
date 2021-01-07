package com.sprint2.backend.model;

import java.time.LocalDateTime;

public class MessageFromCamera {
    private long id;
    private String srcImg="";
    private String plateNumber="";
    private LocalDateTime expirationDate;
    private String message;
    private Boolean status=false;
    private LocalDateTime entryLog;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getEntryLog() {
        return entryLog;
    }

    public void setEntryLog(LocalDateTime entryLog) {
        this.entryLog = entryLog;
    }
}
