package org.example.bot;

import java.lang.reflect.Array;
import java.util.Random;

public class Storage {
    Question[] array = {new Question("Вопрос 1 Варианты ответа: 1, 2, Ответ", "Ответ"),
                         new Question("Вопрос 2 Варианты ответа: 1, 2, Ответ", "2")
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

