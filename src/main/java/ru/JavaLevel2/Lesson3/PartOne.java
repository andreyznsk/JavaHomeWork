package ru.JavaLevel2.Lesson3;

import java.lang.reflect.Array;
import java.util.*;

public class PartOne {

    public static void main(String[] args) {
        String[] strArray = {"Dima","Vova","John","Andrey","Kataya","Andrey","Igor","Saha","Igor","Anatoly","Bob",
        "Rod","Tod","John","John","John","Bob","Lena","Masha","Saha"};
        Set<String> unicStringList = new HashSet<>();//Сэт для уникальных имен
        List<String> stringList = Arrays.asList(strArray);
        Map<String, Integer> countOfUniqueWord = new HashMap<>();

        // Выводим исходный массив
        System.out.println("Исходный список:\n" + stringList);
        System.out.println("Количество элементов списка: " + stringList.size());

        //Создаем список без дубликатов
        for (String s: stringList) unicStringList.add(s);
        System.out.println("Уникальные слова в списке(количество: " + unicStringList.size() + ")");
        System.out.println(unicStringList);

        //Создаем хэш-таблицу из пары имя - количество вхождений
        for (String s : unicStringList) {
            countOfUniqueWord.put(s,Collections.frequency(stringList,s));
        }
        System.out.println("Количество вхождений слов");
        int count = 0;
        for (Map.Entry<String, Integer> o : countOfUniqueWord.entrySet()) {

            System.out.println(o.getKey() + ": " + o.getValue());
            count +=o.getValue();//На свякий случай посчет количества элементов
        }
        if(count == strArray.length) System.out.println("Проверка пройдена");//Доп. проверка что никакой элемент не потерялся
        }


}
