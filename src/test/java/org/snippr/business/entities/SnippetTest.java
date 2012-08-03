package org.snippr.business.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class SnippetTest {

    @Test
    public void testToString() {
        String title = "Code test";
        String description = "This is only a test description for the class Snippet";

        Snippet tester = new Snippet(title, description);
        String result = tester.toString();
        assertEquals("Testing Snippet instance ...", "Snippet [title=" + title + ", description=" + description + "]", result);
    }

}
