package ru.geekbrains.HW2;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by geekbrains on 11/3/20 - взял за основу коммантарии без кода с лекции
 */
public class Lesson4 {

    // Переменные - параметры игры
    public static int SIZE = 3;
    public static int dotsToWin = 3;
    //public static final int LINE_LIMIT = 15;

    // Константы • X O
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    // Переменные - игровое поле, работа с клавиатурой, случайные числа
    public static char[][] map;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    // Основной метод
    public static void main(String[] args) {

        //Запрос у игрока размера поля и количества выиграшных фишек.
        setGameLevel();
        System.out.println("граем на поле размера: " + SIZE + " Длинна выиграшной линии: " + dotsToWin);
        // Инициализация игрового поля
        initMap();

        // Вывод игрового поля
        printMap();

        // Главный игровой цикл
        while (true) {

            // Ход игрока
            System.out.println("Ход пользователя в формате Y, X");

            // Печать игрового поля
            humanTurn();
            // Проверка победителя
            if(checkWin(DOT_X)) {
                System.out.println("Выиграл Пользователь!!");
                printMap();
                break;
            }
            // Проверка полностью заполненного поля
            if (mapIsFull()) {
                printMap();
                System.out.println("Ничья!");
                break;
            }
            // Ход ИИ
            aiTurn();
            // Вывод игрового поля
            printMap();
            // Проверка победителя
            if(checkWin(DOT_O)) {
                System.out.println("Выиграл компьютер!");
                printMap();
                break;
            }
            // Проверка заполненности карты
            if(mapIsFull()) {
                System.out.println("Ничья!");
                 break;
            }
        }


        // Игра закончена

        System.out.println("Игра окончена!");
        // Закрываем консоль
        scanner.close();
    }

    private static boolean mapIsFull() {//метод проверки оставшихся ходов
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j]==DOT_EMPTY) return false;
                }

        }
        return true;
    }

    private static void setGameLevel() {// Метод установки размера поля и длинны выиграшной комбинации
        boolean wrong = false;//Флаг ошибки при непрвильном вооде

        switch (getNumberFromScanner("Введите размер поля\n1 - 3 X 3\n2 - 5 X 5\n3 - 10 x 10\n", 1,3)){
            case 1: SIZE = 3;
                    break;
            case 2: SIZE = 5;
                    break;
            default: SIZE = 10;
                    break;
        }

        do {//Цикл проверки правильности ввода данныхы в игру
        if(wrong) System.out.println("Количество фишек должнго быть больше 3 и меньше размера поля"); //
        dotsToWin = getNumberFromScanner("Введите длинну фишек для победы от 3 до " + SIZE + "\n", 3, 10);
        wrong = true;//если введено неправильное значение установить флаг ошибки
    } while (dotsToWin > SIZE);//Проверка соответствия размера поля и длинны выиграшной комбинации
    }

    public static int getNumberFromScanner(String message, int min, int max) {// запрашивает у пользователя число в заданных пределах
        int x;
        do {
            System.out.print(message);
            x = scanner.nextInt();
        } while (x < min || x > max);
        return x;
    }

    public static boolean checkHorizon(int offsetY, int offsetX, int length, char symb){//Метод проверки горизонталей и вертикалей
        boolean flagWinHor = true;
        boolean flagWinVert = true;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                flagWinHor &= map[i+offsetX][j+offsetY]==symb;
                flagWinVert &= map[j+offsetY][i+offsetX]==symb;
            }
            if (flagWinHor||flagWinVert) return true;
            else {
                flagWinHor = true;
                flagWinVert = true;
            }
        }
    return false;
    }

    /*public static boolean checkVert(int offsetY, int offsetX, int length, char symb){//Метод проверки всертикалей
        boolean flagWin = true;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                flagWin &= map[j+offsetY][i+offsetX]==symb;
            }
            if (flagWin) return flagWin;
            else flagWin = true;
        }
        return false;
    }*/

    public static boolean checkDiag(int offsetY, int offsetX, int length, char symb){//Метод проверки диагонали
        boolean flagWin = true;

            for (int j = 0; j < length; j++)
                flagWin &= map[j+offsetY][j+offsetX]==symb;

        return flagWin;
    }
    public static boolean checkinvDiag(int offsetY, int offsetX, int length, char symb){//Метод проверки обратной диагонали
        boolean flagWin = true;
        for (int j = 0; j < length; j++) flagWin &= map[j+offsetX][length-j+offsetY-1]==symb;
        return flagWin;
    }

    private static boolean checkWin(char symb) {//Метод проверки наличия выиграшной комбинации
        for (int i = 0; i <= SIZE- dotsToWin; i++) {//Цикл проверки всех квадратов размера DOTS_TO_WIN*DOTS_TO_WIN во всем поле
            for (int j = 0; j <= SIZE- dotsToWin; j++) {
                //Методы проверки малых квадратов во всех направлениях
                if(checkHorizon(i,j, dotsToWin,symb)) return true;// Цикл проверки по горизонтали
                //if(checkVert(i,j, dotsToWin,symb)) return true;//Цикл проверки по вертикали
                if(checkDiag(i,j, dotsToWin,symb)) return true;//Цикл проерки диагонали
                if(checkinvDiag(i,j, dotsToWin,symb)) return true;//Цикл проверки обратной диагнали
            }
        }
        return false;
        }


    private static void aiTurn() {
      int x = 0 ,y = 0;
      boolean ai_turn = false;
        for (int i = 0; i < SIZE; i++) {//цикл проерки если следующая комбинация является выиграшной то ходим туда
            for (int j = 0; j < SIZE; j++) {//Проходим весь массив
                if(isCellValid(i,j)) {//Можем постаивть в эту точку метку Х
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {//Проверяем является ли дання комбинация выиграшная
                        y = i;//запоминаем координаты выиграшной комбинации
                        x = j;
                        ai_turn = true;//и устанавдиваем метку хода Компьютера
                    }
                    map[i][j]=DOT_EMPTY;//Очищаем поле
                }

            }

        }
        if (ai_turn) {//Если была найдена выиграшная комбинация, то ставим метку О
            map[y][x] = DOT_O;
            return; //и выходим из метода
        }
        while (true) {//Ставим в любом месте, если блокировать нечего
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
            if(isCellValid(y,x)) {
                map[y][x] = DOT_O;
                break;}
        }
    }
    /**
     * Предоставляем ход игроку
     */
    private static void humanTurn() {//Метод хода игрока
        int x, y;
        while (true){
            y = getNumberFromScanner("Введиите Y: ", 1, SIZE) - 1;
            x = getNumberFromScanner("Введиите X: ", 1, SIZE) - 1;
            if(isCellValid(y,x)) {
                map[y][x] = DOT_X;
                break;}
        }
    }

    private static boolean isCellValid(int y, int x) {//Метод проверки вадиности хода
        if( x>SIZE | y>SIZE | x < 0 | y < 0) return false;

        return map[y][x] == DOT_EMPTY;
    }

    private static void printMap() {//Метод печать карты
        // Сделали отступ
        System.out.println();
        System.out.print("   ");

        // Верхняя "Легенда"
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();
        // Выводим игровое поле
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ",(i+1));
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // Дополнительный отступ
        System.out.println();
    }

    private static void initMap() {//Инициализация карты в заданных пределах
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;

            }

        }

    }

}