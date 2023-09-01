package com.javaunit3.springmvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MovieController {

    @Autowired
    private BestMovieService bestMovieService; //create bestMovieService object to access information from BestMovieService

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping("/bestMovie")
    public String getBestMoviePage(Model model) {
        model.addAttribute("BestMovie", bestMovieService.getBestMovie().getTitle());
        return "bestMovie";
    }
    /*create a new method with the request mapping of “voteForBestMovieForm”. This method will simply return the String “voteForBestMovie”, allowing us to load the page http://localhost:8080/voteForBestMovie when we run our server.*/
    @RequestMapping("/voteForBestMovieForm")
    public String voteForBestMovieFormPage() {
        return "VoteForBestMovie";
    }
    /*create a new method with the request mapping of “/voteForBestMovie”. This method will handle the form data submitted by users. Get the submitted movie title from the request, and add it to the model. Return “voteForBestMovie” so that view is loaded when the form is submitted.*/
    @RequestMapping("/voteForBestMovie")
    public String voteForBestMovie(HttpServletRequest request, Model model) {

        String movieTitle = request.getParameter("movieTitle");

        model.addAttribute("BestMovieVote", movieTitle);

        return "voteForBestMovie";
    }

}