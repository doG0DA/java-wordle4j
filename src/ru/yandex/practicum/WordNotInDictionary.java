package ru.yandex.practicum;

public class WordNotInDictionary extends RuntimeException {
    public WordNotInDictionary(String message) {
        super(message);
    }
}
