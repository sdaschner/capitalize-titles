package com.sebastian_daschner.capitalize_titles;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TitleCapitalizerTest {

    private final TitleCapitalizer capitalizer = new TitleCapitalizer();

    @Test
    void simple_example() {
        assertThat(capitalizer.capitalize("my first title")).isEqualTo("My First Title");
        assertThat(capitalizer.capitalize("MY FIRST TITLE")).isEqualTo("My First Title");
        assertThat(capitalizer.capitalize("My first Title")).isEqualTo("My First Title");
        assertThat(capitalizer.capitalize("My first cloud-native deployment")).isEqualTo("My First Cloud-Native Deployment");
    }

    @Test
    void containing_prepositions() {
        assertThat(capitalizer.capitalize("a river runs through the city")).isEqualTo("A River Runs Through the City");
        assertThat(capitalizer.capitalize("a river runs through a city")).isEqualTo("A River Runs Through a City");
        assertThat(capitalizer.capitalize("a river and the city")).isEqualTo("A River and the City");
        assertThat(capitalizer.capitalize("a river as the city")).isEqualTo("A River as the City");
        assertThat(capitalizer.capitalize("a river but the city")).isEqualTo("A River but the City");
        assertThat(capitalizer.capitalize("a river by the city")).isEqualTo("A River by the City");
        assertThat(capitalizer.capitalize("a river for the city")).isEqualTo("A River for the City");
        assertThat(capitalizer.capitalize("a river if in the city")).isEqualTo("A River if in the City");
        assertThat(capitalizer.capitalize("a river in the city")).isEqualTo("A River in the City");
        assertThat(capitalizer.capitalize("nor a river nor the city")).isEqualTo("Nor a River nor the City");
        assertThat(capitalizer.capitalize("the river of the city")).isEqualTo("The River of the City");
        assertThat(capitalizer.capitalize("the river off the city")).isEqualTo("The River off the City");
        assertThat(capitalizer.capitalize("the river or the city")).isEqualTo("The River or the City");
        assertThat(capitalizer.capitalize("the river to the city")).isEqualTo("The River to the City");
        assertThat(capitalizer.capitalize("the river up the city")).isEqualTo("The River up the City");
    }

    @Test
    void ignoring_trailing_special_characters() {
        assertThat(capitalizer.capitalize("== my first title")).isEqualTo("== My First Title");
        assertThat(capitalizer.capitalize("== a river runs through the city")).isEqualTo("== A River Runs Through the City");
        assertThat(capitalizer.capitalize(" a river runs through the city ")).isEqualTo(" A River Runs Through the City ");
        assertThat(capitalizer.capitalize("; a river runs through the city?")).isEqualTo("; A River Runs Through the City?");
    }

    @Test
    void stop_words() {
        assertThat(capitalizer.capitalize("breaking news: a river runs through the city")).isEqualTo("Breaking News: A River Runs Through the City");
        assertThat(capitalizer.capitalize("breaking news - a river runs through the city")).isEqualTo("Breaking News - A River Runs Through the City");
        assertThat(capitalizer.capitalize("breaking news -- a river runs through the city")).isEqualTo("Breaking News -- A River Runs Through the City");
        assertThat(capitalizer.capitalize("breaking news – a river runs through the city")).isEqualTo("Breaking News – A River Runs Through the City");
        assertThat(capitalizer.capitalize("breaking news — a river runs through the city")).isEqualTo("Breaking News — A River Runs Through the City");
    }

    @Test
    void unicode_characters() {
        assertThat(capitalizer.capitalize("a river in thø city")).isEqualTo("A River in Thø City");
        assertThat(capitalizer.capitalize("a river in øur city")).isEqualTo("A River in Øur City");
        assertThat(capitalizer.capitalize("a river in thä city")).isEqualTo("A River in Thä City");
        assertThat(capitalizer.capitalize("a river in thä¿ city")).isEqualTo("A River in Thä¿ City");
    }

}