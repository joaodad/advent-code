import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DayTwoUtils {
    private static final char rock = 'X';
    private static final char paper = 'Y';
    private static final char scissors = 'Z';
    private static final char theirRock = 'A';
    private static final char theirPaper = 'B';
    private static final char theirScissors = 'C';

    public static void runDayOne(){
        File file = new File("./input/ElfCaloriesInput.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int currentCaloriesMax = 0;
            int current2ndMax = 0;
            int current3rdMax = 0;
            int currentCaloriesForCurrentElf = 0;
            while ((st = br.readLine()) != null){
                if (st.equals("")){
                    // End of elf data
                    if (currentCaloriesForCurrentElf > currentCaloriesMax){
                        current3rdMax = current2ndMax;
                        current2ndMax = currentCaloriesMax;
                        currentCaloriesMax = currentCaloriesForCurrentElf;
                        System.out.println("1 - " + currentCaloriesMax + "2 - " + current2ndMax + "3 - " + current3rdMax);
                    } else if (currentCaloriesForCurrentElf > current2ndMax) {
                        current3rdMax = current2ndMax;
                        current2ndMax = currentCaloriesForCurrentElf;
                    } else if (currentCaloriesForCurrentElf > current3rdMax) {
                        current3rdMax = currentCaloriesForCurrentElf;
                    }
                    currentCaloriesForCurrentElf = 0;
                } else {
                    // add calories to total for elf
                    int calories = Integer.parseInt(st);
                    currentCaloriesForCurrentElf += calories;
                }

            }
            System.out.println("Max - " + currentCaloriesMax);
            int sum = currentCaloriesMax + current2ndMax + current3rdMax;
            System.out.println("Sum - " + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runDayTwoPart1(){
        File file = new File("./input/RPSstrategyGuide.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalScore = 0;
            while ((st = br.readLine()) != null){
                char theirMove = st.charAt(0);
                char myMove = st.charAt(2);
                int scoreFromRound = getScoreFromBattle(myMove, theirMove);
                totalScore += scoreFromRound;
                System.out.println("Battle Sum - " + scoreFromRound);
            }
            System.out.println("Sum - " + totalScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runDayTwoPart2(){
        File file = new File("./input/RPSstrategyGuide.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalScore = 0;
            while ((st = br.readLine()) != null){
                char theirMove = st.charAt(0);
                char desiredOutcome = st.charAt(2);
                char myMove = DayTwoUtils.getMyMove(theirMove, desiredOutcome);
                int scoreFromRound = DayTwoUtils.getScoreFromBattle(myMove, theirMove);
                totalScore += scoreFromRound;
                System.out.println("Battle Sum - " + scoreFromRound);
            }
            System.out.println("Sum - " + totalScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static char getMyMove(char theirMove, char desiredOutcome) {
        // X = Lose
        // Y = Draw
        // Z = Win
        Outcomes outcome = switch (desiredOutcome){
            case 'X' -> Outcomes.Loss;
            case 'Y' -> Outcomes.Draw;
            case 'Z' -> Outcomes.Win;
            default -> throw new IllegalStateException("Unexpected value: " + desiredOutcome);
        };

        return evalMyMove(outcome,theirMove);
    }

    private static int getScoreFromBattle(char myMove, char theirMove) {
        Outcomes outcome = doBattle(myMove, theirMove);
        int outcomeScore = switch (outcome){
            case Win -> 6;
            case Draw -> 3;
            case Loss -> 0;
        };

        System.out.println("Outcome - " + outcome);
        int shapeScore = 0;
        switch (myMove){
            case rock -> shapeScore = 1;
            case paper -> shapeScore = 2;
            case scissors -> shapeScore = 3;
        }

        return outcomeScore + shapeScore;
    }

    private static Outcomes doBattle(char myMove, char theirMove) {
        // rock > paper > scissor
        if (myMove == rock){
            if (theirMove == theirPaper){
                return Outcomes.Loss;
            } else if (theirMove == theirRock) {
                return Outcomes.Draw;
            } else {
                return Outcomes.Win;
            }
        } else if (myMove == paper) {
            if (theirMove == theirScissors){
                return Outcomes.Loss;
            } else if (theirMove == theirPaper) {
                return Outcomes.Draw;
            } else {
                return Outcomes.Win;
            }
        } else {
            if (theirMove == theirRock){
                return Outcomes.Loss;
            } else if (theirMove == theirScissors) {
                return Outcomes.Draw;
            } else {
                return Outcomes.Win;
            }
        }
    }

    private static char evalMyMove(Outcomes desiredOutcome, char theirMove) {
        switch (desiredOutcome){
            case Loss:
                switch (theirMove){
                    case theirRock:
                        return scissors;
                    case theirPaper:
                        return rock;
                    case theirScissors:
                        return paper;
                }
            case Draw:
                switch (theirMove){
                    case theirRock:
                        return rock;
                    case theirPaper:
                        return paper;
                    case theirScissors:
                        return scissors;
                }
            case Win:
                switch (theirMove){
                    case theirRock:
                        return paper;
                    case theirPaper:
                        return scissors;
                    case theirScissors:
                        return rock;
                }
        }
        return 0;
    }

    private enum Outcomes{
        Win,
        Draw,
        Loss
    }
}
