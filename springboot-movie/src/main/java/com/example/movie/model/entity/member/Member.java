package com.example.movie.model.entity.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Member {
	private String member_id;
	private String password;
	private String name;
}
