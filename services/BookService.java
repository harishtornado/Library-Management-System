package services;

import java.util.List;

import models.Author;
import models.Book;
import models.User;
import models.Genre;

public interface BookService {
    void createBook(String title, Author author, Genre genre);

    Book getBook(String title);

    void removeBook(int id);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByGenre(Genre genre);

    User getBorrower(Book book);

    void setBorrower(Book book, User user);

    List<Book> getBooks();
}
