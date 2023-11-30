package Utils.TwentyTwentyTwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DayThree {
    private static final String priorities = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void runDayThreePart1(){
        File file = new File("./input/rucksacks.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalPriority = 0;
            while ((st = br.readLine()) != null){
                String firstHalf = st.substring(0, st.length() / 2);
                String secondHalf = st.substring((st.length() / 2));
                char commonLetter = getCommonLetter(firstHalf, secondHalf);
                if (commonLetter != 0){
                    System.out.println("Common - " + commonLetter);
                    int priority = priorities.indexOf(commonLetter);
                    System.out.println("Priority - " + priority);
                    totalPriority += priority;
                }
                System.out.println(firstHalf + "+++" + secondHalf);
            }
            System.out.println("Total Priority +++ " + totalPriority);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runDayThreePart2(){
        File file = new File("./input/rucksacks.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalPriority = 0;
            int sackIndex = 1;
            String firstSack = null, secondSack = null;
            while ((st = br.readLine()) != null){
                switch (sackIndex%3){
                    case 0:
                        char badgeLetter = getBadgeLetter(firstSack, secondSack, st);
                        if (badgeLetter != 0){
                            System.out.println("Badge#" + sackIndex/3 + " - " + badgeLetter);
                            int priority = priorities.indexOf(badgeLetter);
                            System.out.println("Priority - " + priority);
                            totalPriority += priority;
                        }
                    case 1:
                        firstSack = st;
                    case 2:
                        secondSack = st;
                }
                sackIndex++;
            }
            System.out.println("Total Priority +++ " + totalPriority);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static char getCommonLetter(String firstHalf, String secondHalf) {
        for (char i: firstHalf.toCharArray()) {
            if (secondHalf.indexOf(i) != -1){
                return i;
            }
        }
        return 0;
    }

    private static char getBadgeLetter(String firstSack, String secondSack, String thirdSack) {
        for (char i: firstSack.toCharArray()) {
            if (secondSack.indexOf(i) != -1 && thirdSack.indexOf(i) != -1){
                return i;
            }
        }
        return 0;
    }
}
