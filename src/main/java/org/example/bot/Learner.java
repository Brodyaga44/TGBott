package org.example.bot;

public class Learner{
    Question quest;

    public Question action(Storage storage){
        this.quest = storage.getRandQuote();
        return quest;
    }

    public Boolean check(String answer){
        return quest.answer.equals(answer);
    }

    public String end(String answer) {
        if (check(answer)) {
            return "Ответ верный";
        }
        else {
            return "Ответ неверный";
        }
    }
}

