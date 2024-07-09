package models;

import java.time.LocalDateTime;

public class Book {
    private static int id = 0;
    private int book_id;
    private String title;
    private Author author;
    private Genre genre;
    private User current_holder;

    public User getCurrent_holder() {
        return current_holder;
    }

    public void setCurrent_holder(User current_holder) {
        this.current_holder = current_holder;
    }

    private LocalDateTime published_date;

    public Book(String title, Author author, Genre genre) {
        this.book_id = id++;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.published_date = LocalDateTime.now();
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDateTime getPublished_date() {
        return published_date;
    }

    public void setPublished_date(LocalDateTime published_date) {
        this.published_date = published_date;
    }

    @Override
    public String toString() {
        return "book_id=" + book_id + ", title=" + title + ", author=" + author.getAuthor_name() + ", genre="
                + genre
                + ", current_holder=" + (current_holder != null ? current_holder.getUser_name() : "None")
                + ", published_date=" + published_date;
    }

}
