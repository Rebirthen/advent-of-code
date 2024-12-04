package year2024.day2;

import year2024.Reader;

import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        List<String> lines = Reader.getListFromFile("day2/test.txt");

       int sum = calculateSafeRowsWithProblemDampener(lines);

        System.out.printf("Day 2: %d\n", sum);
    }

    private static int calculateSafeRows(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            int[] places = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean isDecreasing = places[0] > places[1];
            boolean isSafe = true;
            for (int i = 1; i < places.length; i++) {
                int prev = places[i - 1];
                int curr = places[i];
                isSafe = isSafe && curr != prev;
                if (isDecreasing && curr < prev) {
                    isSafe = isSafe && prev-curr < 4;
                } else if (!isDecreasing && curr > prev) {
                    isSafe = isSafe && curr - prev < 4;
                }else{
                    isSafe = false;
                    break;
                }

            }
            System.out.println("Safe row: " + line + " is " + isSafe);
            sum+=isSafe ? 1 : 0;
        }
        return sum;
    }

    private static int calculateSafeRowsWithProblemDampener(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            int[] places = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            boolean isSafe = true;
            int left = 0;
            int right = 1;
            boolean isDecreasing = true;
            boolean isIncreasing = true;
            while(right < places.length) {
                 isDecreasing = isDecreasing && places[left] > places[right];
                 isIncreasing = isIncreasing && places[left] < places[right];
                    isSafe = (isDecreasing || isIncreasing) && Math.abs(places[left] - places[right]) < 4;
                if(!isSafe){
                    isSafe = advancedCalc(places);
                    break;
                }
                left++;
                right++;

            }
            System.out.println("Safe row: " + line + " is " + isSafe);
            sum+=isSafe ? 1 : 0;
        }
        return sum;
    }

    private static boolean advancedCalc(int[] places) {
        for (int i = 0; i < places.length; i++) {
            boolean isSafe = true;
            boolean isDecreasing = true;
            boolean isIncreasing = true;
            int left = 0;
            int right = 1;
            while(isSafe && right < places.length){
                if(i == left) {
                    left++;
                    right++;
                    continue;
                }else if(i == right) {
                    right++;
                    continue;
                }
                System.out.println("Here " + places[left] +" _____ "+ places[right]);
                isDecreasing = isDecreasing && places[left] > places[right];
                isIncreasing = isIncreasing && places[left] < places[right];
                isSafe = (isDecreasing || isIncreasing) && Math.abs(places[left] - places[right]) < 4 && Math.abs(places[left] - places[right]) >= 1;
                left++;
                right++;
            }
            if(isSafe) return true;
        }
        return false;
    }
}
