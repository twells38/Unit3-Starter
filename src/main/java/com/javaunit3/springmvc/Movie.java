package com.javaunit3.springmvc;
//create an interface that allows us to get the attributes of a given movie.
public interface Movie {
   // Add a public method to get the Title
    public  String getTitle();

    // Add a public method to get the Maturity Rating
    public  String getMaturityRating();

    // Add a public method to get the Genre
    public  String getGenre();
}
