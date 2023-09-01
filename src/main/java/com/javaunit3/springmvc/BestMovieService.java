package com.javaunit3.springmvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class BestMovieService {
    //@Autowired
    private Movie movie;

    /*@Autowired
    public void setMovie(Movie movie){
        this.movie = movie;
    }*/


    //create a constructor that takes a movie as a parameter. Annotate the method so that Spring will use the constructor to inject a Movie object.
    /*@Autowired
    public BestMovieService(Movie movie) {
        this.movie = movie;
    }*/

    @Autowired
    public BestMovieService(@Qualifier("titanicMovie") Movie movie) {
        this.movie = movie;
    }

        public Movie getBestMovie () {
            return movie;
        }
}


