package filereader;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SourceFileReaderFactoryTest {

  SourceFileReaderFactory sfrf = new SourceFileReaderFactory();

  @Test
  public void testLocalFileReader() {
    assertThat(sfrf.createSourceFileReader("local"), instanceOf(LocalFile.class));
  }

  @Test
  public void testWebFileReader() {
    assertThat(sfrf.createSourceFileReader("web"), instanceOf(WebFile.class));
  }

  @Test
  public void testWrongFileReader() {
    assertThat(sfrf.createSourceFileReader("wrong"), instanceOf(NullFile.class));
  }
}