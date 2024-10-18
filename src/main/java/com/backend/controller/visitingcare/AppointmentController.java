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
    public  ResponseEntity<Object> addAppointment(
            //form-data로 예약 내역을 Appointment DTO에 담아서 받음
            @RequestBody Appointment appointment) {
                    // 해당날짜와 시간에 차량이 예약이 가능한지 판단하는 메서드 (0 = 예약가능, 1 = 예약불가)
                 int result = appointmentService.getAppointmentYN(appointment);

                 if (result == 0) {
                     int able = appointmentService.addAppointment(appointment);
                     return ResponseEntity.ok(able);
                 } else {   // 예약 불가시 글로벌 예외 핸들러에 예외 처리
                     throw new InvalidRequestException("해당 시간에 예약이 되어있습니다.");
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
            int result =
                    appointmentService.cancelAppointment(aNum);
            if (result == 0) {  // 삭제 결과가 0일때
                throw new ResourceNotFoundException("삭제 실패");
            } else {    // 삭제 결과가 0이 아닐때
                return ResponseEntity.ok("삭제 완료");
            }
    }
    // 예약 수정
    @PutMapping("/appointment/update")
    public ResponseEntity<Object> updateAppointment(
            @RequestBody Appointment appointment) { //form-data로 appointment에 담아 정보를 받아옴
            // 해당시간, 날짜에 해당차량이 예약이 되어 있는지 확인
            int aCount = appointmentService.getAppointmentYN(appointment);

            if (aCount == 0) {  // 예약이 안되어 있을때
                int able = appointmentService.updateAppointment(appointment);
                return ResponseEntity.ok(able);
            } else {    // 예약이 되어있을때 글로벌 예외처리
                throw new InvalidRequestException("해당 시간에 예약이 되어있습니다.");
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