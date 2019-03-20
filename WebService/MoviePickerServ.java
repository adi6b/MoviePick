/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uga.ei2_restserv;

import com.google.gson.Gson;
import javax.ws.rs.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.transform.OutputKeys;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author agashe
 */
@Path("/")
public class MoviePickerServ {

    /**
     * @param args the command line arguments
     */
    
    static Statement stmt;
    static Connection con;
    
    public static void connectDB() throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter MySQL password");
//        String pass = sc.nextLine();
        
        Class.forName("com.mysql.jdbc.Driver"); 
        // JDBC connector version used 5.1.47
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviePickerEI2","root","akshay@thane");  
        //here sonoo is database name, root is username and password  
        stmt=con.createStatement(); 
        
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, JSONException {
        // TODO code application logic here
        MoviePickerServ mvpicker = new MoviePickerServ();
        mvpicker.connectDB();
//        System.out.println(mvpicker.addMovie("Batman Returns", "Best Nolan Movie Ever", 4.9f, "adventure", "12-31-2019"));
//        System.out.println(mvpicker.deleteMovie("Interstellar"));
//        System.out.println(mvpicker.getMoviebyTitle());
        System.out.println(mvpicker.listBytheatre("The Lego Movie 2: The Second Part"));
        
    }
    
//    @GET
//    @Path("/1")
//    @Produces("text/plain")
//    public String getSomeMessage(){
//        return "yeh le chomu";
//    }
    
    // This function adds movie in database.
    @POST
    @Path("/addMovie/{movieTitle}/{movieDesc}/{movieRating}/{genre}/{date}")
    @Produces("text/plain")
    public int addMovie(@PathParam("movieTitle") String movieTitle,
                         @PathParam("movieDesc") String movieDesc, 
                         @PathParam("movieRating") float movieRating, 
                         @PathParam("genre") String genre, 
                         @PathParam("date") String date) throws SQLException, ClassNotFoundException {
        
        MoviePickerServ.connectDB();
        
        String sql = "INSERT INTO Movies(movie_name,movie_desc,movie_rating,votes_numb,movie_genre,release_date) " +
                   "VALUES ('"+movieTitle+"', '"+movieDesc+"', "+movieRating+",10 ,'"+genre+"',STR_TO_DATE('"+date+"', '%m-%d-%Y'))";
        
        int executionFlg = stmt.executeUpdate(sql);
        
        return executionFlg;
        
    }
    
    // This fucntion deletes movie from database.
    @DELETE
    @Path("/deleteMovie/{movieTitle}")
    @Produces("text/plain")
    public int deleteMovie(@PathParam("movieTitle") String movieTitle) throws SQLException, ClassNotFoundException {
        
         MoviePickerServ.connectDB();
        String sql = "DELETE FROM Movies " +
                   "WHERE movie_name = '"+movieTitle+"' ";
        
        int executionFlg = stmt.executeUpdate(sql);
        
        return executionFlg;

    }
    
    
    @GET
    @Path("/movieByTitle/{movieTitle}")
    @Produces("text/plain")
    public String getMoviebyTitle(@PathParam("movieTitle") String movieTitle) throws SQLException, JSONException, ClassNotFoundException {
        MoviePickerServ.connectDB();
//        String movieTitle = "The Lego Movie 2: The Second Part";
        ResultSet rs = stmt.executeQuery("select * from Movies where movie_name = '"+movieTitle+"' ");
        Movies mv = new Movies();
        int movie_id = 0;
        
        while(rs.next()){
            mv.setMovie_name(rs.getString("movie_name"));
            mv.setMovie_desc(rs.getString("movie_desc"));
            mv.setMovie_rating(Float.parseFloat(rs.getString("movie_rating")));
            mv.setVotes_numb(Integer.parseInt(rs.getString("votes_numb")));
            mv.setMovie_genre(rs.getString("movie_genre"));  
            mv.setRelease_date(rs.getString("release_date"));
            movie_id = Integer.parseInt(rs.getString("movie_id"));
        }
        
        
        String sql = "select t.theatre_name, s.movie_showtimings from Movies m, theatre_info t, showtimes s WHERE s.movie_id = m.movie_id AND s.theatre_id = t.theatre_id AND m.movie_id="+movie_id+"";
        ResultSet rs1 = stmt.executeQuery(sql);
        while(rs1.next()){
            mv.setTheatreNameList(rs1.getString(1));
            mv.setShowTimingList(rs1.getString(2));
        }
        
        
        Gson gson = new Gson();
        System.out.println(gson.toJson(mv));
        JSONObject jsonObj = new JSONObject(gson.toJson(mv));
        
        return gson.toJson(mv);
//        return Response.status(200).entity(jsonObj).build();
//        return Response.status(200).entity(MEDIA_TYPE.).build();
        
    }
    
