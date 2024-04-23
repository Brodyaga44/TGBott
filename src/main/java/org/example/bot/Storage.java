package org.example.bot;

import java.lang.reflect.Array;

public class Storage {
    Question[] array = {new Question("вопрос", "Ответ")};
    public String getRandQuote() {
        return "";
    }
    public  String asking(int i){
        return "Ответьте на вопрос " + array[i];
    }
}
