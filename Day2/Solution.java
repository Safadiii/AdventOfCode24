import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Solution instance = new Solution();
        try {
            File input = new File("input.txt");
            Scanner myReader = new Scanner(input);
            int total = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] temp = data.split("\\s+");
                ArrayList<Integer> arr = new ArrayList<>();
                boolean safe = true;
                for(int i = 0; i < temp.length; i++) {
                    int a = Integer.valueOf(temp[i]);
                    arr.add(a);
                }
                int errors = 0;
                if(instance.checkSafe(arr)) {
                    total++;
                } else {
                    ArrayList<Integer> temporary;
                    for(int i = 0; i < arr.size(); i++) {
                        temporary = (ArrayList<Integer>) arr.clone();
                        temporary.remove(i);
                        if(instance.checkSafe(temporary)) {
                            total++;
                            break;
                        }
                    }
                }
            }
            myReader.close();
            System.out.println(total);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public boolean checkSafe(ArrayList<Integer> integers) {
        boolean increasing = false;
        boolean decreasing = false;
        boolean safe = true;
        if(integers.size() <= 1) {
            return true;
        } else {
            if(integers.get(0) < integers.get(1)) {
                increasing = true;
            } else if (integers.get(0) > integers.get(1)) {
                decreasing = true;
            }
        }
        for(int i = 0; i<integers.size()-1; i++) {
            if (Math.abs(integers.get(i) - integers.get(i + 1)) > 3 || Math.abs(integers.get(i) - integers.get(i + 1)) < 1) {
                safe = false;
            }
            if (integers.get(i) > integers.get(i + 1) && increasing) {
                safe = false;
            }
            if (integers.get(i) < integers.get(i + 1) && decreasing) {
                safe = false;
            }
        }
        return safe;
    }
}
