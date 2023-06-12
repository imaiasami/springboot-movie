package com.example.movie.model.dto.member;

import com.example.movie.model.entity.member.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JoinMemberForm {
    @Size(min = 4, max = 20)
    private String member_id;
    @Size(min = 4, max = 20)
    private String password;
    @NotBlank
    private String name;

    public Member toMember(JoinMemberForm joinMemberForm) {
        return new Member(member_id, password, name);
    }
}
