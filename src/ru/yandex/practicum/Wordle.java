package ru.yandex.practicum;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) {

        try (PrintWriter log = new PrintWriter(new FileWriter("wordle.log", StandardCharsets.UTF_8), true)) {
            try {
                WordleDictionaryLoader loader = new WordleDictionaryLoader();
                WordleDictionary dictionary = loader.load("words_ru.txt");
                WordleGame game = new WordleGame(dictionary, log);
                log.println(game.getAnswer());
                play(game);
            } catch (Exception e){
                e.printStackTrace(log);
                System.out.println("Произошла ошибка");
            }
        } catch (IOException e){
            System.out.println("Не удалось создать лог-файл: " + e.getMessage());
        }

    }


    public static void play(WordleGame game){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вы попали в игру Wordle, чтобы отгадать слово у Вас будет 6 попыток, а также при нажатии Enter - компьютер будет давать Вам подсказки! Удачи!");

        while (!game.isFinished()){
            String word = scanner.nextLine();

            if (word.isBlank()){
                System.out.println(game.getHint());
                continue;
            }

            try {
                String result = game.makeMove(word);
                System.out.println(result);

                if (!game.getWin() && game.getSteps() > 0) {
                    System.out.println("У Вас осталось " + game.getSteps() + " попыток!");
                }
            } catch (WordNotInDictionary e){
                System.out.println(e.getMessage() + ". Введите слово ещё раз.");
            }
        }
        if (game.getWin()){
            System.out.println("Поздравляем Вы угадали слово");
            System.out.println(game.getAnswer());
        } else {
            System.out.println("Вы проиграли! Ваши попытки закончились!");
            System.out.println(game.getAnswer());
        }
    }



}
