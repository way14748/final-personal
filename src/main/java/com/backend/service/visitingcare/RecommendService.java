package com.backend.service.visitingcare;

import com.backend.dto.visitingcare.Recommend;
import com.backend.repository.visitingcare.RecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private RecommendMapper mapper;     //mapper 연결
    // 차량 추천 하는 메소드 이메일, 강아지 이름 넘겨 받아 서비스타입, 지역, 강아지 사이즈에 맞는 차량 추천 평점순 3개
    public List<Recommend> recommendVehicle(String email, String pName) {
        return mapper.recommendVehicle(email, pName);
    }
    // 해당 이메일에 등록되어 있는 강아지가 한마리 일때, 그 강아지 정보를 기반으로 차량추천
    public List<Recommend> recommendOnePet(String email){
        return mapper.recommendOnePet(email);
    }
    // 해당 이메일에 등록되어 있는 강아지가 없을때, 차량추천(평점순)
    public List<Recommend> recommendNoPet(String email){
        return mapper.recommendNoPet(email);
    }
    // 강아지가 여러마리 등록되어 있을때 강아지 선택하여 차량추천 받을수 있게 등록되어 있는 강아지 목록 제공
    public List<Recommend> getPetList(String email){
        return mapper.getPetList(email);
    }
    // 강아지가 몇마리 등록되어 있는지 확인
    public int getPetCount(String email){
        return mapper.getPetCount(email);
    }
}
