package com.example.soso.Controller;


import com.example.soso.domain.UserDetailsImpl;
import com.example.soso.dto.response.ResponseDto;
import com.example.soso.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class HeartController {
    private final HeartService heartService;

//
//    @PostMapping(value = {"/api/auth/like/{id}", "/api/auth/like"})
//    public ResponseDto<?> postLike(@PathVariable Long id,
//                                   @AuthenticationPrincipal UserDetailsImpl details) {
//        return heartService.postLike(id, details);
//    }
    @PostMapping(value = {"/api/auth/like/{id}", "/api/auth/like"})
    public ResponseDto<?> postLike(@PathVariable Long id,
                                   HttpServletRequest request) {
        return heartService.postLike(id, request);
    }
}