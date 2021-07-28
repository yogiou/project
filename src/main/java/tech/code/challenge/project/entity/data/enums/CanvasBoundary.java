package tech.code.challenge.project.entity.data.enums;

public enum CanvasBoundary {
    HORIZONTAL_BOUNDARY("-"),
    VERTICAL_BOUNDARY("|");

    private String code;

    CanvasBoundary(String code) {
        this.code = code;
    }

    public String toValue() {
        return code;
    }
}
