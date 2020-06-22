package Lessons;

import java.awt.*;
import java.util.*;

//2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
// В этот телефонный справочник с помощью метода add() можно добавлять записи.
// С помощью метода get() искать номер телефона по фамилии.
// Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
// тогда при запросе такой фамилии должны выводиться все телефоны.
class PhoneBook {

    HashMap<String, String> phonebook = new HashMap<>();

    void add(String phone, String name) {
        phonebook.put(phone, name);
    }

    void get(String name) {
        for (Map.Entry<String, String> phoneBookAsEntry : phonebook.entrySet()) {
            if (phoneBookAsEntry.getValue().equals(name)) {
                System.out.println(name + " - " + phoneBookAsEntry.getKey());
            }
        }
    }
}


//        1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//        Посчитать сколько раз встречается каждое слово.

public class Lesson3 {

    ArrayList<String> wordsList = new ArrayList<>();

    // конструктор класса принимающий массив String и заполняющмий ArrayList of instance.
    Lesson3(String[] words) {
        for (int i = 0; i < words.length; i++) {
            this.wordsList.add(words[i]);
        }
    }

    //метод принимающий String искомого слова и ArrayList в котором нужно подсчитать количество вхождений этого слова.
    public int countThisWord(String word, ArrayList wordsList) {
        return Collections.frequency(wordsList, word);
    }


    public static void main(String[] args) {
//массив для проверки задания 1
        String[] words = new String[]{"cat", "dog", "fly", "air", "monk", "day", "sea", "elephant", "marbel", "stone",
                "bread", "bowl", "cat", "monk", "sea", "elephant", "cat", "book", "auto", "android"};
        // Создаем lesson3 класс с передачей в конструктор массива words.
        Lesson3 lesson3 = new Lesson3(words);
        //сжимаем String[] words в TreeSet для сохранения только уникалтных значенмй.
        TreeSet treeSet = new TreeSet(Arrays.asList(words));
        //итератор для распечатки значений из treeSet.
        Iterator<String> iterator = treeSet.iterator();
        System.out.println("Урок 3, задание 1.");
        while (iterator.hasNext()) {
            String currentWord = iterator.next();
            //печатаем значения в treeSet и возврващаем количество вхождений в lesson3.wordsList( instanceOf ArrayList).
            System.out.println("The word " + currentWord + ", counter is: " +
                    lesson3.countThisWord(currentWord, lesson3.wordsList));
        }

//задание 2 тест
        System.out.println();
        System.out.println("Урок 3, задание 2.");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("9774603020", "Vladimir Rumyancev");
        phoneBook.add("7746435320", "Vladimir Rumyancev");
        phoneBook.add("3224623020", "Anatoliy Sidorov");
        phoneBook.add("4563603020", "Vladimir Lenin");
        phoneBook.add("8899003020", "Mahatma Ghandi");
        phoneBook.add("78787873020", "Mahatma Ghandi");
        phoneBook.get("Vladimir Rumyancev");
        phoneBook.get("Anatoliy Sidorov");
        phoneBook.get("Mahatma Ghandi");


    }
}


