package com.example.movie.model.dto.movie;

import com.example.movie.model.entity.movie.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewsDto {
    private Long movie_id;
    private String title;
    private double avg_score;
    private List<Review> reviews;

    public ReviewsDto(Long movie_id, String title, List<Review> reviews) {
        this.movie_id = movie_id;
        this.title = title;
        if (reviews != null && reviews.size() > 0) {
            // 평균 평점 계산하기
            int sum = 0;
            for (Review review : reviews) {
                sum += review.getScore();
            }
            this.avg_score = (double) sum / reviews.size();
            this.reviews = reviews;
        } else {
            this.reviews = new ArrayList<>();
        }
    }
}
