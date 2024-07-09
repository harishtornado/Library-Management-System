package services;

import java.util.List;

import models.Author;

public interface AuthorService {
    void createAuthor(String authorName);

    Author findAuthor(String authorName);

    List<Author> getAuthors();

    void deleteAuthor(Author author);

    void printAuthors();
}
