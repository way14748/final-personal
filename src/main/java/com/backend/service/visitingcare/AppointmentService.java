package com.backend.service.visitingcare;

import com.backend.dto.visitingcare.Appointment;
import com.backend.exception.InvalidRequestException;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.visitingcare.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentMapper mapper;
    // 예약 추가
    public int addAppointment(Appointment appointment) {
        if (getAppointmentYN(appointment) != 0) {
            throw new InvalidRequestException("이미 예약이 되어 있습니다.");
        } else {
            return mapper.addAppointment(appointment);
        }
    }
    // 예약 수정
    public int updateAppointment(Appointment appointment) {     //예약 수정
        if (getAppointmentYN(appointment) != 0) {
            throw new InvalidRequestException("이미 예약이 되어 있습니다.");
        } else {
            return mapper.updateAppointment(appointment);
        }
    }
    // 예약 삭제
    public int cancelAppointment(String aNum) {
        List<Appointment> result = mapper.getAppointment(aNum);
        if(result == null){
            throw new ResourceNotFoundException("예약을 찾을수 없습니다.");
        }else {
            return mapper.cancelAppointment(aNum);
        }
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
