import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first three digits of your bank account: ");
        String digits = scanner.nextLine();

        URL url = URI.create("https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt").toURL();
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "IBM852"));

        String line;
        String bankName = null;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length > 1 && parts[0].trim().equals(digits)) {
                bankName = parts[1].trim();
                break;
            }
        }
        reader.close();

        if (bankName != null) {
            System.out.println("Your bank is: " + bankName);
        } else {
            System.out.println("Bank not found");
        }
    }
}