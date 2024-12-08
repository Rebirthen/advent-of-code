package year2024.day7;

import year2024.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

public class Day7 {
    public static void main(String[] args) {
        List<String> lines = Reader.getListFromFile("day7/input.txt");
        long sum = calculatePart2WithConcatenation(lines);
        System.out.println("Day 7:" + sum);
    }

    private static long calculatePart1(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            long target = parseLong(parts[0]);
            boolean isvalid = backtrack(0, target, 0, splitBySpace(parts).map(Long::parseLong).collect(Collectors.toList()));
            sum += isvalid ? target : 0;
        }

        return sum;
    }

    private static Stream<String> splitBySpace(String[] parts) {
        return Arrays.stream(parts[1].trim().split(" "));
    }

    private static long calculatePart2WithConcatenation(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            long target = parseLong(parts[0]);
            long[] values = splitBySpace(parts).mapToLong(Long::parseLong).toArray();
            boolean isvalid = backtrackWithConcatenation(values[0], target, 1, values);
            sum += isvalid ? target : 0;
        }

        return sum;
    }


    private static boolean backtrack(long sum, long res, int index, List<Long> collect) {
        if (sum == res) return true;
        if (sum > res || index >= collect.size()) return false;
        return backtrack(sum + collect.get(index), res, index + 1, collect) || backtrack(sum * collect.get(index), res, index + 1, collect);
    }

    private static boolean backtrackWithConcatenation(long currentVal, long target, int i, long[] nums) {
        if (i >= nums.length) return currentVal == target;
        if(backtrackWithConcatenation(parseLong(String.format("%d%d", currentVal, nums[i])), target, i + 1, nums)) return true;
        if( backtrackWithConcatenation(currentVal + nums[i], target, i + 1, nums)) return true;
        return backtrackWithConcatenation(currentVal * nums[i], target, i + 1, nums);
    }


}
