import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static NumberFormat currency = NumberFormat.getCurrencyInstance();
    static NumberFormat number = NumberFormat.getInstance();
    public static void main(String[] args) {
        try {
            String line, name = "", currentName = "";
            String[] parts;
            double contribution, min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0.0;
            int count = 0;
            Scanner file = new Scanner(new File("2024_Presidential_Contributions.csv"));
            // skip the heading
            file.nextLine();

            // loop through the data (while)
            while (file.hasNextLine()) {
                line = file.nextLine();
                parts = line.split(",");
                // Setting each value to a spot in parts[]
                name = parts[1];
                contribution = Double.parseDouble(parts[34]);

                if (contribution > 0 && "Donald Trump".equalsIgnoreCase(name)) {
                    sum += contribution;
                    count++;
                    if (contribution < min) { min = contribution; }
                    if (contribution > max) { max = contribution; }

                }
            }
            // After the loop, print the statistics!!
            System.out.println("************************************************");
            System.out.println("*  2024 Presidential Campaign Contributions    *");
            System.out.println("*                For candidate                 *");
            System.out.println("*                 DONALD TRUMP                 *");
            System.out.println("************************************************");
            printAll(count, min, max, sum);
            // close the file
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());;
        }
    }

    public static void printAll(int pCount, double pMin, double pMax, double pSum) {
        System.out.println("The number of contributions is: " + number.format(pCount));
        System.out.println("The lowest contribution is: " + currency.format(pMin));
        System.out.println("The highest contribution is: " + currency.format(pMax));
        System.out.println("The average contribution is: " + currency.format(pSum/pCount));
        System.out.println("The total contribution is: " + currency.format(pSum));
    }
}