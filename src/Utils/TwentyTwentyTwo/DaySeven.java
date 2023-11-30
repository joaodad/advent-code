package Utils.TwentyTwentyTwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class DaySeven {
    public static void runPart1(){
        File file = new File("./input/fileDir.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            int totalFileSize = 0;
            Directory rootDirectory = new Directory("/");
            rootDirectory.isRoot = true;
            Directory currentDirectory = rootDirectory;
            while ((st = br.readLine()) != null){
                //System.out.println(st);
                if (st.charAt(0) == '$'){
                    String[] splitOffDir = st.split(" ");
                    String command = splitOffDir[1];
                    if (command.equals("cd")){
                        String dirName = splitOffDir[2];
                        if (dirName.equals("/")){
                            currentDirectory = rootDirectory;
                        } else if (dirName.equals("..")) {
                            currentDirectory = currentDirectory.parent;
                        } else {
                            currentDirectory = currentDirectory.innerDirectories.get(dirName);
                        }
                    } else if (command.equals("ls")) {
                        // Probably don't need to do anything
                        //System.out.println(st);
                    }
                } else {
                    String[] splitOffDirFile = st.split(" ");
                    if (splitOffDirFile[0].equals("dir") && !currentDirectory.innerDirectories.containsKey(splitOffDirFile[1])){
                        currentDirectory.innerDirectories.put(splitOffDirFile[1],new Directory(splitOffDirFile[1],currentDirectory));
                    } else if (!splitOffDirFile[0].equals("dir")){
                        currentDirectory.addFile(Integer.parseInt(splitOffDirFile[0]),splitOffDirFile[1]);
                    }
                }
            }
            System.out.println(rootDirectory);
            int totalSizeThatCanBeFreedUp = rootDirectory.getSizeOfEverythingWeCanDelete();
            // this needs to be 1297683
            // Part 2 answer needs to be 5756764
            System.out.println("Total deletable: " + totalSizeThatCanBeFreedUp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Directory {
        String dirName;
        Directory parent;
        HashMap<String, Directory> innerDirectories;
        HashMap<String,Integer> files;
        int totalSize = 0;
        int dirDepth = 1;
        boolean isRoot = false;


        Directory(String name){
            dirName = name;
            innerDirectories = new HashMap<>();
            files = new HashMap<>();
        }
        Directory(String name, Directory directory){
            parent = directory;
            dirName = name;
            dirDepth+= directory.dirDepth;
            innerDirectories = new HashMap<>();
            files = new HashMap<>();
        }
        int getSizeOfEverything(){
            int size = totalSize;
            if (!innerDirectories.isEmpty()){
                for (Directory d :innerDirectories.values()) {
                    size += d.getSizeOfEverything();
                }
            }
            return size;
        }
        void addFile(int size, String name){
            files.put(name, size);
            totalSize += size;
        }
        @Override
        public String toString() {
                StringBuilder stringBuilder = new StringBuilder("Directory: " + dirName + "\n    Files:\n");
                for (Entry<String, Integer> f : files.entrySet()) {
                    stringBuilder.append("    ---").append(f.getKey()).append("  Size: ").append(f.getValue()).append("\n");
                }
                stringBuilder.append("    --- Total size just in this dir: ").append(totalSize).append("\n");
                stringBuilder.append("    --- Total size: ").append(getSizeOfEverything()).append("\n");
                stringBuilder.append("    --- Total depth: ").append(dirDepth).append("\n");
                if (!innerDirectories.isEmpty()) {
                    for (Directory d : innerDirectories.values()) {
                        stringBuilder.append("  --  ").append(d.toString()).append("\n");
                    }
                }
                return stringBuilder.toString();
        }

        public int getSizeOfEverythingWeCanDelete() {
            int total = 0;
            for (Directory d:innerDirectories.values()) {
                if (d.getSizeOfEverything() <= 100000){
                    total += d.getSizeOfEverything();
                } else if (!d.innerDirectories.isEmpty()) {
                    total += d.getSizeOfEverythingWeCanDelete();
                }
            }
            return total;
        }
    }
}
