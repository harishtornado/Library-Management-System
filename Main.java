import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controllers.AuthorController;
import controllers.BookController;
import models.Role;
import models.User;
import services.UserService;
import services.impl.UserServiceImpl;
import utils.TerminalUtils;

public class Main {

    public static User currUser = null;

    public static void main(String[] args) throws Exception {
        UserService userService = UserServiceImpl.getInstance();

        Scanner sc = TerminalUtils.scanner();
        TerminalUtils.clear();
        while (true) {
            System.out.println("\n*************** Welcome to Library Management System ***************\n");
            System.out.println("1. Authors\n2. Books");
            System.out.println(currUser == null ? "3. Login/Signup" : "3. Logout");
            System.out.println("4. Exit\n");
            System.out.print("=> Enter Option : ");
            char choice;
            choice = sc.next().charAt(0);
            switch (choice) {
                case '1':
                    new AuthorController().getInstance(currUser);
                    break;
                case '2':
                    new BookController().getInstance(currUser);
                    break;
                case '3':
                    TerminalUtils.clear();
                    if (currUser != null) {
                        currUser = null;
                        System.out.println("\n\"Logged out Successfully!\"\n");
                        break;
                    }
                    String borrower_name = null;
                    while (true) {
                        TerminalUtils.clear();
                        System.out.print("* Enter your Name: ");
                        borrower_name = sc.nextLine();
                        if (borrower_name.length() > 4) {
                            break;
                        }
                    }
                    User user = userService.getUser(borrower_name);
                    if (user == null) {
                        System.out.print("\n\"You seem to be new here.\"\n\nCreate Password : ");
                        String password = null;
                        while (password == null) {
                            password = sc.next();
                            if (password.length() >= 8) {
                                break;
                            }
                            System.out.print(
                                    "Please Enter a valid password (Password must contains atleast 8 characters) : ");
                            password = null;
                        }
                        currUser = userService.createUser(borrower_name, password, Role.USER);
                        TerminalUtils.clear();
                        System.out.println("\n\"Account Creation Successfull!\"\n");
                    } else {
                        String password = null;
                        System.out.print("Enter your Password : ");
                        while (true) {
                            password = sc.next();
                            if (userService.comparePassword(user, password)) {
                                currUser = user;
                                TerminalUtils.clear();
                                System.out.println("\n\"Login Successfull!\"\n");
                                break;
                            } else {
                                System.out.println("\n\"Invalid Password. Try Again!\"\n");
                                System.out.println("Re-enter password : ");
                            }
                        }
                    }
                    break;
                case '4':
                    System.out.println(
                            "\nSession Ended at " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
                    System.exit(0);
                    break;
                default:
                    TerminalUtils.clear();
                    TerminalUtils.printBox("Invalid Option");
                    break;
            }

        }
    }
}
