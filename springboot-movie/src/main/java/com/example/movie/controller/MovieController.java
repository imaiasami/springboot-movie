package com.example.movie.controller;

import com.example.movie.model.dto.movie.ReviewsDto;
import com.example.movie.model.dto.movie.UpdateReviewForm;
import com.example.movie.model.dto.movie.WriteReviewForm;
import com.example.movie.model.entity.member.Member;
import com.example.movie.model.entity.movie.Movie;
import com.example.movie.model.entity.movie.Review;
import com.example.movie.repository.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("movies")
@Controller
public class MovieController {

    private final MovieMapper movieMapper;

    // 영화 리뷰 페이지 이동
    @GetMapping("reviews/{movie_id}")
    public String reviews(@SessionAttribute Member loginMember,
    					  @PathVariable Long movie_id,
    					  Model model) {
        // 영화 정보 검색
        Movie findMovie = movieMapper.findMovieById(movie_id);
        
        // 리뷰 정보 검색
        List<Review> reviews = movieMapper.findReviewsByMovieId(movie_id);
        
        // 내가 작성한 리뷰 검색
        Review searchReview = new Review();
        searchReview.setMovie_id(movie_id);
        searchReview.setMember_id(loginMember.getMember_id());
        Review myReview = movieMapper.findReview(searchReview);
        
        model.addAttribute("myReview", myReview);

        ReviewsDto reviewsDto = new ReviewsDto(movie_id, findMovie.getTitle(), reviews);
        model.addAttribute("reviewsDto", reviewsDto);

        return "movies/reviews";
    }

    // 리뷰 작성 페이지 이동
    @GetMapping("writeReview")
    public String writeReviewForm(@SessionAttribute Member loginMember,
                                  @RequestParam Long movie_id,
                                  Model model) {
        log.info("movie_id: {}", movie_id);
        Movie movie = movieMapper.findMovieById(movie_id);
        model.addAttribute("movie", movie);

        model.addAttribute("writeReview", new WriteReviewForm(movie_id));

        return "movies/writeReviewForm";
    }

    // 리뷰 작성
    @PostMapping("writeReview")
    public String writeReview(@SessionAttribute Member loginMember,
                              @RequestParam Long movie_id,
                              @Validated @ModelAttribute("writeReview") WriteReviewForm writeReviewForm,
                              BindingResult result,
                              Model model) {
        log.info("review: {}", writeReviewForm);

        Movie movie = movieMapper.findMovieById(movie_id);
        model.addAttribute("movie", movie);

        if (result.hasErrors()) {
            return "movies/writeReviewForm";
        }
        
        // 이미 등록된 리뷰가 있는지 체크
        Review searchReview = new Review();
        searchReview.setMovie_id(movie_id);
        searchReview.setMember_id(loginMember.getMember_id());
        Review findReview = movieMapper.findReview(searchReview);
        if (findReview != null) {
        	return "redirect:/";
        }

        // 리뷰 등록
        Review review = writeReviewForm.toReview(writeReviewForm);
        log.info("review model: {}", review);
        review.setMember_id(loginMember.getMember_id());
        movieMapper.saveReview(review);

        return "redirect:/movies/reviews/" + writeReviewForm.getMovie_id();
    }
    
    // 리뷰 수정 페이지 이동
    @GetMapping("updateReview")
    public String updateReviewForm(@SessionAttribute Member loginMember,
    							   @RequestParam Long review_id,
    							   Model model) {
    	
    	// 작성한 리뷰 정보 가져오기
        Review searchReview = new Review();
        searchReview.setReview_id(review_id);
        searchReview.setMember_id(loginMember.getMember_id());
    	Review review = movieMapper.findReview(searchReview);
    	
    	UpdateReviewForm updateReview = new UpdateReviewForm();
    	updateReview.setReview_id(review.getReview_id());
    	updateReview.setContents(review.getContents());
    	updateReview.setScore(review.getScore());
    	updateReview.setMovie_id(review.getMovie_id());
    	    	
    	model.addAttribute("updateReview", updateReview);
    	
    	// 영화 정보 가져오기
    	Movie movie = movieMapper.findMovieById(review.getMovie_id());
        model.addAttribute("movie", movie);
    	
    	return "movies/updateReviewForm";
    }
    
    // 리뷰 수정
    @PostMapping("updateReview")
    public String updateReview(@SessionAttribute Member loginMember,
    						   @Validated @ModelAttribute UpdateReviewForm updateReview,
    						   BindingResult result) {
    	log.info("updateReview: {}", updateReview);
    	
    	// 리뷰 수정 권한 확인
    	Review searchReview = new Review();
    	searchReview.setReview_id(updateReview.getReview_id());
    	searchReview.setMember_id(loginMember.getMember_id());
    	Review findReview = movieMapper.findReview(searchReview); 
    	if (findReview == null) {
    		return "redirect:/";
    	}
    	
    	movieMapper.updateReview(updateReview.toReview(updateReview));
    	
    	return "redirect:/movies/reviews/" + updateReview.getMovie_id();
    }
    
    // 리뷰 삭제
    @GetMapping("removeReview")
    public String removeReview(@SessionAttribute Member loginMember,
    						   @RequestParam Long review_id) {
    	
    	// 리뷰 삭제 권한 확인
    	Review searchReview = new Review();
    	searchReview.setReview_id(review_id);
    	searchReview.setMember_id(loginMember.getMember_id());
    	Review findReview = movieMapper.findReview(searchReview); 
    	if (findReview == null) {
    		return "redirect:/";
    	}
    	
    	movieMapper.removeReview(review_id);
    	
    	return "redirect:/";
    }

}
