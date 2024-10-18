package com.backend.dto.visitingcare;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommend {
    private int vNum;
    private String vName;
    private int vReview;
    private float vRating;
    private String cSize;
    private String region;
    private String sType;
    private String pName;
    private int pCount;
    private int price;
}
