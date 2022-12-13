package com.sebastian_daschner.capitalize_titles;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class TitleCapitalizer {

    private final Set<Integer> wordDelimiters = Set.of((int) ' ', (int) '-', (int) '\n');
    private final Set<String> ignoredWords = Set.of("a", "and", "as", "at", "but", "by", "for", "if", "in", "nor", "of", "off", "on", "or", "the", "to", "up", "vs");
    private final Set<String> stopSymbols = Set.of(":", "- ", "–", "—", "\n");

    public String capitalize(String title) {
        if (title == null || title.isBlank())
            return title;

        StringBuilder builder = new StringBuilder(title.toLowerCase());

        int length = title.length();

        boolean capitalizeNextWord = true;
        boolean firstAlphaOccurred = false;

        for (int index = 0; index < length; ) {
            int codePoint = title.codePointAt(index);
            int currentIndex = index;
            boolean stopChar = stopSymbols.stream().anyMatch(s -> currentIndex == builder.indexOf(s, currentIndex));

            if (wordDelimiters.contains(codePoint)) {
                capitalizeNextWord = true;
            } else if (capitalizeNextWord) {
                int nextWordEnd = Math.max(builder.indexOf(" ", index), builder.indexOf("-", index));
                String nextWord = nextWordEnd != -1 ? builder.substring(index, nextWordEnd) : builder.substring(index);

                if (!firstAlphaOccurred || !ignoredWords.contains(nextWord)) {
                    codePoint = Character.toUpperCase(codePoint);
                    builder.setCharAt(index, (char) codePoint);
                }
                capitalizeNextWord = false;
            }

            index += Character.charCount(codePoint);

            firstAlphaOccurred = firstAlphaOccurred || Character.isAlphabetic(codePoint);
            if (stopChar) firstAlphaOccurred = false;
        }
        return builder.toString();
    }

}
