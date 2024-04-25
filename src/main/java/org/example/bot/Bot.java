package org.example.bot;

import org.glassfish.jersey.server.model.Routed;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    final private String BOT_TOKEN = "7045050241:AAHzpd77h-oAWgyB6BbfPKdoNPX5JuxNyJI";
    final private String BOT_NAME = "Java_lab_zv_bot";

    String btn;
    Storage storage;
    Learner learner;
    Study study;
    Examinator exam;
    public Bot() {
        storage = new Storage();
        learner = new Learner();
        study = new Study();
        exam = new Examinator();
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
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMess = update.getMessage();
                String chatId = inMess.getChatId().toString();
                String response = null;
                SendMessage outMess = new SendMessage();
                if (!inMess.getText().equals("/start") && btn != "reset"){
                    response = parseMessage(inMess.getText());
                    outMess.setChatId(chatId);
                    outMess.setText(response);
                    execute(outMess);
                }
                if (inMess.getText().equals("/start") || btn == "reset") {
                    response = "Выберите режим";
                    outMess.setChatId(chatId);
                    outMess.setReplyMarkup(createInlineKeyboard());
                    outMess.setText(response);
                    execute(outMess);
                    btn = "";
                }

            }
            else if(update.hasCallbackQuery()){
                String response = parseMessage(update.getCallbackQuery().getData());
                SendMessage outMess = new SendMessage();
                String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
                outMess.setChatId(chatId);
                outMess.setText(response);
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public String parseMessage(String textMsg) {
        String response = null;
        if (textMsg.equals("/exam")) {
            btn = "/exam";
            response = exam.action(storage).getQuestion();
        }
        else if (btn == "/exam"){
            if(exam.check(textMsg)){
                exam.i++;
            }
            exam.k++;
                if (storage.array.length == exam.k){
                    response = exam.result(storage);
                    btn = "reset";
                    exam = new Examinator();
                }
                else {
                    response = exam.action(storage).getQuestion();
                }
        }


        else if (textMsg.equals("/study")) {
            btn = "/study";
            response = study.action(storage).getQuestion();
        }else if (btn == "/study") {
            response = study.end(textMsg);
            btn = "reset";
            }

        else if (textMsg.equals("/learn"))
        {
            btn = "/learn";
            response = learner.action(storage).getQuestion();
        } else if (btn == "/learn") {
            if(learner.check(textMsg)){
                response = learner.end(textMsg);
                btn ="reset";
            }
            else {
                response = learner.end(textMsg);
            }
        }
        else
            response = "Сообщение не распознано";
        return response;
    }

    private InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        rowInline.add(InlineKeyboardButton.builder().text("exam").callbackData("/exam").build());
        rowInline.add(InlineKeyboardButton.builder().text("study").callbackData("/study").build());
        rowInline.add(InlineKeyboardButton.builder().text("learn").callbackData("/learn").build());

        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }
}


