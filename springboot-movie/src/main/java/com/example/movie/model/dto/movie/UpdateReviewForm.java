package com.example.movie.model.dto.movie;

import com.example.movie.model.entity.movie.Review;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateReviewForm {
	@NotNull
	private Long review_id;

    @NotBlank
    private String contents;

    @Range(min = 1, max = 10)
    private int score;
    
    @NotNull
    private Long movie_id;

    public Review toReview(UpdateReviewForm updateReviewForm) {
        Review review = new Review();
        review.setReview_id(updateReviewForm.review_id);
        review.setContents(updateReviewForm.getContents());
        review.setScore(updateReviewForm.getScore());
        review.setMovie_id(updateReviewForm.getMovie_id());
        return review;
    }
}