        //This function updates the movies in mysql database
    @PUT
    @Path("/updateMovie/{movieTitle}/{movieDesc}")
    @Produces("text/plain")
    public int updateMovie(@PathParam("movieTitle") String movieTitle,
                           @PathParam("movieDesc") String movieDesc) throws SQLException, ClassNotFoundException {

        MoviePickerServ.connectDB();
        String sql = "UPDATE Movies SET movie_desc = '" + movieDesc + "' WHERE movie_name ='" + movieTitle + "'";

        int executionFlg = stmt.executeUpdate(sql);

        return executionFlg;

    }
    
    @GET
    @Path("/movieByGenre/{genreName}")
    @Produces("text/plain")
    public String listByGenre(@PathParam("genreName") String genreName) throws SQLException, JSONException, ClassNotFoundException{
         MoviePickerServ.connectDB();
        String sql = "select movie_name from Movies where movie_genre ='"+genreName+"' ";
        
        ArrayList<String> mv1 = new ArrayList<String>();
        ResultSet rs1 = stmt.executeQuery(sql);
        while(rs1.next()){
            Movies movie1 = new Movies();
            movie1.setMovie_name(rs1.getString("movie_name"));
            mv1.add(movie1.getMovie_name());
            
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(mv1));
//        JSONObject jsonObj = new JSONObject(new Gson().toJson(mv1));
        
        return gson.toJson(mv1);
//        return Response.status(200).entity(jsonObj).build();

    }
    
    
    @GET
    @Path("/movieByRating/{rating}")
    @Produces("text/plain")
    public String listByRating(@PathParam("rating") float rating) throws SQLException, JSONException, ClassNotFoundException{
         MoviePickerServ.connectDB();
        String sql = "select movie_name from Movies where movie_rating > "+rating+" ";
        
        ArrayList<String> mv1 = new ArrayList<String>();
        ResultSet rs1 = stmt.executeQuery(sql);
        while(rs1.next()){
            Movies movie1 = new Movies();
            movie1.setMovie_name(rs1.getString("movie_name"));
            mv1.add(movie1.getMovie_name());
            
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(mv1));
//        JSONObject jsonObj = new JSONObject(new Gson().toJson(mv1));
        
        return gson.toJson(mv1);
//        return Response.status(200).entity(jsonObj).build();

    }    

    @GET
    @Path("/movieByShows/{movie_name}")
    @Produces("text/plain")
    public String listBytheatre(@PathParam("movie_name") String movie_name) throws SQLException, JSONException, ClassNotFoundException{
         MoviePickerServ.connectDB();
        
        String sql = "select t.theatre_name, s.movie_showtimings from Movies m, theatre_info t, showtimes s WHERE s.movie_id = m.movie_id AND s.theatre_id = t.theatre_id AND m.movie_name='"+movie_name+"' ";
        
        ArrayList<Movies> mv1 = new ArrayList<Movies>();
        ResultSet rs1 = stmt.executeQuery(sql);
        while(rs1.next()){
            Movies movie1 = new Movies();
            movie1.setTheatreNameList(rs1.getString(1));
            movie1.setShowTimingList(rs1.getString(2));
            mv1.add(movie1);
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(mv1));
//        JSONObject jsonObj = new JSONObject(new Gson().toJson(mv1));
        
        return gson.toJson(mv1);
//        return Response.status(200).entity(jsonObj).build();

    }
    
    
    
}
