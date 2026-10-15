import java.util.Scanner;

public class Main {
    public static int nwd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static int wczytajDodatnia(Scanner wejscie, String nazwa) {
        while (true) {
            System.out.println("Podaj dodatnią liczbę całkowitą " + nazwa + ":");
            if (wejscie.hasNextInt()) {
                int liczba = wejscie.nextInt();
                if (liczba > 0) {
                    return liczba;
                }
            } else {
                wejscie.next();
            }
            System.out.println("Niepoprawna liczba.");
        }
    }

    public static void main(String[] args) {
        Scanner wejscie = new Scanner(System.in);
        int a = wczytajDodatnia(wejscie, "a");
        int b = wczytajDodatnia(wejscie, "b");
        System.out.println("NWD: " + nwd(a, b));
    }
}
