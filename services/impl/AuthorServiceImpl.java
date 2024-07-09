package services.impl;

import java.util.List;

import Repositories.AuthorRepository;
import models.Author;
import services.AuthorService;

public class AuthorServiceImpl implements AuthorService {

    private static AuthorServiceImpl instance = null;

    public static AuthorServiceImpl getInstance() {
        if (instance == null) {
            instance = new AuthorServiceImpl();
        }
        return instance;
    }

    @Override
    public void createAuthor(String authorName) {
        Author newAuthor = new Author(authorName);
        AuthorRepository.create(newAuthor);
    }

    @Override
    public void printAuthors() {
        List<Author> authors = AuthorRepository.findAll();
        authors.forEach(author -> System.out.println(author.getAuthor_name()));
    }

    @Override
    public Author findAuthor(String authorName) {
        return AuthorRepository.findByName(authorName);
    }

    @Override
    public List<Author> getAuthors() {
        return AuthorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Author author) {
        AuthorRepository.delete(author.getAuthor_id());
    }

}
