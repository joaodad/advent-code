package Utils.TwentyTwentyTwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayEight {
    public static void runPart1(){
        File file = new File("./input/trees.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            List<List<Integer>> rows = new ArrayList<>();
            while ((st = br.readLine()) != null){
                List<Integer> row = new ArrayList<>();
                for (int i = 0; i < st.length(); i++){
                    row.add(Integer.parseInt(String.valueOf(st.charAt(i))));
                }
                rows.add(row);
            }
            int totalVisibleTrees = countVisibleTreesInGrid(rows);
            System.out.println("Total:" + totalVisibleTrees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runPart2(){
        File file = new File("./input/trees.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            List<List<Integer>> rows = new ArrayList<>();
            while ((st = br.readLine()) != null){
                List<Integer> row = new ArrayList<>();
                for (int i = 0; i < st.length(); i++){
                    row.add(Integer.parseInt(String.valueOf(st.charAt(i))));
                }
                rows.add(row);
            }
            int scenicScore = getBestScenicScore(rows);
            System.out.println("Score:" + scenicScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getBestScenicScore(List<List<Integer>> rows) {
        int bestScore = 0;
        for (int i = 1; i < rows.get(0).size()-1; i++) {
            List<Integer> row = rows.get(i);
            for (int j = 1; j < row.size()-1; j++){
                int treeToCheck = row.get(j);
                int score = getScoreForTree(treeToCheck,i,j,rows);
                if (score > bestScore){
                    bestScore = score;
                    System.out.println("Best Score: " + bestScore);
                    System.out.println("Index: i " + i + " , j " + j);
                    System.out.println("Best Tree: " + treeToCheck);
                }
            }
        }
        return bestScore;
    }

    private static int getScoreForTree(int treeToCheck, int i, int j, List<List<Integer>> rows) {
        // i is the row index
        // j is the column index
        boolean stillVisible = true;
        int knownSize = 99;
        int x = j-1, y = i-1;
        int topView = 0;
        int bottomView = 0;
        int leftView = 0;
        int rightView = 0;
        // Check Top. keep going while they are less
        // if any are taller, leave this while and move to next direction
        // if it makes it to the top without a taller tree, it's visible. return 1
        while (y >= 0 && stillVisible){
            topView++;
            if (rows.get(y).get(j) >= treeToCheck){
                stillVisible = false;
            }
            y--;
        }

        y = i + 1;
        stillVisible = true;
        // Check Bottom. keep going while they are less
        // if any are taller, leave this while and move to next direction
        // if it makes it to the top without a taller tree, it's visible. return 1
        while (y < knownSize && stillVisible){
            bottomView++;
            if (rows.get(y).get(j) >= treeToCheck){
                stillVisible = false;
            }
            y++;
        }

        stillVisible = true;
        // Check Left, keep going while they are less
        // if any are taller, leave this while and move to next direction
        // if it makes it to the top without a taller tree, it's visible. return 1
        while (x >= 0 && stillVisible){
            leftView++;
            if (rows.get(i).get(x) >= treeToCheck){
                stillVisible = false;
            }
            x--;
        }

        x = j + 1;
        stillVisible = true;
        // Check Right
        while (x < knownSize && stillVisible){
            rightView++;
            if (rows.get(i).get(x) >= treeToCheck){
                stillVisible = false;
            }
            x++;
        }
        if (i == 86 && j == 49){
            System.out.println("Top: " + topView);
            System.out.println("Bottom: " + bottomView);
            System.out.println("Left: " + leftView);
            System.out.println("Right: " + rightView);
            System.out.println("Tree to check: " + treeToCheck);
        }
        return topView*bottomView*leftView*rightView;
    }

    private static int countVisibleTreesInGrid(List<List<Integer>> rows) {
        // Start with Top and bottom rows
        int total = rows.get(0).size() + rows.get(rows.size()-1).size();
        //  Add first and last column
        total += ((rows.size()-2)*2);
        // Real heavy lifting
        // Moving through the rows
        for (int i = 1; i < rows.get(0).size()-1; i++) {
            List<Integer> row = rows.get(i);
            for (int j = 1; j < row.size()-1; j++){
                int treeToCheck = row.get(j);
                total += checkVisibility(treeToCheck,i,j,rows);
            }
        }
        return total;
    }

    private static int checkVisibility(int treeToCheck, int i, int j, List<List<Integer>> rows) {
        // i is the row index
        // j is the column index
        boolean stillVisible = true;
        int knownSize = 99;
        int x = j-1, y = i-1;
        // Check Top. keep going while they are less
        // if any are taller, leave this while and move to next direction
        // if it makes it to the top without a taller tree, it's visible. return 1
        while (y >= 0 && stillVisible){
            if (rows.get(y).get(j) >= treeToCheck){
                stillVisible = false;
            }
            y--;
        }
        if (stillVisible){
            return 1;
        }

        y = i + 1;
        stillVisible = true;
        // Check Bottom. keep going while they are less
        // if any are taller, leave this while and move to next direction
        // if it makes it to the top without a taller tree, it's visible. return 1
        while (y < knownSize && stillVisible){
            if (rows.get(y).get(j) >= treeToCheck){
                stillVisible = false;
            }
            y++;
        }
        if (stillVisible){
            return 1;
        }

        stillVisible = true;
        // Check Left, keep going while they are less
        // if any are taller, leave this while and move to next direction
        // if it makes it to the top without a taller tree, it's visible. return 1
        while (x >= 0 && stillVisible){
            if (rows.get(i).get(x) >= treeToCheck){
                stillVisible = false;
            }
            x--;
        }
        if (stillVisible){
            return 1;
        }

        x = j + 1;
        stillVisible = true;
        // Check Right
        while (x < knownSize && stillVisible){
            if (rows.get(i).get(x) >= treeToCheck){
                stillVisible = false;
            }
            x++;
        }
        if (stillVisible){
            return 1;
        }
        return 0;
    }
}
