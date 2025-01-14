package dk.easv.dal;

import dk.easv.be.Movie;
import dk.easv.be.Rating;
import dk.easv.be.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAccessManager {
    private final HashMap<Integer, User> users = new HashMap<>();
    private final HashMap<Integer, Movie> movies = new HashMap<>();
    private final List<Rating> ratings = new ArrayList<>();

    // Loads all data from disk and stores in memory
    // For performance, data is only updated if updateCacheFromDisk() is called
    public DataAccessManager() {
        updateCacheFromDisk();
    }

    public Map<Integer, User> getAllUsers() {
        return users;
    }

    public Map<Integer, Movie> getAllMovies() {
        return movies;
    }

    public List<Rating> getAllRatings(){
        return ratings;
    }


    public void updateCacheFromDisk(){
        loadAllRatings();
    }

    private void loadAllMovies() {
        Thread t = new Thread(()->{
            try {
                List<String> movieLines = Files.readAllLines(Path.of("data/movie_titles.txt"));
                for (String movieLine : movieLines) {
                    String[] split = movieLine.split(",");
                    Movie movie = new Movie(Integer.parseInt(split[0]), split[2], Integer.parseInt(split[1]));
                    movies.put(movie.getId(), movie);
                }
            } catch (IOException e) {
                e.printStackTrace();
        }});
        t.start();
    }

    private void loadAllUsers() {
        Thread t = new Thread(() -> {
            try {
                List<String> userLines = Files.readAllLines(Path.of("data/users.txt"));
                for (String userLine : userLines) {
                    String[] split = userLine.split(",");
                    User user = new User(Integer.parseInt(split[0]), split[1]);
                    users.put(user.getId(), user);
                }
            } catch (IOException e) {
                e.printStackTrace();
        }});
        t.start();

    }

    // Loads all ratings, users and movies must be loaded first
    // Users holds a list of ratings and movies holds a list of ratings
    private void loadAllRatings() {
        Thread t = new Thread(() -> {
            loadAllMovies();
            loadAllUsers();
            try {
                List<String> ratingLines = Files.readAllLines(Path.of("data/ratings.txt"));
                for (String ratingLine : ratingLines) {
                    String[] split = ratingLine.split(",");
                    int movieId = Integer.parseInt(split[0]);
                    int userId = Integer.parseInt(split[1]);
                    int rating = Integer.parseInt(split[2]);
                    Rating ratingObj = new Rating(users.get(userId), movies.get(movieId), rating);
                    ratings.add(ratingObj);
                    users.get(userId).getRatings().add(ratingObj);
                    movies.get(movieId).getRatings().add(ratingObj);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }


}
