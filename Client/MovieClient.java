/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uga.ei2_restserv;

/**
 *
 * @author agashe
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONArray;
import org.json.JSONObject;



public class MovieClient {
    private static final String BASE_URL = "http://localhost:8081/EI2_restServ-1.0-SNAPSHOT/api";

    public static void main(String args[]) throws Exception{
        System.out.println("=====================================");
        System.out.println(" Welcome to MoviePick API Service");
        System.out.println("=====================================");
        System.out.println("Select the service to be used");
        System.out.println("1. Create");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Retrieve Movie By Title");
        System.out.println("5. Retrieve Movie By Genre");
        System.out.println("6. Retrieve Movie By Rating");
        System.out.println("7. Retrieve Movie By Theatre");

        Scanner sc = new Scanner(System.in);
        int selection = Integer.parseInt(sc.nextLine());

        switch (selection){
            case 1:
                System.out.println("You selected to add a record using our service");
                System.out.println("Enter the name of the movie");
                String movie_name = sc.nextLine();
                System.out.println("Enter a short description about movie");
                String movie_desc = sc.nextLine();
                System.out.println("Enter the rating of movie");
                float movie_rating = Float.parseFloat(sc.nextLine());
                System.out.println("Enter the genre of movie");
                String genre = sc.nextLine();
                System.out.println("Enter the released date of movie");
                String date = sc.nextLine();
                String path = "/addMovie/"+ movie_name + "/" + movie_desc + "/" + movie_rating + "/" + genre + "/" + date;
                String newUri = BASE_URL + path;

                try{
                    //Creating the RESTeasy Client
		ClientRequest request = new ClientRequest(newUri);
		request.accept("text/plain");
		ClientResponse<String> response = request.post(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));

                String jsonText = readAll(br);
//                    System.out.println(jsonText);
                    
                    if(Integer.parseInt(jsonText)==1){
                        System.out.println("New movie named "+movie_name+" successfully created.");
                    }else{
                        System.out.println("Creation Failed");
                    }
                

                } catch(Exception e) {

                }
                
                break;
                
                
                
                
            case 2:
                try{
                    
                System.out.println("Enter values for updating description for a movie");
                System.out.println("Enter the name of the movie");
                String mv_name = sc.nextLine();
                System.out.println("Enter the name of the movie");
                String mv_desc = sc.nextLine();
                
                String urlStr = BASE_URL+"/updateMovie/"+mv_name+"/"+mv_desc;
                
                                   //Creating the RESTeasy Client
		ClientRequest request = new ClientRequest(urlStr);
		request.accept("text/plain");
		ClientResponse<String> response = request.put(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));

                String jsonText = readAll(br);
//                    System.out.println(jsonText);
                    if(Integer.parseInt(jsonText)==1){
                        System.out.println("New movie named "+mv_name+" successfully updated.");
                    }else{
                        System.out.println("Update Failed");
                    }
                
                }catch(Exception e){
                    
                }
                
                break;
                
                
            case 3:
                try{
                    
                System.out.println("Enter values for updating description for a movie");
                System.out.println("Enter the name of the movie to be deleted");
                String mv_name = sc.nextLine();
                
                String urlStr = BASE_URL+"/deleteMovie/"+mv_name;
                
                                   //Creating the RESTeasy Client
		ClientRequest request = new ClientRequest(urlStr);
		request.accept("text/plain");
		ClientResponse<String> response = request.delete(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));

                String jsonText = readAll(br);
                    System.out.println(jsonText);
                    
                    if(Integer.parseInt(jsonText)==1){
                        System.out.println("New movie named "+mv_name+" successfully deleted.");
                    }else{
                        System.out.println("Deletion Failed");
                    }
                
                }catch(Exception e){
                    
                }
                
                break;
                
            case 4:
                try{
                System.out.println("Enter Movie Name");
                String mv_name = sc.nextLine();
                String urlStr = BASE_URL+"/movieByTitle/"+mv_name;
		ClientRequest request = new ClientRequest(urlStr);
		request.accept("text/plain");
		ClientResponse<String> response = request.get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));
                
                      String jsonText = readAll(br);
                      JSONObject json = new JSONObject(jsonText);
//                      System.out.println(json);
//                      HashMap<HashMap<String,String>> myjsonObj =(HashMap<HashMap<String,String>>) json;

                        ObjectMapper mapper = new ObjectMapper();
        
                        try{
                            HashMap<String, String> map = new HashMap<String, String>();
                            JSONObject jObject = new JSONObject(jsonText);
                            Iterator<?> keys = jObject.keys();

                            while( keys.hasNext() ){
                                String key = (String)keys.next();
                                String value = jObject.getString(key); 
                                map.put(key, value);

                            }

                            for (Map.Entry<String, String> entry : map.entrySet())
                            {
                                System.out.println(entry.getKey() + " --> " + entry.getValue());
                            }
                        }catch(Exception e1){
                            System.out.println(e1);
                        }
//                        System.out.println(movies.getMovie_name());
        

                }catch(Exception e){
                    
                } 
                
                break;
                      
            case 5:
                try{
                System.out.println("Enter Genre");
                String mv_genre = sc.nextLine();
                
                String urlStr = BASE_URL+"/movieByGenre/"+mv_genre;
		ClientRequest request = new ClientRequest(urlStr);
		request.accept("text/plain");
		ClientResponse<String> response = request.get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));
                
                      String jsonText = readAll(br);
                      
                      JSONArray jsonarray = new JSONArray(jsonText);
                      System.out.println("Movies with genre "+mv_genre+" are:");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            System.out.println(jsonarray.get(i));
                        }
                        
                        
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
                
            case 6:
                try{
                System.out.println("Enter Rating");
                String mv_rating = sc.nextLine();
                
                String urlStr = BASE_URL+"/movieByRating/"+mv_rating;
		ClientRequest request = new ClientRequest(urlStr);
		request.accept("text/plain");
		ClientResponse<String> response = request.get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));
                
                      String jsonText = readAll(br);
//                      System.out.println(jsonText);
                      
                      JSONArray jsonarray = new JSONArray(jsonText);
                      System.out.println("Movies with rating "+mv_rating+" and above are:");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            System.out.println(jsonarray.get(i));
                        }
                      
                      
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
                
            case 7:
                try{
                System.out.println("Enter Movie Name To see where its screening!!");
                String mv_movie = sc.nextLine();
                
                String urlStr = BASE_URL+"/movieByShows/"+mv_movie;
		ClientRequest request = new ClientRequest(urlStr);
		request.accept("text/plain");
		ClientResponse<String> response = request.get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));
                
                      String jsonText = readAll(br);
                      jsonText = "{ movies:" + jsonText + "}";
                   
                    JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject)jsonParser.parse(jsonText);
            JsonArray jsonArr = jo.getAsJsonArray("movies");
            Gson googleJson = new Gson();
            ArrayList jsonObjList = googleJson.fromJson(jsonArr, ArrayList.class); 
                    System.out.println("Show timings for movie "+mv_movie+" are as follows:");
            for(int i = 0 ; i<jsonObjList.size() ; i++){
               
               LinkedHashMap<String,ArrayList> ab = (LinkedHashMap<String,ArrayList>) jsonObjList.get(i);
               
               System.out.print(ab.get("theatreNameList").get(0).toString()); 
                System.out.print(" --> ");
                System.out.println(ab.get("showTimingList").get(0).toString());

            }
                    
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
                
            default:
                System.out.println("Please enter valid number");
                
        }
    }
    
            private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
              sb.append((char) cp);
            }
            return sb.toString();
          }
 
}
