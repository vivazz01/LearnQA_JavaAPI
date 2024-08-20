package exercises;

import lib.BaseTestCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ex10ShortStringCheck extends BaseTestCase {

    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789ABCDE", "0123456789ABCDEFG"})
    public void ex10ShortStringCheck(String name) {
        assertTrue(name.length() >= 15, "String length is lesser than 15");
    }
}
