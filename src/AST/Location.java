package AST;

public class Location implements Setting, ASTnode {
    String name;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.checkToken("location:");
        name = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }

    public String getLocation() {
        return name;
    }
}