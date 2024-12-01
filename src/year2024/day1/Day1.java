package year2024.day1;

import java.util.*;

import year2024.Reader;

public class Day1 {
    public static void main(String[] args) {

        List<String> lines = Reader.getListFromFile("day1/input.txt");

        int sum = calculateSimilarityScore(lines);

        System.out.println("sum:"+ sum);
    }




    public static int calculateDistance(List<String> lines){
        int res = 0;
        List<Integer> place1 = new ArrayList<>();
        List<Integer> place2 = new ArrayList<>();
        for(String line : lines){
            int[] numbers = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            place1.add(numbers[0]);
            place2.add(numbers[1]);
        }
        place1.sort(Integer::compareTo);
        place2.sort(Integer::compareTo);

        while(!place1.isEmpty())
        {
            res+=Math.abs(place1.remove(0) - place2.remove(0));
        }
        return res;
    }

    public static int calculateSimilarityScore(List<String> lines){
        int res = 0;
        List<Integer> leftList = new ArrayList<>();
        Map<Integer, Integer> rightListToCount = new HashMap<>();
        for(String line : lines){
            int[] numbers = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            leftList.add(numbers[0]);
            rightListToCount.put(numbers[1], rightListToCount.getOrDefault(numbers[1], 0) + 1);
        }

        while(!leftList.isEmpty())
        {
            int num = leftList.remove(0);
            res+=Math.abs(num * rightListToCount.getOrDefault(num, 0));
        }
        return res;
    }



}
