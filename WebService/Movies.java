/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uga.ei2_restserv;

import java.util.ArrayList;

/**
 *
 * @author agashe
 */
public class Movies {
    
    String movie_name;
    String movie_desc;
    float movie_rating;
    int votes_numb;
    String movie_genre;
    String release_date;
    
    ArrayList<String> theatreNameList = new ArrayList<String>();
    ArrayList<String> showTimingList = new ArrayList<String>();
    
    Movies(){
        
    }

    public ArrayList<String> getTheatreNameList() {
        return theatreNameList;
    }

    public void setTheatreNameList(String theatreNameStr) {
        theatreNameList.add(theatreNameStr);
    }

    public ArrayList<String> getShowTimingList() {
        return showTimingList;
    }

    public void setShowTimingList(String showTimingStr) {
        showTimingList.add(showTimingStr);
    }
    
    
    
    
    
    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_desc() {
        return movie_desc;
    }

    public void setMovie_desc(String movie_desc) {
        this.movie_desc = movie_desc;
    }

    public float getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(float movie_rating) {
        this.movie_rating = movie_rating;
    }

    public int getVotes_numb() {
        return votes_numb;
    }

    public void setVotes_numb(int votes_numb) {
        this.votes_numb = votes_numb;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    
    
    
    
}
