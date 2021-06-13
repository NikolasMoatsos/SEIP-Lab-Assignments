package codeanalyzer;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class AnalyzerFactoryTest {
  AnalyzerFactory af = new AnalyzerFactory();

  @Test
  public void testRegex() {
    assertThat(af.createAnalyzer("regex", "any-filepath", "any-file-reader-type"), instanceOf(Regex.class));
  }

  @Test
  public void testStrcomp() {
    assertThat(af.createAnalyzer("strcomp", "any-filepath", "any-file-reader-type"), instanceOf(Strcomp.class));
  }

  @Test
  public void testWrongAnalyzerType() {
    assertThat(af.createAnalyzer("wrong", "any-filepath", "any-file-reader-type"), instanceOf(NullAnalyzer.class));
  }
}