package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    public WordleDictionary load(String fileName) throws IOException,DictionaryIsEmpty {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            try {
                while ((line = read.readLine()) != null) {
                    result.add(line);
                }
                return new WordleDictionary(result);
            } catch (DictionaryIsEmpty exc) {
                throw new DictionaryIsEmpty("Файл пуст");
            }
        }
    }
}
