package dev.tools;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс-читатель, сериализирующий данные из файла в необходимый объект.
 */
public class AnimalReader {

    private static String fileToString(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    public static <T> T getClassFromFile(String path, Class<T> className) {
        Gson gson = new Gson();
        try{
            return gson.fromJson(fileToString(path), className);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
