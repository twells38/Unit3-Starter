package com.javaunit3.springmvc;
import com.javaunit3.springmvc.model.MovieEntity;
import com.javaunit3.springmvc.model.VoteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.param.VersionTypeSeedParameterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class MovieController {
    //create bestMovieService object to access information from BestMovieService
    @Autowired
    private BestMovieService bestMovieService;

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }


    //Finding the best movie-with data
    @RequestMapping("/bestMovie")
    public String getBestMoviePage(Model model) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();
        movieEntityList.sort(Comparator.comparing(movieEntity -> movieEntity.getVotes().size()));

        MovieEntity movieWithMostVotes = movieEntityList.get(movieEntityList.size()-1);
        List<String> voterNames = new ArrayList<>();

        for (VoteEntity vote: movieWithMostVotes.getVotes()){
            voterNames.add(vote.getVoterName());
        }
        String voterNamesList = String.join(",", voterNames);

        model.addAttribute("bestMovie", movieWithMostVotes.getTitle());
        model.addAttribute("bestMovieVoters", voterNamesList);

        session.getTransaction().commit();

        return "bestMovie";
    }

    /*create a new method with the request mapping of “voteForBestMovieForm”. This method will simply return the String “voteForBestMovie”, allowing us to load the page http://localhost:8080/voteForBestMovie when we run our server.*/
    /*to get a list of all the movies entities in the database, then populate a “movies” attribute in the model with the list.*/
    @RequestMapping("/voteForBestMovieForm")
    public String voteForBestMovieFormPage(Model model) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();

        session.getTransaction().commit();

        model.addAttribute("movies", movieEntityList);
        return "VoteForBestMovie";
    }

    /*create a new method with the request mapping of “/voteForBestMovie”. This method will handle the form data submitted by users. Get the submitted movie title from the request, and add it to the model. Return “voteForBestMovie” so that view is loaded when the form is submitted.*/
    //Saving the votes
    @RequestMapping("/voteForBestMovie")
    public String voteForBestMovie(HttpServletRequest request, Model model) {

        String movieId = request.getParameter("movieId");
        String voterName = request.getParameter("voterName");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class, Integer.parseInt(movieId));
        VoteEntity newVote = new VoteEntity();
        newVote.setVoterName(voterName);
        movieEntity.addVote(newVote);

        session.update(movieEntity);

        session.getTransaction().commit();
        return "voteForBestMovie";
    }
    // In the MovieController, use field injection to set a private SessionFactory variable. This variable will be set by the bean we defined earlier for SessionFactory.
    @Autowired
    private SessionFactory sessionFactory;

    //In MovieController, create a method addMovieForm with the request mapping “/addMovieForm”. Simply return “addMovie” to direct to the addMovie.html view.
    @RequestMapping("/addMovieForm")
    public String addMovieForm() {
        return "addMovie";
    }

    //create a method addMovie with the request mapping “addMovie”. In this method, get the movie title, maturity rating, and genre from the request and assign them to local variables.
    @RequestMapping("/addMovie")
    public String addMovie(HttpServletRequest request){
        String movieTitle = request.getParameter("movieTitle");
        String maturityRate = request.getParameter("maturityRating");
        String genre = request.getParameter("genre");

        //Create a new MovieEntity object, and set all  values appropriately.
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movieTitle);
        movieEntity.setMaturityRating(maturityRate);
        movieEntity.setGenre(genre);

        //Using the session factory we injected, get a session object. Use it to begin a transaction, save the MovieEntity object, and then commit the transaction
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(movieEntity);
        session.getTransaction().commit();
        return "addMovie";
    }
}