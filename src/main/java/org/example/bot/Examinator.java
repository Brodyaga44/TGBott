package org.example.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLType;

public class Examinator {
    Question quest;
    int i = 0;
    int k = 0;
    boolean await = false;

    public Question action(Storage storage){
        this.quest = storage.array[k];
        return quest;
    }

    public Boolean check(String answer){

        return quest.answer.equals(answer);
    }

    public String end(String answer) {
        this.k++;
        //this.quest;
        if (check(answer)) {
            i++;
            return "Ответ верный";
        }
        else {
            return "Ответ неверный";
        }
    }

    public String result(Storage storage){
        String response = "Всего вопросов: " + storage.array.length + "\nКоличество верных: " + i;
        return response;
    }
}