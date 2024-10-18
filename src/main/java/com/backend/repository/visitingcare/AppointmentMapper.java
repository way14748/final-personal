package com.backend.repository.visitingcare;

import com.backend.dto.visitingcare.Appointment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    // 예약 등록
    int addAppointment(Appointment appointment);
    // 예약 수정
    int updateAppointment(Appointment appointment);
    // 예약 조회
    List<Appointment> getAppointment(String aNum);
    // 모든 예약 조회
    List<Appointment> getAllAppointments();
    // 예약 취소
    int cancelAppointment(String aNum);
    // 나의 모든 예약 조회
    List<Appointment> getMyAppointments(String email);
    // 예약 하고자 하는 시간, 날짜에 예약이 되어 있는지 확인
    int getAppointmentYN(Appointment appointment);
}
