package org.example.bot;

public class Study { // otvet vidno
    Question quest;

    public Question action(Storage storage){
        this.quest = storage.getRandQuote();
        return quest;
    }

    public Boolean check(String answer){
        return quest.answer.equals(answer);
    }

    public String end(String answer) {
        String response = "Верный ответ: " + quest.answer + "\nВаш ответ: " + answer;
        return response;
    }

}



