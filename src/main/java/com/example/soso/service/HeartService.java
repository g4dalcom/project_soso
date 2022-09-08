package com.example.soso.service;


import com.example.soso.domain.Heart;
import com.example.soso.domain.Member;
import com.example.soso.domain.Post;
import com.example.soso.domain.UserDetailsImpl;

import com.example.soso.dto.response.ResponseDto;
import com.example.soso.jwt.TokenProvider;


import com.example.soso.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HeartService {


    private final PostRepository postRepository;

    private final TokenProvider tokenProvider;

    //좋아요를 등록함
//    @Transactional
//    public ResponseDto<?> postLike(@PathVariable Long postId,
//                                   @PathVariable Long memberId) {
//
//        //게시글 아이디가 존재하고
//        Post post = isPresentPost(postId);
//        if (null == post) {
//            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
//        }
//
//        Heart heart;
//        //맴버 아이디가 현재 요청 들어온 유저 디테일의 아이디랑 같을때
//        if (heartRepository.findByMemberId(memberId).equals(details.getMember().getId())) {
//            //새로 받은 요청의 게시글 아이디가 기존 게시글 아이디와 같으면
//            if (heartRepository.findByPostId(postId).equals(postId)) {
//                //현재 isLike가 true면 삭제하기
//                heart = null;
//                if (heart.isLike.equals(true)) {
//                    heart.setIsLike(false);
//                    post.dislike();
//                    heartRepository.delete(heart);
//                    return ResponseDto.success("dislike success");
//                }
//                //현재 isLike가 false면 등록하기
//            }
//
//
//        heart = Heart.builder()
//                .post(post)
//                .isLike(true)
//                .build();
//
//        post.like();
//
//        heartRepository.save(heart);
//
//        return ResponseDto.success("like success");
//    }
    //좋아요를 등록함
    @Transactional
    public ResponseDto<?> postLike(@PathVariable Long postId,
                                   HttpServletRequest request) {

        if (null == request.getHeader("RefreshToken")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다refresh.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다author.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        //게시글 아이디가 존재하고
        Post post = isPresentPost(postId);
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }


        post.like();
        postRepository.save(post);
        return ResponseDto.success("좋아요가 정상적으로 반영되었습니다.");
    }
//
    @Transactional(readOnly = true)
    public Post isPresentPost (Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }


    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("RefreshToken"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }




}