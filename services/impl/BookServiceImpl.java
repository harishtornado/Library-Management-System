package services.impl;

import java.util.List;

import Repositories.BookRepository;
import models.Author;
import models.Book;
import models.User;
import models.Genre;
import services.BookService;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance = null;

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public void createBook(String title, Author author, Genre genre) {
        Book book = new Book(title, author, genre);
        BookRepository.create(book);
    }

    @Override
    public List<Book> getBooks() {
        return BookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return BookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return BookRepository.findByGenre(genre);
    }

    @Override
    public Book getBook(String title) {
        return BookRepository.findByName(title);
    }

    @Override
    public User getBorrower(Book book) {
        return BookRepository.findById(book.getBook_id()).getCurrent_holder();
    }

    @Override
    public void setBorrower(Book book, User user) {
        book.setCurrent_holder(user);
    }

    @Override
    public void removeBook(int id) {
        BookRepository.delete(id);
    }
}
