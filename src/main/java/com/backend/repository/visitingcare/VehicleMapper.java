package com.backend.repository.visitingcare;

import com.backend.dto.visitingcare.Vehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    // 해당 차량넘버에 맞는 차량조회
    Vehicle getVehicle(int vNum);
    // 해당 차량넘버에 등록되어 있는 댓글(리뷰)조회
    List<Vehicle> getComments(int vNum);
    // 모든 차량 조회
    List<Vehicle> getVehicles();
    }
