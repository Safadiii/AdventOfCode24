import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution_part2 {
    public static void main(String[] args) {
        int similarity = 0;
        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();
        try {
            File input = new File("input.txt");
            Scanner myReader = new Scanner(input);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] temp = data.split("\\s+");
                firstList.add(Integer.valueOf(temp[0]));
                secondList.add(Integer.valueOf(temp[1]));
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for(int i = 0; i < firstList.size(); i++) {
            int sim = Collections.frequency(secondList, firstList.get(i)) * firstList.get(i);
            similarity += sim;
        }
        System.out.println(similarity);
    }
}
