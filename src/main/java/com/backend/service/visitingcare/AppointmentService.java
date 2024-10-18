package com.backend.service.visitingcare;

import com.backend.dto.visitingcare.Appointment;
import com.backend.repository.visitingcare.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentMapper mapper;
    // 예약 추가
    public int addAppointment(@RequestBody Appointment appointment) {      // 예약 추가하는 메소드
        return mapper.addAppointment(appointment);
    }
    // 예약 수정
    public int updateAppointment(Appointment appointment) {     //예약 수정
        return mapper.updateAppointment(appointment);
    }
    // 예약 삭제
    public int cancelAppointment(String aNum) {
        return mapper.cancelAppointment(aNum);
    }
    // 예약 목록 조회
    public List<Appointment> getAllAppointments() {             // 예약 리스트
        return mapper.getAllAppointments();
    }
    // 예약 상세 조회
    public List<Appointment> getAppointment(String aNum) {      // 예약 상세 보기
        return mapper.getAppointment(aNum);
    }
    // 내 예약 조회
    public List<Appointment> getMyAppointments(String email) {
        return mapper.getMyAppointments(email);
    }
    // 예약 중복 감지
    public int getAppointmentYN(Appointment appointment) {
        return mapper.getAppointmentYN(appointment);
    }
}
