package com.backend.repository.visitingcare;

import com.backend.dto.visitingcare.Recommend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
    // 이메일과 반려견 이름 입력받아 차량 추천
    List<Recommend> recommendVehicle(String email, String pName);
    // 해당 이메일에 등록된 반려견 정보 기반 차량 추천
    List<Recommend> recommendOnePet(String email);
    // 등록되어 있는 강아지가 없을때 차량 추천
    List<Recommend> recommendNoPet(String email);
    // 등록되어 있는 반려견이 여러마리일때 반려견 목록 제공
    List<Recommend> getPetList(String email);
    // 반려견 등록이 몇마리 되어있는지 확인
    int getPetCount(String email);
 }
