package com.strel.lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 26.11.2015.
 */
public class AlbumTest {

    Album justice;


    @Before
    public void setUp() throws Exception {
        justice = new Album();
    }

    @Test
    public void testLoad() throws Exception {
        justice.load("src/main/resources/albums.properties");

        assertNotEquals(justice.getName(), "<unknown>");
        assertNotEquals(justice.getGroup(), "<unknown>");
        assertNotEquals(justice.getYear(), 0);
        assertNotNull(justice.getTracklist());
    }

    @Test
    public void testContainsTrack() throws Exception {
        testLoad();
        assertFalse(justice.containsTrack("Seek and Destroy", "Metallica"));
        assertTrue(justice.containsTrack("One", "Metallica"));
    }
}