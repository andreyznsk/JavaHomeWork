package ru.geekbrains.HW2;

import org.omg.PortableInterceptor.ServerRequestInfo;

import java.util.Random;
import java.util.Scanner;

public class Lession3 {
    public static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    public static int getNumberFromScanner(String message, int min, int max) {// запрашивает у пользователя число в заданных пределах
        int x;
        do {
            System.out.println(message);
            x = sc.nextInt();
        } while (x < min || x > max);
        return x;
    }
    public static void  printArrayWard(String[] wrd){// метод печати массива строк. Нужно доработать динамический перенос строки
        for (int i = 0; i < wrd.length; i++) {
            System.out.print(i + " - " + wrd[i] + ";");
            if(i==(10)) System.out.println();//TODO
            if(i==(20)) System.out.println();//TODO

        }
        System.out.println();
    }
    public static void GuessTheWorld(){//Метод угадай слово
        //Random random = new Random();
        String userWord;
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String pcWord = words[random.nextInt(words.length)];//Запоминаем случайное слово. Изменил границы
        //String pcWord = "123";
        printArrayWard(words); // Вывод всего набора слов
        do {

            System.out.print("Введите слово: ");
            //System.out.println(pcWord);
            userWord = sc.next();

            if (userWord.equals(pcWord)) {
                System.out.println("Поздравляем! Вы выиграли");
                break;
            }
            if (pcWord.charAt(0)==userWord.charAt(0)) {//цикл проверки введеных букв
                System.out.print("Вы почти угадали: ");
                int i = 0;
                        while(pcWord.charAt(i)==userWord.charAt(i)) {
                            System.out.print(pcWord.charAt(i));
                            i++;
                            if(i>(pcWord.length()-1)||i>(userWord.length()-1)) break;
                        }
                for (; i < 15; i++) System.out.print("#");

                System.out.println();
            }

        } while (!pcWord.equals(userWord));

    }
    public static void GuessNumberGame(){

        int playerNumber;

        int i = 0;
        int arMenu = 0;
        do {
            int pcNumber = random.nextInt(9); // ввели случайное число
            for (i = 0; i < 3; i++) {
                playerNumber = getNumberFromScanner("Угадай число от 0 до 9: ", 0, 9);
                if (playerNumber == pcNumber) {
                    System.out.println("Вы выиграли");
                    break;
                } else if (playerNumber > pcNumber) System.out.println("Ваше число больше");
                else System.out.println("Ваше число меньше");
            }
            if (i == 3) System.out.printf("Вы проиграли! Правильное число: %d\n", pcNumber);
            arMenu = getNumberFromScanner("1 - Играть снова\n0 - Выйти", 0, 1);
        } while (arMenu != 0);
    }

    public static void main(String[] args) {
      int arMenu = 0;
        do{
           arMenu = getNumberFromScanner("Меню\n1 - Игра угадай число\n2 - Игра угадай слово\n0 - Выход",0,2);
           switch (arMenu) {
               case 1: GuessNumberGame();
                        break;
               case 2: GuessTheWorld();
                        break;
               default: break;
           }
      }
      while (arMenu != 0);
        sc.close();
    }

}

