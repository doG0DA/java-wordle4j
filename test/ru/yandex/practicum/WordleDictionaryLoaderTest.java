package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleDictionaryLoaderTest {

    @Test
    void loadRealDictionary() throws Exception {
        WordleDictionary dictionary = new WordleDictionaryLoader().load("words_ru.txt");
        assertTrue(dictionary.size() > 0);
    }
}