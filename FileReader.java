import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

    List<String> readFileContents(String fileName) {
        String path = "resources/" + fileName;
        try {
            return Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }

    }


}
