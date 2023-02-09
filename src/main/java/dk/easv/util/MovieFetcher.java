package dk.easv.util;

import dk.easv.Main;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MovieFetcher {

    private final String resourceName = "/config.cfg";
    private TmdbApi api;
    private TmdbSearch search;
    private TmdbMovies movies;

    public MovieFetcher(){
        Properties props = new Properties();
        try(InputStream resourceStream = Main.class.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        api = new TmdbApi(props.getProperty("API_KEY"));
        search = new TmdbSearch(api);
        movies = new TmdbMovies(api);
    }


    public MovieResultsPage searchMovie(String query){
        return search.searchMovie(query, 0, "en", false, 0);
    }
    public MovieResultsPage searchMovie(String query, int year){
        return search.searchMovie(query, year, "en", false, 0);
    }

    public MovieDb getMovie(int id){
        return movies.getMovie(id, "en");
    }



}
