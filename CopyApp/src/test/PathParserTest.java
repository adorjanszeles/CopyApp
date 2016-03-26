package test;

import junit.framework.TestCase;
import main.PathParser;

public class PathParserTest extends TestCase {
    private PathParser parser;

    public void setUp() throws Exception{
        parser = new PathParser();
        parser.parseXML();
    }

    public void testParseSource() {
        assertEquals("file/to/source", parser.getSource());
    }

    public void testParseDestination() {
        assertEquals("file/to/destination", parser.getDestination());
    }
}
