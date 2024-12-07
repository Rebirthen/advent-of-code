package year2024.day6;

import year2024.Reader;
import java.util.List;

public class Day6 {
    static boolean[][] matrix;
    static int x;
    static int y;
    static Node order = new Node();
    static int[][] visited;
    public static void main(String[] args) {
        List<String> lines = Reader.getListFromFile("day6/input.txt");
        preprocess(lines);
        makeLinkedList();
        int sum =  dfs(1, order)+2;
        System.out.println("Day 6 sum: " + sum);
    }

    private static void makeLinkedList() {
        Node curr = order;
        Node prev = null;
        for(Direction direction : Direction.values()) {
            curr.direction = direction;
            curr.next = new Node();
            prev = curr;
            curr = curr.next;
        }
        prev.next = order;
    }

    private static int dfs(int count, Node node) {
        if(x<0 || x>= matrix.length || y<0 || y>= matrix[0].length) return count;
        if(matrix[x][y]) {
            x-= node.direction.i;
            y-= node.direction.j;
            node  = node.next;
            count --;
            visited[x][y] = 9;
        }
        count += visited[x][y] == 0 ? 1: 0;
        visited[x][y]++;
        x+= node.direction.i;
        y+= node.direction.j;

        return dfs(count, node);
    }

    private static void preprocess(List<String> lines) {
        matrix = new boolean[lines.size()][lines.get(0).length()];
        visited = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                matrix[i][j] = lines.get(i).charAt(j) == '#';
                if(lines.get(i).charAt(j) == '^'){
                    x= i;
                    y= j;
                }
            }
        }
    }

}

enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);
    final int i;
    final int j;

    Direction(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Node {
    Node next;
    Direction direction;

    Node(Direction direction) {
        next = null;
        this.direction = direction;
    }

    Node() {
        next = null;
        this.direction = null;
    }
}

