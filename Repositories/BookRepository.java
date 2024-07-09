package Repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import models.Book;
import models.Genre;

public interface BookRepository {
    static List<Book> books = new ArrayList<>();

    public static List<Book> findAll() {
        return books;
    }

    public static Book findById(int id) {
        return books.stream().filter(book -> book.getBook_id() == id).findFirst().orElse(null);
    }

    public static List<Book> findByAuthor(String author) {
        return books.stream().filter(book -> book.getAuthor().getAuthor_name().equals(author)).toList();
    }

    public static List<Book> findByGenre(Genre genre) {
        return books.stream().filter(book -> book.getGenre().equals(genre))
                .sorted(Comparator.comparing(Book::getPublished_date).reversed()).toList();
    }

    public static Book findByName(String name) {
        return books.stream().filter(book -> book.getTitle().equals(name)).findFirst().orElse(null);
    }

    public static void create(Book book) {
        books.add(book);
    }

    public static void delete(int id) {
        books.removeIf(book -> book.getBook_id() == id);
    }
}
