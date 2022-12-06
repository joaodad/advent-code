import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DayFourUtils {

    public static void runDayFourPart1(){
        File file = new File("./input/CleaningSections.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalOverlaps = 0;
            while ((st = br.readLine()) != null){
                String[] elfAssignments = st.split(",");
                if (cleaningFullyOverlaps(elfAssignments)){
                    totalOverlaps++;
                }
            }
            System.out.println("Total " + totalOverlaps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runDayFourPart2(){
        File file = new File("./input/CleaningSections.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalOverlaps = 0;
            while ((st = br.readLine()) != null){
                String[] elfAssignments = st.split(",");
                if (cleaningOverlaps(elfAssignments)){
                    totalOverlaps++;
                }
            }
            System.out.println("Total " + totalOverlaps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean cleaningFullyOverlaps(String[] elfAssignments) {
        String[] elf1SectionStrings = elfAssignments[0].split("-");
        String[] elf2SectionStrings = elfAssignments[1].split("-");
        int[] elf1Sections = new int[] {
                Integer.parseInt(elf1SectionStrings[0]),
                Integer.parseInt(elf1SectionStrings[1])
        };
        int[] elf2Sections = new int[] {
                Integer.parseInt(elf2SectionStrings[0]),
                Integer.parseInt(elf2SectionStrings[1])
        };
        return fullyOverlaps(elf1Sections, elf2Sections) || fullyOverlaps(elf2Sections, elf1Sections);
    }

    private static boolean fullyOverlaps(int[] elf1Sections, int[] elf2Sections) {
        return elf1Sections[0] >= elf2Sections[0] && elf1Sections[1] <= elf2Sections[1];
    }

    private static boolean cleaningOverlaps(String[] elfAssignments) {
        String[] elf1SectionStrings = elfAssignments[0].split("-");
        String[] elf2SectionStrings = elfAssignments[1].split("-");
        int[] elf1Sections = new int[] {
                Integer.parseInt(elf1SectionStrings[0]),
                Integer.parseInt(elf1SectionStrings[1])
        };
        int[] elf2Sections = new int[] {
                Integer.parseInt(elf2SectionStrings[0]),
                Integer.parseInt(elf2SectionStrings[1])
        };
        return overlaps(elf2Sections, elf1Sections);
    }

    private static boolean overlaps(int[] elf1Sections, int[] elf2Sections) {
        return !(elf1Sections[1] < elf2Sections[0] || elf1Sections[0] > elf2Sections[1]);
    }
}
