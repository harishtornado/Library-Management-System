package controllers;

import java.util.List;
import java.util.Scanner;

import models.Author;
import models.Book;
import models.Genre;
import services.AuthorService;
import services.BookService;
import services.impl.AuthorServiceImpl;
import services.impl.BookServiceImpl;
import utils.TerminalUtils;

public class SearchController {

    BookService bookService = BookServiceImpl.getInstance();
    AuthorService authorService = AuthorServiceImpl.getInstance();

    public void getInstance() {
        TerminalUtils.clear();
        while (true) {
            System.out.println("*******Search********\n---------------------");
            System.out.println("1.Search Book by title\n2.Search Book by Author\n3.Search Book by Genre\n0.Exit");
            Scanner sc = TerminalUtils.scanner();
            System.out.print("\n* Enter Option : ");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            switch (choice) {
                case '1':
                    TerminalUtils.clear();
                    System.out.print("Enter Book Title : ");
                    Book currBook = bookService.getBook(sc.nextLine());
                    if (currBook == null) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Book Not Found!\"");
                        break;
                    }
                    TerminalUtils.clear();
                    System.out.println(currBook);
                    break;
                case '2':
                    TerminalUtils.clear();
                    Author currAuthor = null;
                    System.out.print("Enter Author Name : ");
                    currAuthor = authorService.findAuthor(sc.nextLine());
                    if (currAuthor == null) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Author Not Found!\"");
                        break;
                    }
                    List<Book> books1 = bookService.getBooksByAuthor(currAuthor.getAuthor_name());
                    if (books1.size() == 0) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"No Books found for the Author!\"");
                        break;
                    }
                    TerminalUtils.clear();
                    books1.forEach(System.out::println);
                    break;
                case '3':
                    TerminalUtils.clear();
                    Genre currGenre = null;
                    while (currGenre == null) {
                        TerminalUtils.clear();
                        System.out.print(
                                "* Enter Genre ( HORROR, ROMANCE, THRILLER, COMEDY,ACTION, FANTASY, SCI_FI, ADVENTURE ) : ");
                        try {
                            currGenre = Genre.valueOf(sc.next().toUpperCase());
                            break;
                        } catch (IllegalArgumentException ex) {
                            TerminalUtils.printBox("\"Invalid Genre. Try Again!\"");
                        }
                    }
                    List<Book> books2 = bookService.getBooksByGenre(currGenre);
                    if (books2.size() == 0) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"No Books found for the Genre!\"");
                        break;
                    }
                    TerminalUtils.clear();
                    books2.forEach(System.out::println);
                    break;

                case '0':
                    TerminalUtils.clear();
                    return;
                default:
                    TerminalUtils.printBox("Invalid Operation");
                    break;
            }

        }
    }
}
