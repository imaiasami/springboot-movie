package com.example.movie.repository;

import com.example.movie.model.entity.movie.Movie;
import com.example.movie.model.entity.movie.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieMapper {
    // 모든 영화 리스트
    List<Movie> findAllMovies();

    // 영화에 등록된 모든 리뷰 검색
    List<Review> findReviewsByMovieId(Long movie_id);

    // 영화 검색
    Movie findMovieById(Long movie_id);
    
    // 리뷰 검색
    Review findReview(Review searchReview);

    // 리뷰 등록
    int saveReview(Review review);
    
    // 리뷰 수정
    int updateReview(Review updateReview);
    
    // 리뷰 삭제
    int removeReview(Long review_id);
}
