package utils;

import java.io.IOException;
import java.util.Scanner;

public class TerminalUtils {
    private static Scanner scanner = null;

    public static void clear() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static Scanner scanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void printBox(String message) {
        int length = message.length();
        printLine(length);
        System.out.println("| " + message + " |");
        printLine(length);
    }

    private static void printLine(int length) {
        System.out.print("+");
        for (int i = 0; i < length + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
