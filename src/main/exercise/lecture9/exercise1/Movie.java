package com.homework.exercise.lecture9.exercise1;

public class Movie {

    String title;
    String genre;
    double rating;

    public Movie(String title, String genre, double rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public static void main(String[] args) {

        Movie movie1 = new Movie("Interstellar", "Adventure", 8.6);
        Movie movie2 = new Movie("Parasite", "Thriller", 8.6);
        Movie movie3 = new Movie("Coco", "Family", 8.4);

        movie1.showMovieDetails();
        movie2.showMovieDetails();
        movie3.showMovieDetails();
    }
        public void showMovieDetails() {
        System.out.println("Movie Title: " + title);
        System.out.println("Movie Genre: " + genre);
        System.out.println("Rating: " + rating + "/10");
        System.out.println();
    }
}
