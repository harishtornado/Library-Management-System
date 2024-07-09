package Repositories;

import java.util.ArrayList;
import java.util.List;

import models.Author;

public interface AuthorRepository {
    static List<Author> authors = new ArrayList<>();

    public static List<Author> findAll() {
        return authors;
    }

    public static Author findById(int id) {
        return authors.stream().filter(author -> author.getAuthor_id() == id).findFirst().orElse(null);
    }

    public static Author findByName(String name) {
        return authors.stream().filter(author -> author.getAuthor_name().equals(name)).findFirst().orElse(null);
    }

    public static void create(Author author) {
        authors.add(author);
    }

    public static void delete(int id) {
        authors.removeIf(author -> author.getAuthor_id() == id);
    }
}
