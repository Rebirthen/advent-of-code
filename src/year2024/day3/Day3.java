package year2024.day3;

import year2024.Reader;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        List<String> lines = Reader.getListFromFile("day3/input.txt");

        int sum = calculateWithDoInstructions(lines);

        System.out.printf("Day 2: %d\n", sum);
    }

    private static int calculateInstructions(List<String> lines) {
        // Define the regex pattern to match only "mull(number1, number2)"
        int sum = 0;
        for (String line : lines) {
            String pattern = "mul\\((\\d+),(\\d+)\\)";

            // Compile the pattern
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);

            while (matcher.find()) {
                // Extract and format numbers
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                System.out.println(matcher.group(1) + "*" + matcher.group(2));

            }
        }
        return sum;

    }

    public static int calculateWithDoInstructions(List<String> lines) {
        int sum = 0;
        // Define the regex patterns for instructions
        String mulPattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        String doPattern = "do\\(\\)";
        String dontPattern = "don't\\(\\)";

        // Compile patterns
        Pattern mulRegex = Pattern.compile(mulPattern);
        Pattern doRegex = Pattern.compile(doPattern);
        Pattern dontRegex = Pattern.compile(dontPattern);
        Queue<String> queue = new LinkedList<>();
        for (String line : lines) {
            // Matcher for all instructions
            Matcher instructionMatcher = Pattern.compile("(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))").matcher(line);



            while (instructionMatcher.find()) {
                String instruction = instructionMatcher.group();
                queue.add(instruction);
                // Check if it's a `do()` instruction
            }




        }

        int curr = 1;
        while(!queue.isEmpty()) {
            String instruction = queue.poll();
            if (doRegex.matcher(instruction).matches()) {
                curr=1;
            }
            // Check if it's a `don't()` instruction
            else if (dontRegex.matcher(instruction).matches()) {
                curr=0;
            }
            // Process `mul` instructions only if enabled
            else if (mulRegex.matcher(instruction).matches() && curr>0) {
                Matcher mulMatcher = mulRegex.matcher(instruction);
                if (mulMatcher.matches()) {
                    int number1 = Integer.parseInt(mulMatcher.group(1));
                    int number2 = Integer.parseInt(mulMatcher.group(2));
                    sum+= number1 * number2;
                }
            }
        }

        return sum;
    }

}
