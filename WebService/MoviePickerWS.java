/**
 * @author Gaurav
 */
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONException;
import org.json.JSONObject;
//import java.util.Scanner;


@Path("/")
public class MoviePickerWS {

    Statement stmt;
    Connection con;

    MoviePickerWS() throws ClassNotFoundException, SQLException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter MySQL password");
        String host = "localhost";
        String port = "8889";
        String db = "moviePickerEI2";
        String user = "root";
        String pass = "root";

        Class.forName("com.mysql.jdbc.Driver");
        // JDBC connector version used 5.1.47
        con=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, pass);
        stmt=con.createStatement();

    }

    @GET
    @Path("/1")
    @Produces("text/plain")
    public String getSomeMessage(){
        return "yeh le chomu";
    }

    @GET
    @Path("/2")
    @Produces("text/plain")
    public String getSomeMessage1(){
        return "test";
    }

    // This function adds movie in database.
    @POST
    @Path("/addMovie/{movieTitle}/{movieDesc}/{movieRating}/{genre}/{date}")
    public Response addMovie(@PathParam("movieTitle") String movieTitle,
                             @PathParam("movieDesc") String movieDesc,
                             @PathParam("movieRating") float movieRating,
                             @PathParam("genre") String genre,
                             @PathParam("date") String date) throws SQLException {

        String sql = "INSERT INTO Movies(movie_name,movie_desc,movie_rating,movie_genre,release_date) " +
                "VALUES ('"+movieTitle+"', '"+movieDesc+"', "+movieRating+", '"+genre+"',STR_TO_DATE('"+date+"', '%m/%d/%Y'))";

        int executionFlg = stmt.executeUpdate(sql);

        return Response.status(200).entity(executionFlg).build();

    }

    // This function deletes movie from database.
    @DELETE
    @Path("/deleteMovie/{movieTitle}")
    public Response deleteMovie(@PathParam("movieTitle") String movieTitle) throws SQLException {

        String sql = "DELETE FROM Movies " +
                "WHERE movie_name = '"+movieTitle+"' ";

        int executionFlg = stmt.executeUpdate(sql);

        return Response.status(200).entity(executionFlg).build();

    }

    //    This function returns the search result for the movie title
    @GET
    @Path("/TitleSearch/{movieTitle}")
    @Produces("application/json")
    public Response getMoviebyTitle(@PathParam("movieTitle") String movieTitle) throws SQLException, JSONException {


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

        return Response.status(200).entity(jsonObj).build();
//        return Response.status(200).entity(MEDIA_TYPE.).build();

    }

    //This function updates the movies in mysql database
    @PUT
    @Path("/updateMovie/{movieTitle}/{movieDesc}")
    public Response updateMovie(@PathParam("movieTitle") String movieTitle,
                                @PathParam("movieDesc") String movieDesc) throws SQLException {

        String sql = "UPDATE Movies SET movie_desc = '" + movieDesc + "' WHERE movie_name ='" + movieTitle + "'";

        int executionFlg = stmt.executeUpdate(sql);

        return Response.status(200).entity(executionFlg).build();

    }
}
