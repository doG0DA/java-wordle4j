package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleDictionaryTest {

    @Test
    void normalize() {
        assertEquals("ежика", WordleDictionary.normalize(" Ёжика "));
    }

    @Test
    void isValidWord() {
        assertTrue(WordleDictionary.isValidWord("герой"));
        assertFalse(WordleDictionary.isValidWord("кот"));
        assertFalse(WordleDictionary.isValidWord("hello"));
    }

    @Test
    void contains() {
        WordleDictionary dictionary = new WordleDictionary(List.of("герой", "гонец"));
        assertTrue(dictionary.contains("герой"));
        assertFalse(dictionary.contains("мороз"));
    }

    @Test
    void constructorKeepsOnlyValidWords() {
        WordleDictionary dictionary = new WordleDictionary(List.of("ГЕРОЙ", "кот", "hello"));
        assertEquals(1, dictionary.size());
        assertTrue(dictionary.contains("герой"));
    }

    @Test
    void compare() {
        assertEquals("+^-^-", WordleDictionary.compare("гонец", "герой"));
        assertEquals("+++++", WordleDictionary.compare("герой", "герой"));
    }
}