package tech.code.challenge.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    @Test
    void isNumeric() {
        assertTrue(Util.isNumeric("1"));
        assertTrue(Util.isNumeric("1123"));
        assertFalse(Util.isNumeric("1a"));
        assertFalse(Util.isNumeric("fasfasf"));
        assertFalse(Util.isNumeric(null));
    }
}