package mypackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long id = scanner.nextLong();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Dummy dummy = new Dummy(id, x, y);
        dummy.run();
    }
}
