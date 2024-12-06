import java.io.File;
import java.io.FileNotFoundException;
import java.lang.constant.PackageDesc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_part1 {
    public static void main(String[] args) {
        try {
            File input = new File("input.txt");
            Scanner scanner = new Scanner(input);
            String regex = "(\\d+)\\|(\\d+)";

            int total = 0;

            int[][] rules = new int[10000][2];
            int rules_tracker = 0;
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
                    boolean valid = true;
                    //check all rules now for each element
                    for(int i = 0; i < rules_tracker; i++) {
                        if(!(line.indexOf(rules[i][0]) < line.indexOf(rules[i][1])) && (line.indexOf(rules[i][0]) != -1) && (line.indexOf(rules[i][1]) != -1)) {
                            valid = false;
                            break;
                        }
                    }
                    //get total
                    if(valid) {
                        total += line.get(line.size()/2);
                    }
                }
            }
            System.out.println(total);

    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
