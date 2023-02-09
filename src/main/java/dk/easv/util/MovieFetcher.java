package dk.easv.util;

import dk.easv.Main;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MovieFetcher {

    private final String resourceName = "/config.cfg";

    public MovieFetcher(){
        Properties props = new Properties();
        try(InputStream resourceStream = Main.class.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TmdbMovies movies = new TmdbApi(props.getProperty("API_KEY")).getMovies();
        System.out.println(movies);
    }


}
