package ru.yandex.practicum;

import java.util.*;


/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final Random random = new Random();
    private List<String> words;
    private final HashSet<String> setOfWords = new HashSet<>();

    public WordleDictionary(List<String> rawWords) {
        List<String> wordle = new ArrayList<>();
        for (String word : rawWords) {
            word = normalize(word);
            if (isValidWord(word) && setOfWords.add(word)) {
                wordle.add(word);
            }
        }
        this.words = wordle;
    }


    public static String normalize(String word) {
        return word.toLowerCase().replace('ё', 'е').trim();
    }

    public static boolean isValidWord(String word) {
        if (word.length() == 5) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c < 'а' || c > 'я') {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    public boolean contains(String word) {
        return words.contains(word);
    }

    public long size() {
        return words.size();
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public List<String> getWords() {
        return words;
    }
    public static String compare(String guess, String answer) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);

            if (answer.charAt(i) == c) {
                result.append('+');
            } else if (answer.indexOf(c) >= 0) {
                result.append('^');
            } else {
                result.append('-');
            }
        }
        return result.toString();
    }
}
