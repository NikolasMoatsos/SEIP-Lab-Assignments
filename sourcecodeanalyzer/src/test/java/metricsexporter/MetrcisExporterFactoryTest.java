package metricsexporter;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class MetrcisExporterFactoryTest {

  MetricsExporterFactory mef = new MetricsExporterFactory();

  @Test
  public void testCsvFileType() {
    assertThat(mef.createMetricsExporter("csv"), instanceOf(CsvWriter.class));
  }

  @Test
  public void testJsonFileType() {
    assertThat(mef.createMetricsExporter("json"), instanceOf(JsonWriter.class));
  }

  @Test
  public void testWrongFileType() {
    assertThat(mef.createMetricsExporter("wrong"), instanceOf(NullWriter.class));
  }
}
