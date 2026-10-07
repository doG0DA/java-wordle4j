package ru.yandex.practicum;

import java.io.*;
import java.util.*;

public class WordleGame {

    private final Set<String> givenHints = new HashSet<>();
    private final String answer;
    private final PrintWriter log;
    private int steps;
    private final WordleDictionary dictionary;
    private boolean win;
    private LinkedHashMap<String, String> history = new LinkedHashMap<>();
    private final Random random = new Random();



    public WordleGame(WordleDictionary dictionary,String answer, PrintWriter log) {
        this.dictionary = dictionary;
        this.answer = answer;
        this.steps = 6;
        this.log = log;
    }

    public WordleGame(WordleDictionary dictionary, PrintWriter log) {
        this(dictionary, dictionary.getRandomWord(), log);
    }

    public void isWin() {
        win = true;
    }



    public boolean isFinished() {
        return win || steps == 0;
    }

    public String makeMove(String word) throws WordNotInDictionaryException {
        String normalized = dictionary.normalize(word);
        validate(normalized);
        steps--;
        String result = checkWord(normalized);
        history.put(normalized, result);
        log.println("Ход " + word + "--> " + result + " осталось шагов: " + steps);
        return result;
    }
    private void validate(String word) throws WordNotInDictionaryException{
        if (!dictionary.isValidWord(word)) {
            throw new WordNotInDictionaryException();
        }
        if (!dictionary.contains(word)) {
            throw new WordNotInDictionaryException();
        }
    }

    private String checkWord(String word) {
        String result = WordleDictionary.compare(word, answer);
        if (word.equals(answer)) {
            win = true;
            return "+++++";
        }
        return result;
    }

    private boolean matchesHistory(String candidate) {
        for (Map.Entry<String, String> move : history.entrySet()) {
            String guess = move.getKey();
            String realResult = move.getValue();
            if (!WordleDictionary.compare(guess, candidate).equals(realResult)) {
                return false;
            }
        }
        return true;
    }

    public String getHint() {
        List<String> candidates = new ArrayList<>();
        for (String word : dictionary.getWords()) {
            if (history.containsKey(word) || givenHints.contains(word)) {
                continue;
            }
            if (matchesHistory(word)) {
                candidates.add(word);
            }
        }
        if (candidates.isEmpty()) {
            throw new IllegalStateException("Нет подходящих слов для подсказки");
        }
        String hint = candidates.get(random.nextInt(candidates.size()));
        givenHints.add(hint);
        log.println("Подсказка: " + hint + ", подходящих слов: " + candidates.size());
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public int getSteps() {
        return steps;
    }

    public boolean getWin() {
        return win;
    }









}
