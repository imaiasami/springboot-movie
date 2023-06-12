package com.example.movie.repository;

import com.example.movie.model.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	// 회원가입
	int joinMember(Member member);
	
	// 회원정보 검색(로그인)
	Member findMemberById(String member_id);
}
