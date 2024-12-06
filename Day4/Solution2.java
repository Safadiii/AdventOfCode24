import java.io.File;
import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Solution instance = new Solution();
        try {
            File input = new File("input1.txt");
            Scanner scanner = new Scanner(input);
            int total = 0;
            char[][] matrix = new char[200][200];
            int i = 0;
            //fill the matrix
            while(scanner.hasNextLine()) {
                String data = scanner.nextLine();
                char[] row = data.toCharArray();
                matrix[i++] = row;
            }
            //iterate over each letter
            for(int row = 0; row < matrix.length; row++) {
                for(int column = 0; column < matrix[row].length; column++) {
                    StringBuilder sb = new StringBuilder();
                    if(matrix[row][column] > 0) {
                        sb.append(matrix[row][column]);
                    }
                    if(sb.isEmpty()) {
                        continue;
                    }
                    System.out.println(sb);
                    if(matrix[row][column] == 'X') {
                        total += instance.checkPossibilities(new int[]{row, column}, matrix);
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int checkPossibilities(int[] position, char[][] matrix) {
        int[][] vectors = {{-1, -1}, {-1, 0}, {-1, +1}, {0, -1}, {0, +1}, {+1, -1}, {+1, 0}, {+1, +1}};
        int total = 0;
        for (int i = 0; i < vectors.length; i++) {
            if(checkVector(vectors[i], matrix, position)) {total++;}
        }
        return total;
    }

    public boolean checkVector(int[] vector, char[][] matrix, int[] position) {
        StringBuilder sb = new StringBuilder();
        int[] currPos = position;
        sb.append(matrix[position[0]][position[1]]);
        if((position[0] + 3*vector[0]) >= matrix.length || (position[0] + 3*vector[0]) < 0) {
            return false;
        }
        if((position[1] + 3*vector[1]) >= matrix[0].length || (position[1] + 3*vector[1]) < 0) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            currPos = new int[]{position[0] + i*vector[0], position[1] + i*vector[1]};
            sb.append(matrix[currPos[0]][currPos[1]]);
        }
        if(sb.toString().equals("XMAS")) {
            return true;
        }
        return false;
    }
}

