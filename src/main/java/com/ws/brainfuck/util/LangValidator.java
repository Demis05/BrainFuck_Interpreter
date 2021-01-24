package com.ws.brainfuck.util;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LangValidator implements Predicate<String> {

    private Pattern validatorPattern;

    public LangValidator(Pattern validatorPattern) {
        this.validatorPattern = validatorPattern;
    }

    @Override
    public boolean test(String inputLine) {
        Matcher extensionMatcher = validatorPattern.matcher(inputLine);
        return extensionMatcher.find();
    }
}
