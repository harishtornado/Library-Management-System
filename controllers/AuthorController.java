package controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import models.Author;
import models.Role;
import models.User;
import services.AuthorService;
import services.BookService;
import services.impl.AuthorServiceImpl;
import services.impl.BookServiceImpl;
import utils.TerminalUtils;

public class AuthorController {

    BookService bookService = BookServiceImpl.getInstance();
    AuthorService authorService = AuthorServiceImpl.getInstance();

    public void getInstance(User user) {
        TerminalUtils.clear();
        while (true) {
            System.out.println("Author\n---------------------");
            System.out.print(
                    "1. List All Authors\n2. Search\n");
            if (user != null && user.getRole().equals(Role.ADMIN)) {
                System.out.print("3. Add New Author\n4. Remove Author\n");
            }
            System.out.println("0. Exit");
            Scanner sc = TerminalUtils.scanner();
            System.out.print("\n* Enter Option : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {

                case 1:
                    TerminalUtils.clear();
                    List<Author> authors = authorService.getAuthors();
                    if (authors.size() == 0) {
                        System.out.println("\nNo authors found!\n");
                        break;
                    }
                    TerminalUtils.printBox("Authors");
                    authors.forEach(auth -> System.out.println(auth.getAuthor_name()));
                    System.out.println("\nSort By : \n-----------");
                    System.out.println("1.Name 2.Number of Books written 0.Exit");
                    System.out.print("Enter Sort criteria : ");
                    int choice1 = sc.nextInt();
                    switch (choice1) {
                        case 1:
                            TerminalUtils.clear();
                            authors.stream()
                                    .sorted(Comparator.comparing(auth -> auth.getAuthor_name().toLowerCase()))
                                    .forEach(auth -> System.out.println(auth.getAuthor_name()));
                            System.out.println();
                            break;
                        case 2:
                            TerminalUtils.clear();
                            authors.stream().sorted(Comparator.comparing(
                                    auth -> bookService.getBooksByAuthor(auth.getAuthor_name()).stream().count()))
                                    .forEach(auth -> System.out.println(auth.getAuthor_name()));
                            System.out.println();
                            break;

                        case 0:
                            TerminalUtils.clear();
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    TerminalUtils.clear();
                    System.out.print("* Enter Author Name : ");
                    String authorname = sc.nextLine();
                    Author result = authorService.findAuthor(authorname);
                    if (result == null) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Author Not Found!\"");
                        break;
                    }
                    System.out.println(result);
                    break;
                case 3:
                    if (user == null || user.getRole() != Role.ADMIN) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Access Denied!\"");
                        break;
                    }
                    TerminalUtils.clear();
                    System.out.println("Enter exit to return to main menu...");
                    System.out.print("* Enter Author Name : ");
                    while (true) {
                        String authorName = sc.nextLine();
                        if (authorName.equals("exit")) {
                            TerminalUtils.clear();
                            return;
                        }
                        if (authorName.length() > 0) {
                            authorService.createAuthor(authorName);
                            TerminalUtils.clear();
                            TerminalUtils.printBox("\"Author Created Successfully!\"");
                            break;
                        }
                        System.out.print("* Enter Valid Author Name : ");
                    }
                    break;
                case 4:
                    if (user == null || user.getRole() != Role.ADMIN) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Access Denied!\"");
                        break;
                    }
                    TerminalUtils.clear();
                    List<Author> authors1 = authorService.getAuthors();
                    if (authors1.size() == 0) {
                        System.out.println("\nNo authors found!\n");
                        break;
                    }
                    TerminalUtils.printBox("Authors");
                    authors1.forEach(auth -> System.out.println(auth.getAuthor_name()));
                    System.out.print("\n* Enter Author Name to Delete : ");
                    String authorName = sc.nextLine();
                    Author currAuthor = authorService.findAuthor(authorName);
                    if (currAuthor == null) {
                        TerminalUtils.clear();
                        TerminalUtils.printBox("\"Author Not Found!\"");
                        break;
                    }
                    authorService.deleteAuthor(currAuthor);
                    TerminalUtils.clear();
                    TerminalUtils.printBox("\"Author Removed Successfully!\"");
                    break;
                case 0:
                    TerminalUtils.clear();
                    return;
                default:
                    TerminalUtils.clear();
                    TerminalUtils.printBox("Invalid Operation");
                    break;
            }

        }
    }
}
