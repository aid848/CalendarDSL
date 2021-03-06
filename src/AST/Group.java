package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group extends Event {
    List<Event> events;
    HashMap<String, String> keys = Keyword.keywords;

    @Override
    public void parse() {
        events = new ArrayList<>();
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(keys.get("group:"));
        title = new Title();
        title.parse();
        t.getAndCheckNext(keys.get(">"));
        t.getAndCheckNext(keys.get("("));
        String token = t.getNext();
        while(!token.equals(keys.get(")"))){
            events.add(Validator.validateExistingEvent(token));
            token = t.getNext();
            if (!(token.equals(keys.get(")")) || token.equals(keys.get(",")))) {
                throw new RuntimeException("invalid grouping");
            }
            if(token.equals(keys.get(","))) {
                token = t.getNext();
                if(token.equals(keys.get(")"))) {
                    throw new RuntimeException("unexpected token received after comma");
                }
            }
        }

    }

    public void setTitle(Title title) {
        this.title = title;
    }
    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
