package com.backend.service.visitingcare;

import com.backend.dto.visitingcare.Vehicle;
import com.backend.repository.visitingcare.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleMapper mapper;
    // 해당 차량 정보 조회
    public Vehicle getVehicle(int vNum){        // 차량 상세 정보 가져오는 메소드
        return mapper.getVehicle(vNum);
    }
    // 해당 차량 리뷰 조회
    public List<Vehicle> getComments(int vNum){ // 차량 넘버에 맞는 리뷰 댓글 보여주는 메소드
        return mapper.getComments(vNum);
    }
    // 모든 차량 조회
    public List<Vehicle> getVehicles() {
        return mapper.getVehicles();
    }
}
