package com.javaunit3.springmvc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class MovieApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieApp.class);
        //Using the application context, get the best movie service using the default bean id (since we did not specify a bean id, the id will simply be the class name of the bean starting with a lower case).
          BestMovieService bestMovieService = applicationContext.getBean("bestMovieService", BestMovieService.class);

          //Using the best movie service, get the best movie
          Movie bestMovie = bestMovieService.getBestMovie();

          //Print out the title, maturity rating, and genre of the best movie
          System.out.println("Title: " + bestMovie.getTitle());
          System.out.println("Maturity Rating: " + bestMovie.getMaturityRating());
          System.out.println("Genre: " + bestMovie.getGenre());

    }
}
