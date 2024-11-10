package com.backend.controller.visitingcare;

import com.backend.dto.visitingcare.Appointment;
import com.backend.exception.InvalidRequestException;
import com.backend.exception.ResourceNotFoundException;
import com.backend.service.visitingcare.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/service")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // 예약 등록
    @PostMapping("/appointment")
    public ResponseEntity<Object> addAppointment(
            //form-data로 예약 내역을 Appointment DTO에 담아서 받음
            @RequestBody Appointment appointment) {
        try {
            int result = appointmentService.addAppointment(appointment);
            return ResponseEntity.ok(result);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 예약 조회
    @GetMapping("/appointment/{aNum}")
    public ResponseEntity<Object> getAppointment(
            @PathVariable String aNum) {    // PathVariable로 경로에 있는 예약번호 가져옴
            List<Appointment> result = appointmentService.getAppointment(aNum);
            // 예약 조회결과가 없을때 글로벌 예외 처리
            if (result == null || result.isEmpty() ) {
                throw new ResourceNotFoundException("해당 예약을 찾을 수 없습니다.");
            }else{  // 조회 결과가 있을때 결과 제공
            return ResponseEntity.ok(result);
        }

    }
    // 예약 목록 조회
    @GetMapping("/appointment/list")
    public ResponseEntity<Object> getAllAppointments() {
        List<Appointment> appointment =
                appointmentService.getAllAppointments();
        // 예약 목록이 존재할때
        if (appointment != null && !appointment.isEmpty()) {
            return ResponseEntity.ok(appointment);
        } else {
            // 예약 목록이 존재 하지 않을때 글로벌 예외 처리
            throw new ResourceNotFoundException("예약목록을 찾을 수 없습니다.");
        }
    }
    // 예약 삭제
    @PutMapping("/appointment/delete/{aNum}")
    public ResponseEntity<Object> cancelAppointment(
            @PathVariable String aNum) {    //PathVariable로 예약번호 받아옴
            int result = appointmentService.cancelAppointment(aNum);
            if (result == 1) {
                return ResponseEntity.ok("예약 삭제 완료");
            }else{
                return ResponseEntity.badRequest().body("삭제 실패");
            }
    }
    // 예약 수정
    @PutMapping("/appointment/update")
    public ResponseEntity<Object> updateAppointment(
            @RequestBody Appointment appointment) {
            try {
                int result = appointmentService.updateAppointment(appointment);
                return ResponseEntity.ok(result);
            } catch (InvalidRequestException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    // 나의 모든 예약 조회
    @GetMapping("/myAppointments")
    public ResponseEntity<Object> getMyAppointments(
            @RequestParam String email) {
        List<Appointment> appointments
                = appointmentService.getMyAppointments(email);
        // 예약내역이 존재할때
        if (appointments != null && !appointments.isEmpty()) {
            return ResponseEntity.ok(appointments);
        } else {    //예약 내역이 존재하지 않을시
            throw new ResourceNotFoundException("예약이 없습니다.");
        }
    }
}