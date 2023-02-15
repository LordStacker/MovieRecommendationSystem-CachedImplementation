package dk.easv.be;

public class Card {

    private String title;

    private String moviePhoto;

    private int year;

    public Card(String title, String moviePhoto, int year) {
        this.title = title;
        this.moviePhoto = moviePhoto;
        this.year = year;
    }


    public String getMoviePhoto() {
        return moviePhoto;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public void setMoviePhoto(String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
