package year2024;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    public static List<String> getListFromFile(String input) {
        Path filePath = Paths.get("src/year2024/"+input);
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            return lines;
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        return null;
    }
}