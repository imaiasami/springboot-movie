package com.example.movie.model.entity.movie;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Review {
	private Long review_id;
	private String contents;
	private int score;
	private Long movie_id;
	private String member_id;
	private LocalDateTime created_date;	
}
