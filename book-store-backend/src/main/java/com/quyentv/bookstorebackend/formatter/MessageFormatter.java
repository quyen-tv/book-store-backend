package com.quyentv.bookstorebackend.formatter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFormatter {
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{(\\w+)\\}");

    public static String format(String message, Map<String, Object> attributes) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(message);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String key = matcher.group(1);
            Object value = attributes.getOrDefault(key, "{" + key + "}");
            matcher.appendReplacement(sb, Matcher.quoteReplacement(String.valueOf(value)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
