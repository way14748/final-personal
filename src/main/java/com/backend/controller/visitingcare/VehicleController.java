package com.backend.controller.visitingcare;

import com.backend.dto.visitingcare.Vehicle;
import com.backend.exception.ResourceNotFoundException;
import com.backend.service.visitingcare.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/{vNum}")
    // PathVariable로 넘겨받은 값으로 해당 하는 차량 넘버에 맞는 차량정보 제공
    public ResponseEntity<Object> getVehicle(
            @PathVariable int vNum) {   // PathVariable로 경로에서 차량번호 가져옴
            Vehicle vehicle = vehicleService.getVehicle(vNum);
            // 해당 차량의 정보가 있을때
            if (vehicle != null) {
                return ResponseEntity.ok(vehicle);
            } else {    // 해당 차량의 정보가 없을때 글로벌 예외 처리로 넘김
                throw new ResourceNotFoundException("해당 차량의 정보가 없습니다.");
            }
        }


    // 해당 차량 댓글 조회
    @GetMapping("/{vNum}/comments")
    public ResponseEntity<Object> getComments(@PathVariable int vNum) {
        // PathVariable로 차량넘버 받아옴
            List<Vehicle> vehicle = vehicleService.getComments(vNum);
            // 해당 차량의 댓글 정보가 있을시
            if (vehicle != null) {
                return ResponseEntity.ok(vehicle);
            } else {        // 해당 차량의 정보가 없을때 글로벌 예외 처리로 넘김
                throw new ResourceNotFoundException("해당 차량의 리뷰가 없습니다.");
            }
        }

    // 모든 차량 조회
    @GetMapping("/all")
    public ResponseEntity<Object> getVehicles() {
            List<Vehicle> vehicle = vehicleService.getVehicles();
            // 차량 목록이 조회가 되었을때
            if (vehicle != null) {
                return ResponseEntity.ok(vehicle);
            } else {   //차량 목록 조회 실패했을때
                throw new ResourceNotFoundException("차량 목록이 없습니다.");
            }
    }
}
