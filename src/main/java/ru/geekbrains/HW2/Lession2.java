package ru.geekbrains.HW2;

public class Lession2 {
    public static void PrintArry(int[] a) { //метод печать массива
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    public static void PrintArryN(int[][] a) { //метод печать 2х мерного массива
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            } System.out.println();
        }
        System.out.println();
    }

    public static int[] ChangeOneToZero(int[] a) { //метод п. 1
        for (int i = 0; i < a.length; i++) {
            if (a[i]==1) a[i] = 0;
            else a[i]=1; // Исправил в соответсивии с заданием
            //else System.out.println("Erorr! Element of array №[" + i + "] Is Not Binary" );
        }
       return a;
    }
    public static int[] FillArrX3(int[] a) { //метод п. 2
        for (int i = 0; i < a.length; i++)
            a[i] = i*3;
            return a;
    }
    public static int[] ArrMulti(int[] a) { //метод п. 3
        for (int i = 0; i < a.length; i++)
            if (a[i]<6) a[i] *= 2;
        return a;
    }
    public static int[][] ArrDiagToOne(int[][] a) { //метод п. 4
        for (int i = 0; i < a[0].length; i++) {
            a[i][i] = 1; // Заполнение прямой диагонали
            a[i][(a[0].length) - i - 1] = 1; // Заполнение обратной диагонали
        }

        return a;
    }

    public static int[] FillByRnd(int[] a) { // Вспомогательный метод заполнения массива рандом
        for (int i = 0; i < a.length; i++)
            a[i] = (int) (Math.random()*100);
        return a;
    }

    public static void SeekMinMax(int[] a) { //метод п. 5
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++){
            if (min > a[i]) min = a[i];
            if (max < a[i]) max = a[i];
        }
        System.out.println("Минималный элемент массива = " + min);
        System.out.println("Максимальный элемент массива = " + max);

    }
    public static boolean CheckBalance(int[] a) {//метод п. 6, модифицировал в соотвстствии с рекоммендациями
        int left = 0, right = 0;// Убрал вложенные циклы
        for (int j : a) right += j;// Суммируем весь массив
        for (int i = 0; i < a.length; i++) {
            left += a[i];//складываем слева
            right = right - a[i];//вычитаем справа
            if (left == right) {//проеряем баланс
                System.out.println("Сумма баланса = " + right);
                return true;}
            //else right = 0;
        }
        return false;
    }

    public static void ShiftOnOne(int[] a) {//Вспомогательный метод сдвиг на 1
        int tempA = a[1];
        a[1] = a[0];
        for (int i = 1; i < a.length; i++) {
            int tempB;
            if (i < a.length-1) {
                tempB = a[i+1];
                a[i+1] = tempA;
                tempA = tempB;
                }

        }
        a[0] = tempA;


    }

    public static int[] ShiftOnN(int[] a, int b) {//метод п. 7
       int i = 0;
       while (b<0) b = a.length + b;
       while (i<b) {ShiftOnOne(a);
        i++;
        }
        return a;
    }




    public static void main(String[] args) {
        int[] arrBin = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        //int[] arDec = {1,1,0,0,12,23,1,0};
        int[] arrx = new int[8];
        int[] arr1 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        int[][] arrD = new int[10][10];
        System.out.print("Исходный массив: ");
        PrintArry(arrBin);
        System.out.print("Измененный массив 0 на 1 и 1 на 0: ");
        arrBin = ChangeOneToZero(arrBin);
        PrintArry(arrBin);
        //System.out.print("Массив с ошибкой: ");
        //PrintArry(arDec);
        //PrintArry(ChangeOneToZero(arDec));
        System.out.print("Нулевой массив п.2: ");
        PrintArry(arrx);
        arrx = FillArrX3(arrx);
        System.out.print("Результат п.2: ");
        PrintArry(arrx);
        System.out.print("Исходный массив п.3: ");
        PrintArry(arr1);
        arr1 = ArrMulti(arr1);
        System.out.print("Результат п.3: ");
        PrintArry(arr1);
        System.out.println("Исходный массив п.4: ");
        PrintArryN(arrD);
        arrD = ArrDiagToOne(arrD);
        System.out.println("Результат п.4: ");
        PrintArryN(arrD);
        // Дополнительные пункты
        int[] arrRnd = new int[10];
        arrRnd = FillByRnd(arrRnd);
        System.out.println();
        System.out.println("Выполнение задания п. 5");
        System.out.print("Рандомный массив: ");
        PrintArry(arrRnd);
        SeekMinMax(arrRnd);
        System.out.println();
        System.out.println("Выполнение задания п. 6");
        System.out.println("Исходный массив [1, 1, 1, 2, 1]");
        int[] arrTest = {1, 1, 1, 2, 1};
        System.out.println("Проверка баланса " + CheckBalance(arrTest));
        System.out.println("Исходный массив [2, 2, 2, 1, 2, 2, 10, 1]");
        int[] arrTest2 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println("Проверка баланса " + CheckBalance(arrTest2));
        System.out.println("Исходный массив [2, 2, 2, 1, 2, 2, 10, 10]");
        int[] arrTest3 = {2, 2, 2, 1, 2, 2, 10, 10};
        System.out.println("Проверка баланса " + CheckBalance(arrTest3));
        int[] arrShift = {1,2,3,4,5,6};
        System.out.println();
        System.out.println("Выполнение задания п. 7");
        System.out.print("Исходный массив: ");
        PrintArry(arrShift);
        System.out.print("сдвигаем на 1: ");
        PrintArry(ShiftOnN(arrShift,1)); // Влпрос почему изменяется исходный массив arrShft?
        arrShift = new int[]{1, 2, 3, 4, 5, 6};
        System.out.print("сдвигаем на 2: ");
        PrintArry(ShiftOnN(arrShift,2));
        arrShift = new int[]{1, 2, 3, 4, 5, 6};
        System.out.print("сдвигаем на 7: ");
        PrintArry(ShiftOnN(arrShift,7));
        arrShift = new int[]{1, 2, 3, 4, 5, 6};
        System.out.print("сдвигаем на 0: ");
        PrintArry(ShiftOnN(arrShift,0));
        arrShift = new int[]{1, 2, 3, 4, 5, 6};
        System.out.print("сдвигаем на -1: ");
        PrintArry(ShiftOnN(arrShift,-1));
        arrShift = new int[]{1, 2, 3, 4, 5, 6};
        System.out.print("сдвигаем на -3: ");
        PrintArry(ShiftOnN(arrShift,-3));
        arrShift = new int[]{1, 2, 3, 4, 5, 6};
        System.out.print("сдвигаем на -30 ");
        PrintArry(ShiftOnN(arrShift,3));//При значительно большем числе выходит ошибка
    }

}
