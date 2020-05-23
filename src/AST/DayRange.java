package AST;

public class DayRange extends Occurrence implements ASTnode{
    Day from;
    Day to;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("from");
        from = new Day();
        from.parse();
        t.getAndCheckNext("to");
        to = new Day();
        to.parse();
    }

    @Override
    public void evaluate() {

    }

    public Day getFrom() {
        return from;
    }

    public Day getTo() {
        return to;
    }

    public String getDayRange() {
        return from + "to" + to;
    }
}