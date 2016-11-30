package com.carpool.website.model;

import com.carpool.domain.RoomState;
import com.carpool.domain.UserEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Project: Carpool
 * Package: com.carpool.website.model
 * Author:  Novemser
 * 2016/12/1
 */

public class Room implements Serializable {

    private int id;

    @NotEmpty
    private String roomname;
    @NotEmpty
    private String startPoint;
    @NotEmpty
    private String endPoint;

    private int numberLimit;

    private int currentNums;

    private Timestamp createTime;

    private Date startTime;

    private RoomState state;

    private UserEntity host;

    private UserEntity payer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public int getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(int numberLimit) {
        this.numberLimit = numberLimit;
    }

    public int getCurrentNums() {
        return currentNums;
    }

    public void setCurrentNums(int currentNums) {
        this.currentNums = currentNums;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public UserEntity getHost() {
        return host;
    }

    public void setHost(UserEntity host) {
        this.host = host;
    }

    public UserEntity getPayer() {
        return payer;
    }

    public void setPayer(UserEntity payer) {
        this.payer = payer;
    }
}