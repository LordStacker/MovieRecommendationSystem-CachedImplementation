package dk.easv.be;

public class Card {

    private String title;

    private String moviePhoto;

    private String subTitleMovie;

    public Card(String title, String moviePhoto, String subTitleMovie) {
        this.title = title;
        this.moviePhoto = moviePhoto;
        this.subTitleMovie = subTitleMovie;
    }

    public Card(String title, String moviePhoto) {
        this.title = title;
        this.moviePhoto = moviePhoto;
    }


    public String getMoviePhoto() {
        return moviePhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitleMovie() {
        return subTitleMovie;
    }

    public void setMoviePhoto(String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }

    public void setSubTitleMovie(String subTitleMovie) {
        this.subTitleMovie = subTitleMovie;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
