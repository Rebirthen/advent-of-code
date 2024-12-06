package year2024.day5;

import year2024.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {

    static Graph graph = new Graph();
    static int n;
    public static void main(String[] args) {
        List<String> lines = Reader.getListFromFile("day5/input.txt");
        processGraph(lines);
        assert lines != null;
        int sum = calculateReorderedValidLines(lines);
        System.out.println("DAY 5: "+ sum);
    }

    private static int calculateValidLines(List<String> lines) {
        int sum = 0;
        for (int i = n; i < lines.size(); i++) {
            int[] arr = Arrays.stream(lines.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            if (isValid(arr)) {
                sum +=arr[arr.length/2];
            }
        }
        return sum;
    }

    private static int calculateReorderedValidLines(List<String> lines) {
        int sum = 0;
        for (int i = n; i < lines.size(); i++) {
            int[] arr = Arrays.stream(lines.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            if (!isValid(arr)) {
                arr = reorder(arr);
            }
            sum +=arr[arr.length/2];
        }
        return sum;
    }

    private static int[] reorder(int[] arr) {
        int[] stack = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

        }
        return  stack;
    }

    private static boolean isValid(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (graph.adjacencyList.containsKey(arr[i + 1]) && graph.adjacencyList.get(arr[i + 1]).contains(arr[i])) {
                return false;
            }
        }
        return true;
    }

    private static void processGraph(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals("")) {
                n = i+1;
                break;
            }
            int[] line = Arrays.stream(lines.get(i).split("\\|")).mapToInt(Integer::parseInt).toArray();

            graph.addEdge(line[0], line[1]);
        }
    }
}

class Graph {
    Map<Integer, Set<Integer>> adjacencyList;

    Graph() {
        adjacencyList = new HashMap<>();
    }

    void addEdge(int from, int to) {
        adjacencyList.computeIfAbsent(from, k -> new HashSet<>()).add(to);
    }

}
