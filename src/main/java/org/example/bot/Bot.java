package org.example.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    //вместо звездочек подставляйте свои данные

    final private String BOT_TOKEN = "7045050241:AAHzpd77h-oAWgyB6BbfPKdoNPX5JuxNyJI";

    final private String BOT_NAME = "Java_lab_zv_bot";

    Storage storage;



    public Bot() {

        storage = new Storage();

    }



    @Override

    public String getBotUsername() {

        return BOT_NAME;

    }



    @Override

    public String getBotToken() {

        return BOT_TOKEN;

    }



    @Override

    public void onUpdateReceived(Update update) {

        try{

            if(update.hasMessage() && update.getMessage().hasText())

            {

                //Извлекаем из объекта сообщение пользователя

                Message inMess = update.getMessage();

                //Достаем из inMess id чата пользователя

                String chatId = inMess.getChatId().toString();

                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик

                String response = parseMessage(inMess.getText());

                //Создаем объект класса SendMessage - наш будущий ответ пользователю

                SendMessage outMess = new SendMessage();



                //Добавляем в наше сообщение id чата а также наш ответ

                outMess.setChatId(chatId);

                outMess.setText(response);



                //Отправка в чат

                execute(outMess);

            }

        } catch (TelegramApiException e) {

            e.printStackTrace();

        }

    }

    public String parseMessage(String textMsg) {

        String response;
        String asking;



        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ

        if(textMsg.equals("/start"))

            response = "Привет";

        else if(textMsg.equals("/get"))
            response = storage.getRandQuote();
        else if(textMsg.equals("/exam"))
            response = storage.getRandQuote();
        else if(textMsg.equals("/study"))

            response = storage.getRandQuote();
        else if(textMsg.equals("/learn")) {
            response = storage.asking(0);
            response = storage.array[0].answer;
        }

        else

            response = "Сообщение не распознано";



        return response;

    }

}