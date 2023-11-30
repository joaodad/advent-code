package Utils.TwentyTwentyTwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class DaySix {
    public static void runDaySixPart1(){
        File file = new File("./input/SignalStream.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                System.out.println(st);
                for (int i = 0; i < st.length()-3; i++) {
                    if (st.charAt(i) != st.charAt(i+1) && st.charAt(i) != st.charAt(i+2) && st.charAt(i) != st.charAt(i+3) &&
                            st.charAt(i+1) != st.charAt(i+2) && st.charAt(i+1) != st.charAt(i+3) &&
                            st.charAt(i+2) != st.charAt(i+3)){
                        System.out.println("Signal start: " + (i+4));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runDaySixPart2(){
        File file = new File("./input/SignalStream.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                System.out.println(st);
                int messageBufferEnd = getMessageBufferEnd(st);
                System.out.println("Signal start: " + messageBufferEnd);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getMessageBufferEnd(String st) {
        for (int i = 0; i < st.length(); i++) {
            HashSet<Character> markerSet = new HashSet<>();
            for (int j = 0; j < 14 ; j++){
                markerSet.add(st.charAt(j+i));
            }
            if (markerSet.size() == 14){
                System.out.println("Set: " + markerSet);
                System.out.println("Index: " + (i));
                System.out.println("Char: " + st.charAt(i));
                return i+14;
            }
        }
        return 0;
    }
}
