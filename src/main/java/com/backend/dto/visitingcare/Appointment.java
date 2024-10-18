package com.backend.dto.visitingcare;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Appointment {
    private String aNum;
    private String email;
    private int pNum;
    private int vNum;
    private Date aDate;
    private int price;
    private String cDate;
    private int cTime;
    private String status;
    private String aName;
    private String aAddress;
    private String aPhone;
    private String pName;
    private String adAddress;
    private String roadCode;
    private int rRating;
    private String comments;
    private String vName;
    private String sType;
    private int rNum;
    private String cancelYN;
}
