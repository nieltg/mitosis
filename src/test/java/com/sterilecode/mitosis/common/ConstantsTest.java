package com.sterilecode.mitosis.common;

import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : ConstantsTest.java
 * Created at        : 4/24/17
 * Last modified at  : 4/24/17
 */

public class ConstantsTest {

  @Test
  public void test_getNanoSeconds() {
    assertEquals(1000000000, Constants.NANOSECONDS_IN_A_SECOND);
    assertEquals(1000000, Constants.NANOSECONDS_IN_A_MILLISECOND);
  }
}