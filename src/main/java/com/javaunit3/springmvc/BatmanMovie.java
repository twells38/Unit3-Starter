package com.javaunit3.springmvc;

import org.springframework.stereotype.Component;

//create a file called BatmanMovie.java where we’ll implement the Movie interface
@Component
public class BatmanMovie implements Movie {

    //Implement getTitle() to return the title “Batman: The Dark Knight”
    public String getTitle(){
        return "Batman: The Dark King";
    }

    //Implement getMaturityRating() to return “PG-13”
    public String getMaturityRating(){
        return "PG-13";
    }

    //Implement getGenre() to return “Action”
    public String getGenre(){
        return "Action";
    }
}
