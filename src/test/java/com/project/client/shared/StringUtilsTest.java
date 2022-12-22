package com.project.client.shared;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {
  /** Method under test: {@link StringUtils#extractResponseIdRegex(String)} */
  @Test
  void testExtractResponseIdRegex() {
    assertTrue(StringUtils.extractResponseIdRegex("Not all who wander are lost").isEmpty());
  }

  /** Method under test: {@link StringUtils#extractResponseIdRegex(String)} */
  @Test
  void testExtractResponseIdRegex2() {
    List<String> actualExtractResponseIdRegexResult = StringUtils.extractResponseIdRegex("9");
    assertEquals(1, actualExtractResponseIdRegexResult.size());
    assertEquals("9", actualExtractResponseIdRegexResult.get(0));
  }
}
