package dk.easv.gui.model;

import dk.easv.be.*;
import dk.easv.bll.LogicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AppModel {
    private static final AppModel INSTANCE = new AppModel();
    LogicManager logic = new LogicManager();
    // Models of the data in the view
    private static final ObservableList<User>  obsUsers = FXCollections.observableArrayList();
    private static final ObservableList<Movie> obsTopMovieSeen = FXCollections.observableArrayList();
    private static final ObservableList<Movie> obsTopMovieNotSeen = FXCollections.observableArrayList();
    private static final ObservableList<UserSimilarity>  obsSimilarUsers = FXCollections.observableArrayList();
    private static final ObservableList<TopMovie> obsTopMoviesSimilarUsers = FXCollections.observableArrayList();

    private AppModel() {
    }
    public static AppModel getInstance() {
        return INSTANCE;
    }
    public void loadUsers(){
        obsUsers.clear();
        obsUsers.addAll(logic.getAllUsers());
    }

    public void loadData(User user) {
            obsTopMovieSeen.clear();
            obsTopMovieSeen.addAll(logic.getTopAverageRatedMovies(user));
            obsTopMovieNotSeen.clear();
            obsTopMovieNotSeen.addAll(logic.getTopAverageRatedMoviesUserDidNotSee(user));
            obsSimilarUsers.clear();
            obsSimilarUsers.addAll(logic.getTopSimilarUsers(user));
            obsTopMoviesSimilarUsers.clear();
            obsTopMoviesSimilarUsers.addAll(logic.getTopMoviesFromSimilarPeople(user));
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
