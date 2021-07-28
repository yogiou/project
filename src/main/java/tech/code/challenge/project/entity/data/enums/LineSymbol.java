package tech.code.challenge.project.entity.data.enums;

public enum LineSymbol {
    LINE_SYMBOL("x");

    private String symbol;

    LineSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String toValue() {
        return symbol;
    }
}
