package org.gp3.moblima.view.moviegoer;

import org.gp3.moblima.model.Constant;
import org.gp3.moblima.model.Movie;
import org.gp3.moblima.model.Review;
import org.gp3.moblima.view.BaseMenu;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static org.gp3.moblima.view.IOUtil.*;

/**
 * Created by zhangxinye on 19/10/17.
 */
public class MovieInfo extends BaseMenu {
    private final Movie movie;
    public MovieInfo(BaseMenu previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }

    @Override
    public BaseMenu execute() {
        printTitle(movie.getTitle());

        printMovieInfo();

        ArrayList<String> choices = new ArrayList<>();
        int c=0;
        if(!movie.getShowingStatus().equals(Constant.ShowingStatus.END_SHOWING))
            choices.add("Buy Tickets");
        else
            c=1;
        choices.add("Reviews");
        choices.add("Back");
        printMenuItems(choices, 0);
        c = c + readChoice(0, choices.size());

        BaseMenu nextMenu = this;
        switch (c) {
            case 0:
                nextMenu = new BuyTicketMenu(this, movie);
                break;
            case 1:
                nextMenu = new ReviewsMenu(this, movie);
                break;
            case 2:
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;
    }

    public void printMovieInfo() {
        println("Title         : " + this.movie.getTitle());
        println("Showing Status: " + this.movie.getShowingStatus().toString());
        println("Content Rating: " + this.movie.getContentRating().toString());
        println("Runtime       : " + this.movie.getRuntime());
        println("Director      : " + this.movie.getDirector());
        println("Opening       : " + this.movie.getOpening());
        print(  "Synopsis      : ");println( this.movie.getSynopsis(), 16);
        if(movie.getRatingTimes() != 0 ){
        print("Overall Rating  :");
                printStars(movie.getOverAllRating());
            }
            else{
                println("Overall Rating: N/A");
            }
        if (movie.getRatingTimes() != 0){
            for (Review r : movie.getReview()) {
                print("Review         : ");
                println(r.getComment());
                print("Rating         : ");
                printStars(r.getRating());
            }
        }
        else
        {

            println("Overall Rating: N/A");
        }
    }
    public void printStars(double rating) {
//        double rating = re.getOverAllRating();
        if(rating <= 1)
            println("★☆☆☆☆(" + rating + ")");
        else if(rating <= 2)
            println("★★☆☆☆(" + rating + ")");
        else if(rating <= 3)
            println("★★★☆☆(" + rating + ")");
        else if(rating <= 4)
            println("★★★★☆(" + rating + ")");
        else
            println("★★★★★(" + rating + ")");
        println();
    }
}
