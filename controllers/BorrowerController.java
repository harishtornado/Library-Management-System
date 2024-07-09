package controllers;

import java.util.List;
import java.util.Scanner;

import models.Author;
import models.Book;
import models.Genre;
import models.User;
import services.AuthorService;
import services.BookService;
import services.impl.AuthorServiceImpl;
import services.impl.BookServiceImpl;
import utils.TerminalUtils;

public class BorrowerController {
    BookService bookService = BookServiceImpl.getInstance();
    AuthorService authorService = AuthorServiceImpl.getInstance();

    public void getInstance(User currUser) {
        TerminalUtils.clear();
        while (true) {
            System.out.println("Books\n---------------------");
            System.out.println("1.List All Books\n2.Search");
            if (currUser != null) {
                System.out.println("4.Create Book\n5.Remove Book");
            }
            System.out.println("0.Exit");
            Scanner sc = TerminalUtils.scanner();
            System.out.print("\n* Enter Option : ");
            char choice = sc.next().charAt(0);
            sc.nextLine();
            switch (choice) {
                case '1':
                    TerminalUtils.clear();
                    List<Book> books = bookService.getBooks();
                    if (books.size() == 0) {
                        System.out.println("\nNo books found!\n");
                        break;
                    }
                    TerminalUtils.printBox("Books");
                    books.forEach(System.out::println);
                    System.out.print("Borrow(yes/no) : ");
                    String choice1 = sc.next();
                    if (choice1.toLowerCase().trim().equals("yes")) {
                        TerminalUtils.clear();

                        String book_title = null;
                        while (book_title == null || book_title.trim().isEmpty()) {
                            System.out.print("Enter Book Name: ");
                            book_title = sc.nextLine().trim();
                            if (book_title.isEmpty()) {
                                TerminalUtils.clear();
                                TerminalUtils.printBox("Enter Valid Book Name!");
                                book_title = null;
                            }
                        }
                        Book currBook = bookService.getBook(book_title);

                        if (currBook == null) {
                            TerminalUtils.clear();
                            TerminalUtils.printBox("Book Not Found!");
                            break;
                        }

                        User currBorrower = bookService.getBorrower(currBook);

                        if (currBorrower == null) {
                            bookService.setBorrower(currBook, currUser);
                            TerminalUtils.clear();
                            TerminalUtils.printBox("Book Borrowed Successfully!");
                            break;
                        }

                        TerminalUtils.clear();
                        TerminalUtils.printBox("Book Already Borrowed by " + currBorrower.getUser_name());
                        break;
                    } else if (choice1.toLowerCase().trim().equals("no")) {
                        TerminalUtils.clear();
                        return;
                    } else {
                        TerminalUtils.clear();
                        System.out.println(choice1);
                        TerminalUtils.printBox("Invalid input");
                        break;
                    }

                case '2':
                    TerminalUtils.clear();
                    new SearchController().getInstance();
                    break;
                case '4':
                    TerminalUtils.clear();
                    System.out.println("Type exit to return to Main menu...");
                    System.out.print("* Enter Book Name : ");
                    String title = sc.nextLine();
                    if (title.equals("exit")) {
                        TerminalUtils.clear();
                        break;
                    }
                    Author currAuthor = null;
                    TerminalUtils.clear();
                    while (currAuthor == null) {
                        System.out.println("Type exit to return to Main menu...");
                        System.out.print("* Enter Author Name : ");
                        String authname = sc.nextLine();
                        currAuthor = authorService.findAuthor(authname);
                        if (authname.equals("exit")) {
                            TerminalUtils.clear();
                            return;
                        }
                        if (currAuthor != null)
                            break;
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Author not found. Try again!\"");
                    }
                    Genre genre = null;
                    TerminalUtils.clear();
                    while (genre == null) {
                        TerminalUtils.printBox("HORROR, ROMANCE, THRILLER, COMEDY,ACTION, FANTASY, SCI_FI, ADVENTURE");
                        System.out.print(
                                "* Enter Genre : ");
                        try {
                            genre = Genre.valueOf(sc.next().toUpperCase());
                            break;
                        } catch (IllegalArgumentException ex) {
                            TerminalUtils.clear();
                            TerminalUtils.printBox("\"Invalid Genre. Try Again!\"");
                        }
                    }
                    bookService.createBook(title, currAuthor, genre);
                    TerminalUtils.clear();
                    TerminalUtils.printBox("\"Book Added Successfully\"");
                    break;
                case '5':
                    TerminalUtils.clear();
                    TerminalUtils.printBox("Books");
                    List<Book> books1 = bookService.getBooks();
                    if (books1.size() == 0) {
                        System.out.println("\nNo books found!\n");
                        break;
                    }
                    books1.forEach(System.out::println);
                    System.out.print("\nEnter Book Title : ");
                    String title1 = sc.nextLine();
                    Book currBook = bookService.getBook(title1);
                    if (currBook == null) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Book Not Found!\"");
                        break;
                    }
                    bookService.removeBook(currBook.getBook_id());
                    TerminalUtils.clear();
                    TerminalUtils.printBox("\"Book Deleted Successfully!\"");
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
