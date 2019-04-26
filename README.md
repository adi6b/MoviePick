# MoviePick
Movie Pick is a RESTful API providing information about the movies which are currently being showed in Athens. The service provides information about the ratings of movies, the show times, name of the theatres and its location, its genre. The web service offers in total 7 functionalities:
1. Create
2. Update
3. Delete
4. Retrieve Movie by Title
5. Retrieve Movie by Genre
6. Retrieve Movie by Rating
7. Retrieve Movie by Shows

![GitHub Logo](/images/client.png)

### How to use the WebService
#### 1. Deploying the project on JBOSS
1. Clone the repository using command git clone https://github.com/adi6b/MoviePick.git
2. Run the project on your local machine using any IDE since its an Maven project it will automatically install all the dependencies
3. Once the project has been sucessfully build copy the MoviePick.war file and deploy it to /opt/jboss/standalone/deployments on JBOSS server
#### 2. Using the RESTeasy Client
1. Navigate to the directory: /opt/jboss/standalone/deployments/MoviePick/
2. Run command: java -classpath 'WEB-INF/lib/*:WEB-INF/classes/' edu.uga.cs.MoviePick.MovieClient
3. Enter the choice code

### NOTE: Whenever asked for date enter in the following format "MM-DD-YYYY"
