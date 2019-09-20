package gumtree;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
    @Test
    public void testEcho() {
        assertThat("Hello World!!", is("Hello World!!"));
    }
}
