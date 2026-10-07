package ru.yandex.practicum;

public class WordNotInDictionaryException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Слово отсутствует в словаре";

    public WordNotInDictionaryException() {
        super(DEFAULT_MESSAGE);
    }
}
