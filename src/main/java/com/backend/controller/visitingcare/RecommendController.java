package com.backend.controller.visitingcare;

import com.backend.dto.visitingcare.Recommend;
import com.backend.exception.ResourceNotFoundException;
import com.backend.service.visitingcare.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/recommends")
    // 로그인되어 있는 회원의 이메일과 선택한 강아지 이름을 파라미터로 조건에 맞는 차량 추천
    public ResponseEntity<Object> recommendVehicle(
            @RequestParam String email, @RequestParam String pName) {
            List<Recommend> recommends
                    = recommendService.recommendVehicle(email, pName);
            // 추천 목록이 존재할때
            if(recommends != null) {
                return ResponseEntity.ok(recommends);
            }else{  // 추천 목록이 존재하지 않을때
                throw new ResourceNotFoundException("추천 차량 정보가 없습니다.");
            }
        }

    @GetMapping("/recommend")
    public ResponseEntity<Object> recommends(
            // 비회원도 조회 할수 있도록 required = false로 설정
            @RequestParam(required = false) String email) {
            // 이메일을 파라미터로 받아 해당이메일에 반려견이 몇마리 등록되어 있는지 확인
            int petCount = recommendService.getPetCount(email);

            if(petCount == 0) { // 등록된 강아지 정보가 없을때
                // 강아지 정보가 없을때 실행되는 메서드
                List<Recommend> recommend =
                        recommendService.recommendNoPet(email);
                return ResponseEntity.ok(recommend);
            }else if (petCount == 1) {  // 강아지가 한마리 등록되어 있을때
                // 해당 email에 등록되어 있는 강아지 정보와 함께 추천 차량 조회
                List<Recommend> recommend =
                        recommendService.recommendOnePet(email);
                return ResponseEntity.ok(recommend);
            }else{  // 강아지가 여러마리일 경우, 차량 추천을 위해
                    // 등록되어 있는 강아지 리스트 전달
                List<Recommend> recommend =
                        recommendService.getPetList(email);
                return ResponseEntity.ok(recommend);
            }

    }


}
