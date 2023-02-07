package dk.easv.gui.model;

import dk.easv.be.*;
import dk.easv.bll.LogicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AppModel {

    LogicManager logic = new LogicManager();
    // Models of the data in the view
    private final ObservableList<User>  obsUsers = FXCollections.observableArrayList();
    private final ObservableList<Movie> obsTopMovieSeen = FXCollections.observableArrayList();
    private final ObservableList<Movie> obsTopMovieNotSeen = FXCollections.observableArrayList();
    private final ObservableList<UserSimilarity>  obsSimilarUsers = FXCollections.observableArrayList();
    private final ObservableList<TopMovie> obsTopMoviesSimilarUsers = FXCollections.observableArrayList();

    public void loadUsers(){
        obsUsers.clear();
        obsUsers.addAll(logic.getAllUsers());
    }

    public void loadData(User user) {
        Thread t1 = new Thread(() -> {
            obsTopMovieSeen.clear();
            obsTopMovieSeen.addAll(logic.getTopAverageRatedMovies(user));
        });
        Thread t2 = new Thread(() -> {
            obsTopMovieNotSeen.clear();
            obsTopMovieNotSeen.addAll(logic.getTopAverageRatedMoviesUserDidNotSee(user));
        });
        Thread t3 = new Thread(() -> {
            obsSimilarUsers.clear();
            obsSimilarUsers.addAll(logic.getTopSimilarUsers(user));
        });
        Thread t4 = new Thread(() -> {
            obsTopMoviesSimilarUsers.clear();
            obsTopMoviesSimilarUsers.addAll(logic.getTopMoviesFromSimilarPeople(user));
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public ObservableList<User> getObsUsers() {
        return obsUsers;
    }

    public ObservableList<Movie> getObsTopMovieSeen() {
        return obsTopMovieSeen;
    }

    public ObservableList<Movie> getObsTopMovieNotSeen() {
        return obsTopMovieNotSeen;
    }

    public ObservableList<UserSimilarity> getObsSimilarUsers() {
        return obsSimilarUsers;
    }

    public ObservableList<TopMovie> getObsTopMoviesSimilarUsers() {
        return obsTopMoviesSimilarUsers;
    }
}
