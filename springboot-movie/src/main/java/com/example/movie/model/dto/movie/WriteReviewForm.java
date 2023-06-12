package com.example.movie.model.dto.movie;

import com.example.movie.model.entity.movie.Review;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WriteReviewForm {
    @NotNull
    private Long movie_id;

    @NotBlank
    private String contents;

    @Range(min = 1, max = 10)
    private int score;

    public WriteReviewForm(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Review toReview(WriteReviewForm writeReviewForm) {
        Review review = new Review();
        review.setContents(writeReviewForm.getContents());
        review.setScore(writeReviewForm.getScore());
        review.setMovie_id(writeReviewForm.getMovie_id());
        return review;
    }
}
