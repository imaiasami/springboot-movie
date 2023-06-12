package com.example.movie.controller;

import com.example.movie.model.entity.movie.Movie;
import com.example.movie.repository.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MovieMapper movieMapper;

    @GetMapping("/")
    public String home(Model model) {
        List<Movie> movies = movieMapper.findAllMovies();
        model.addAttribute("movies", movies);
        return "index";
    }
}
