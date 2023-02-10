package dk.easv.util;

import dk.easv.Main;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MovieFetcher {

    private final String resourceName = "/config.cfg";
    private TmdbApi api;
    private TmdbSearch search;
    private TmdbMovies movies;
    private TmdbTV tv;
    private static final MovieFetcher INSTANCE = new MovieFetcher();

    private MovieFetcher(){
        Properties props = new Properties();
        try(InputStream resourceStream = Main.class.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        api = new TmdbApi(props.getProperty("API_KEY"));
        search = api.getSearch();
        movies = api.getMovies();
        tv = api.getTvSeries();
    }

    public static MovieFetcher getInstance(){
        return INSTANCE;
    }
    public TmdbSearch.MultiListResultsPage searchMulti(String query){
        return search.searchMulti(query, "en", 1);
    }
    public MovieResultsPage searchMovie(String query, int year){
        return search.searchMovie(query, year, "en", false, 1);
    }

    public TvResultsPage searchTv(String query){
        return search.searchTv(query,"en", 1);
    }

    public MovieDb getMovie(int id){
        return movies.getMovie(id, "en");
    }
    public TvSeries getTv(int id){
        return tv.getSeries(id, "en");
    }



}
