import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int total = 0;
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
        Collections.sort(firstList);
        Collections.sort(secondList);

        while(!firstList.isEmpty()) {
            total += Math.abs(firstList.getFirst() - secondList.getFirst());
            firstList.remove(firstList.getFirst());
            secondList.remove(secondList.getFirst());
        }
        System.out.println(total);

    }

}

