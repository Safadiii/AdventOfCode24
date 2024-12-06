import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    int[][] solutionsUsed = new int[5000][200];
    static int track = 0;
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
                    if(matrix[row][column] == 'M') {
                        total += instance.checkPossibilities(new int[]{row, column}, matrix);
                    }
                }
            }
            for(int k = 0; k < track; k++) {
                for(int j=0; j < instance.solutionsUsed[k].length; j++) {
                    System.out.print(instance.solutionsUsed[k][j]);
                }
                System.out.println("new");
            }
            System.out.println(track/2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int checkPossibilities(int[] position, char[][] matrix) {
        int[][] vectors = {{-1, -1}, {-1, +1}, {+1, -1}, {+1, +1}};
        int total = 0;
        for (int i = 0; i < vectors.length; i++) {
            if(checkVector(vectors[i], matrix, position)) {
                if(checkX(position, matrix, vectors[i])){
                    total++;
                }
            }
        }
        return total;
    }

    public boolean checkVector(int[] vector, char[][] matrix, int[] position) {
        StringBuilder sb = new StringBuilder();
        int[] currPos = position;
        if(position[0] >= matrix.length || position[1] >= matrix[0].length || position[0] < 0 || position[1] < 0)  {
            return false;
        }
        sb.append(matrix[position[0]][position[1]]);
        if((currPos[0] + 2*vector[0]) >= matrix.length || (currPos[0] + 2*vector[0]) < 0) {
            return false;
        }
        if((currPos[1] + 2*vector[1]) >= matrix[0].length || (currPos[1] + 2*vector[1]) < 0) {
            return false;
        }
        for(int i = 1; i < 3; i++) {
                currPos = new int[]{position[0] + i*vector[0], position[1] + i*vector[1]};
                sb.append(matrix[currPos[0]][currPos[1]]);
        }

        if(sb.toString().equals("MAS")) {
            return true;
        }
        return false;
    }

    public boolean checkX(int[] position, char[][] matrix, int[] vector) {
        StringBuilder sb = new StringBuilder();
        sb.append(vector[0]);
        sb.append(vector[1]);
        switch(sb.toString()) {
            case "-1-1":
                if(checkVector(new int[]{-1, +1}, matrix, new int[]{position[0], position[1]-2}) || checkVector(new int[]{+1, -1}, matrix, new int[]{position[0]-2, position[1]})) {
                    int[] positions = {position[0], position[0]-2, position[1], position[1]-2};
                    if(!checkSolutionUsed(positions, solutionsUsed)) {
                        solutionsUsed[track] = positions;
                        track++;
                    }
                    return true;
                }
                break;
            case "-11":
                if(checkVector(new int[]{-1, -1}, matrix, new int[]{position[0], position[1]+2}) || checkVector(new int[]{+1, +1}, matrix,new int[]{position[0]-2, position[1]})) {
                    int[] positions = {position[0], position[0]-2, position[1], position[1]+2};
                    if(!checkSolutionUsed(positions, solutionsUsed)) {
                        solutionsUsed[track] = positions;
                        track++;
                    }
                    return true;
                }
                break;
            case "1-1":
                if(checkVector(new int[]{1, 1}, matrix,new int[]{position[0], position[1]-2}) || checkVector(new int[]{-1, -1}, matrix,new int[]{position[0]+2, position[1]})) {
                    int[] positions ={position[0], position[0]+2, position[1], position[1]-2};
                    if(!checkSolutionUsed(positions, solutionsUsed)) {
                        solutionsUsed[track] = positions;
                        track++;
                    }
                    return true;
                }
                break;
            case "11":
                if(checkVector(new int[]{1, -1}, matrix,new int[]{position[0], position[1]+2}) || checkVector(new int[]{-1, 1}, matrix,new int[]{position[0]+2, position[1]})) {
                    int[] positions = {position[0], position[0]+2, position[1], position[1]+2};
                    if(!checkSolutionUsed(positions, solutionsUsed)) {
                        solutionsUsed[track] = positions;
                        track++;
                    }
                    return true;
                }
                break;
        }
    return false;
    }

    public boolean checkSolutionUsed(int[] solution, int[][] solutionsUsed) {
        for(int[] row : solutionsUsed) {
            if(Arrays.equals(row, solution)) {
            return true;
            }
        }
        return false;
    }
}
