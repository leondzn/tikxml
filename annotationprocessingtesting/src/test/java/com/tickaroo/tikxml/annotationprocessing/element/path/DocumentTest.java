package com.tickaroo.tikxml.annotationprocessing.element.path;

import com.tickaroo.tikxml.TestUtils;
import com.tickaroo.tikxml.TikXml;
import java.io.IOException;
import okio.Buffer;
import org.junit.*;

/**
 * @author Hannes Dorfmann
 */
public class DocumentTest {

  @Test
  public void skipElementWithPath() throws IOException {
    TikXml xml = new TikXml.Builder().exceptionOnUnreadXml(false).build();
    Document document =
        xml.read(TestUtils.sourceForFile("element_with_tag_to_skip_with_path.xml"), Document.class);
    Assert.assertNotNull(document);
    Assert.assertNotNull(document.image);

    // Writing xml test
    Buffer buffer = new Buffer();
    xml.write(buffer, document);

    String xmlStr =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><toSkip><image/></toSkip></document>";
    Assert.assertEquals(xmlStr, TestUtils.bufferToString(buffer));

    Document document2 = xml.read(TestUtils.sourceFrom(xmlStr), Document.class);
    Assert.assertEquals(document, document2);
  }

  @Test
  public void skipElementWithAttributesWithPath() throws IOException {
    TikXml xml = new TikXml.Builder().exceptionOnUnreadXml(false).build();
    Document document =
        xml.read(TestUtils.sourceForFile("element_with_tag_with_attributes_to_skip_with_path.xml"), Document.class);
    Assert.assertNotNull(document);
    Assert.assertNotNull(document.image);


    // Writing xml test
    Buffer buffer = new Buffer();
    xml.write(buffer, document);

    String xmlStr =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><toSkip><image/></toSkip></document>";
    Assert.assertEquals(xmlStr, TestUtils.bufferToString(buffer));

    Document document2 = xml.read(TestUtils.sourceFrom(xmlStr), Document.class);
    Assert.assertEquals(document, document2);

  }
}
