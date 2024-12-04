package year2024.day4;

import year2024.Reader;

import java.util.List;

public class Day4 {
    static TrieNode root;
    static int m;
    static int n;
    static int sum;

    public static void main(String[] args) {
        List<String> lines = Reader.getListFromFile("day4/input.txt");
        char[][] grid = createMatrix(lines);
        root = createTrie();
        m = grid.length;
        n = grid[0].length;
        calculateTotalWords(grid);

        System.out.printf("Day 4: %d\n", sum);
    }

    private static TrieNode createTrie() {
        TrieNode root = new TrieNode();
        String word = "XMAS";
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'A';
            curr.children[index] = new TrieNode();
            curr = curr.children[index];
        }
        curr.isWord = true;

        return root;
    }

    private static char[][] createMatrix(List<String> lines) {
        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        return grid;
    }

    private static void calculateTotalWords(char[][] grid) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                 checkPaths(grid, i, j);
            }
        }
    }

    private static void checkPaths(char[][] grid, int i, int j) {
        for(Direction direction : Direction.values()) {
            dfs(grid, i, j, root, 1, direction);
        }
    }

    private static boolean dfs(char[][] grid, int i, int j, TrieNode curr, int count, Direction dir) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && count <= 4) {
            TrieNode next = curr.children[grid[i][j] - 'A'];
            if (next == null) return false;
            if (next.isWord && count == 4) {
                sum++;
                return true;
            }

            return dfs(grid, i + dir.i, j + dir.j, next, count + 1, dir);
        }
        return false;

    }
}

enum Direction {
    UP(1, 0),
    DOWN(-1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    DIAGONAL_UP(1, 1),
    DIAGONAL_DOWN(-1, -1),
    DIAGONAL_LEFT(-1, 1),
    DIAGONAL_RIGHT(1, -1);

    final int i;
    final int j;

    Direction(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
}
