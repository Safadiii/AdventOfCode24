import java.io.File;
import java.io.FileNotFoundException;
import java.lang.constant.PackageDesc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_part2 {
    public static int[][] rules = new int[10000][2];
    public static int rules_tracker = 0;
    public static void main(String[] args) {
        Solution_part2 instance = new Solution_part2();
        try {
            File input = new File("input.txt");
            Scanner scanner = new Scanner(input);
            String regex = "(\\d+)\\|(\\d+)";

            int total = 0;

            Pattern pattern = Pattern.compile(regex);
            //fill the matrix
            while(scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Matcher matcher = pattern.matcher(data);
                //case where the empty line exists
                if(data.isEmpty()) {
                    continue;
                }
                //case where its a rule add it to rule array
                if(matcher.matches()) {
                    rules[rules_tracker][0] = Integer.valueOf(matcher.group(1));
                    rules[rules_tracker][1] = Integer.valueOf(matcher.group(2));
                    rules_tracker++;
                } else {
                    //convert the non rule line to an array of integers
                    String[] temp = data.split(",");
                    int[] arr = new int[temp.length];
                    for(int i = 0; i < temp.length; i++) {
                        arr[i] = Integer.parseInt(temp[i].trim());
                    }
                    //convert array to arraylist for easier index manipulation
                    ArrayList<Integer> line = new ArrayList<>();
                    for(int x : arr) {
                        line.add(x);
                    }
                    if(!instance.isValid(line)) {
                        instance.makeValid(line);
                        total += line.get(line.size()/2);
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < line.size(); i++) {
                            sb.append(line.get(i).toString() + " ");
                        }
                        System.out.println(sb);
                    }
                }
            }
            System.out.println(total);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> void swapElements(ArrayList<T> list, int i, int j) {
        // Swapping using Collections.swap
        Collections.swap(list, i, j);
    }

    public boolean isValid(ArrayList<Integer> line) {
        boolean valid = true;
        for(int i = 0; i < rules_tracker; i++) {
            if(!(line.indexOf(rules[i][0]) < line.indexOf(rules[i][1])) && (line.indexOf(rules[i][0]) != -1) && (line.indexOf(rules[i][1]) != -1)) {
                valid = false;
                return valid;
            }
        }
        return valid;
    }

    public void makeValid(ArrayList<Integer> line) {
        while(!isValid(line)) {
            for(int i = 0; i < rules_tracker; i++) {
                if(line.indexOf(rules[i][0]) < 0 || line.indexOf(rules[i][1]) < 0) {
                    continue;
                }
                if(line.indexOf(rules[i][0]) > line.indexOf(rules[i][1])) {
                    System.out.println(rules[i][0]);
                    System.out.println(rules[i][1]);
                    swapElements(line, line.indexOf(rules[i][0]), line.indexOf(rules[i][1]));
                }
            }
        }
    }
}
