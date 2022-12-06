import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class DayFiveUtils {
    public static void runDayFivePart1(){
        File file = new File("./input/Crates.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            boolean buildingStacks = true;
            List<Stack<Character>> stacks = new ArrayList<>();
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            while ((st = br.readLine()) != null){
                // First, build out stacks
                // 9 stacks
                // Newline
                if (st.equals("")){
                    buildingStacks = false;
                    System.out.println(stacks);
                }
                if (buildingStacks) {
                    int charIndex = 1;
                    for (int i = 0; i < 9; i++){
                        if (st.charAt(charIndex) != ' ') {
                            stacks.get(i).add(0, st.charAt(charIndex));
                        }
                        charIndex+=4;
                    }
                    // [M] [Z] [H] [P] [N] [W] [P] [L] [C]
                    System.out.println(st);
                } else if (!st.equals("")) {
                //    System.out.println("The moves: " + st);
                    moveCrate(stacks, st);
                }
            }
            System.out.println(stacks);
            for (Stack<Character> stack: stacks
            ) {
                System.out.println(stack.pop());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runDayFivePart2(){
        File file = new File("./input/Crates.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            boolean buildingStacks = true;
            List<Stack<Character>> stacks = new ArrayList<>();
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            stacks.add(new Stack<>());
            while ((st = br.readLine()) != null){
                // First, build out stacks
                // 9 stacks
                // Newline
                if (st.equals("")){
                    buildingStacks = false;
                    System.out.println(stacks);
                }
                if (buildingStacks) {
                    int charIndex = 1;
                    for (int i = 0; i < 9; i++){
                        if (st.charAt(charIndex) != ' ') {
                            stacks.get(i).add(0, st.charAt(charIndex));
                        }
                        charIndex+=4;
                    }
                    // [M] [Z] [H] [P] [N] [W] [P] [L] [C]
                    System.out.println(st);
                } else if (!st.equals("")) {
                    //    System.out.println("The moves: " + st);
                    moveCratesTogether(stacks, st);
                }
            }
            System.out.println(stacks);
            for (Stack<Character> stack: stacks
            ) {
                System.out.println(stack.pop());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void moveCratesTogether(List<Stack<Character>> stacks, String move) {
        String[] moveSplit = move.split(" ");
        int cratesToMove = Integer.parseInt(moveSplit[1]);
        int sourceCrate = Integer.parseInt(moveSplit[3]);
        int destinationCrate = Integer.parseInt(moveSplit[5]);
        Stack<Character> cratesGettingMoved = new Stack<>();
        try {
            for (; cratesToMove > 0; cratesToMove--) {
                char popped = stacks.get(sourceCrate-1).pop();
                cratesGettingMoved.push(popped);
            }
            while (!cratesGettingMoved.isEmpty()){
                stacks.get(destinationCrate-1).push(cratesGettingMoved.pop());
            }
        } catch (EmptyStackException e){
            System.out.println(move);
            System.out.println(stacks);
        }
    }

    private static void moveCrate(List<Stack<Character>> stacks, String move) {
        String[] moveSplit = move.split(" ");
        int cratesToMove = Integer.parseInt(moveSplit[1]);
        int sourceCrate = Integer.parseInt(moveSplit[3]);
        int destinationCrate = Integer.parseInt(moveSplit[5]);
        try {
            for (; cratesToMove > 0; cratesToMove--) {
                char popped = stacks.get(sourceCrate-1).pop();
                stacks.get(destinationCrate-1).push(popped);
            }
        } catch (EmptyStackException e){
            System.out.println(move);
            System.out.println(stacks);
        }
    }
}
