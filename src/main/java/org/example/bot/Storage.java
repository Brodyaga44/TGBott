package org.example.bot;

import java.lang.reflect.Array;
import java.util.Random;

public class Storage {
    Question[] array = {new Question("Вопрос 1) 5+5=x \nВарианты ответа: 1, 2, 10", "10"),
                        new Question("Вопрос 2) На каком языке написан этот бот? \nВарианты ответа: Русский, Арабский, Java", "Java"),
                        new Question("Вопрос 3) Вопрос? \nВарианты ответа: Ответ, Да, Нет", "Ответ")
                        };
    public Question getRandQuote() {
        Random random = new Random();
        int randomNum = random.nextInt(2);
        return array[randomNum];
    }
    public String asking(int i){
        return "Ответьте на вопрос " + array[i];
    }
}

