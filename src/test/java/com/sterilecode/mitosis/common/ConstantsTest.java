package com.sterilecode.mitosis.common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wennyyustalim on 4/24/17.
 */
public class ConstantsTest {
    Constants con = new Constants();

    @Test
    public void test_getNanoSeconds() {
        assertEquals(1000000, con.getNanoSeconds());
    }
}