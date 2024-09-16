import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * Enum der beskriver sværhedsgraden, og maksimum og minimum-gæt i spillet.
     * Dette her er ligeså meget blevet lavet fordi jeg gerne ville lære enums i
     * Java at kende, for umiddelbart er de ret mange egenskaber.
     */
    enum Difficulty {
        Easy(1, 10),
        Medium(1, 100),
        Hard(1, 1000);

        final int lower;
        final int upper;

        Difficulty(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }

        /**
         * En statisk funktion til at konvertere et tal til en sværhedsgrad.
         *
         * @param value
         * @return sværhedsgraden
         */
        public static Difficulty fromNumber(int value) {
            return switch (value) {
                case 1 -> Difficulty.Easy;
                case 2 -> Difficulty.Medium;
                case 3 -> Difficulty.Hard;
                default -> Difficulty.Medium;
            };
        }
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.println("Velkommen til Gæt-et-tal!\nVi er glade for at du er her!");

        while (true) {
            gameLoop();

            System.out.print("Har du lyst til at spille igen? (ja/nej) ");

            var res = scanner.nextLine();
            switch (res) {
                case "ja":
                    continue;
                case "nej":
                    return;
                default:
                    System.out.println("Ugyldigt valg. Afslutter programmet...");
                    return;
            }
        }
    }

    /**
     * Spillets kode. At dette er en funktion for sig selv, gør at det er nemt at genstarte spillet.
     */
    static void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("For at starte, skal du vælge en sværhedsgrad: ");
        System.out.println("1. Let (1-10)");
        System.out.println("2. Mellem (1-100)");
        System.out.println("3. Svær (1-1000)");

        // Input en sværhedsgrad. Valgmulighederne rangerer mellem (inklusive) 1-3.
        var inputDifficulty = 0;
        while (inputDifficulty < 1 || inputDifficulty > 3) {
            inputDifficulty = scanner.nextInt();
            if (inputDifficulty < 1 || inputDifficulty > 3) {
                System.out.println("Vælg mellem 1, 2 eller 3");
            }
        }

        var difficulty = Difficulty.fromNumber(inputDifficulty);

        // Et random tal mellem lower og upper i Difficulty enumen bliver valgt.
        var rand = new Random();
        int number = rand.nextInt(difficulty.lower, difficulty.upper + 1);
        System.out.println(STR."Et tal mellem \{difficulty.lower} og \{difficulty.upper} (inklusive) er blevet valgt");
        System.out.println("Begynd at gætte!");

        // Start-gættet er 0 til at starte med.
        int guess = 0;

        // Bliv ved med at gætte til det er korrekt.
        // En do-while kunne også være blevet brugt, men i dette her tilfælde er det fuldstændigt lige meget
        // da guess bliver initialiseret uden for løkken.
        while (number != guess) {
            guess = scanner.nextInt();

            if (guess > number) {
                System.out.println("Tallet er for højt. Gæt lavere!");
            } else if (guess < number) {
                System.out.println("Tallet er for lavt. Gæt højere!");
            } else {
                // Man kan indsætte emojier i koden
                System.out.println("Du gættede rigtigt! You go girl\uD83D\uDC85");
            }
        }
    }
}