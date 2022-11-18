package com.sebastian_daschner.capitalize_titles;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Command(name = "capitalize-title")
public class CapitalizeCommand implements Runnable {

    @Parameters(description = "The title to capitalize")
    String title;

    @Parameters(hidden = true)
    List<String> parts;

    @Inject
    TitleCapitalizer capitalizer;

    @Override
    public void run() {
        String title = String.join(" ", parts);
        if ("-".equals(title)) {
            try {
                title = new String(System.in.readAllBytes());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        System.out.print(capitalizer.capitalize(title));
    }

}