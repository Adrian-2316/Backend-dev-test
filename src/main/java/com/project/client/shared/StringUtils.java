package com.project.client.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * Utility classes are not meant to be instanced.
     */
    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method used to extract the numbers from the response body using a regular expression.
     *
     * @param responseBody Response body.
     * @return List of strings.
     */
    public static List<String> extractResponseIdRegex(String responseBody) {
        // Extract the numbers from the response body using a regular expression
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(responseBody);

        List<String> strings = new ArrayList<>();
        while (matcher.find()) strings.add(matcher.group());
        return strings;
    }
}
