package com.example.soso.repository;

import com.example.soso.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface HeartRepository extends JpaRepository<Heart, Long> {
//    Optional<Heart> findByPostId(Long postId);
    Optional<Heart> findByIdAndNickname(Long Id , String nickname);
    List<Heart> findAllByRequestId(Long RequestID);
//    Optional<Heart> findByMemberId(Long memberId);
}
