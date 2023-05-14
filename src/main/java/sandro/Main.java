package sandro;

import sandro.objects.Parking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(3);

        Scanner scanner = new Scanner(System.in);

        boolean application = true;

        System.out.println("1 - park");
        System.out.println("2 - unpark");
        System.out.println("0 - exit");
        System.out.println("--");

        while (application) {
            switch (scanner.nextLine()) {
                case "1" -> new Thread(parking::park).start();
                case "2" -> new Thread(parking::unpark).start();
                case "0" -> {
                    System.out.println("Application close");
                    application = false;
                }
            }
        }
    }
}