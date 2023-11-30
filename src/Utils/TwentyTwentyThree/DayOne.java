package Utils.TwentyTwentyThree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DayOne {
    public static void runDayOne(){
        File file = new File("./input/ElfCaloriesInput.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;

            while ((st = br.readLine()) != null){


            }
            System.out.println("Max - ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
