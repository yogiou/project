package tech.code.challenge.project.utils;

import java.util.regex.Pattern;

public class Util {
    public static boolean isNumeric(String input) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (input == null) {
            return false;
        }

        return pattern.matcher(input).matches();
    }
}
