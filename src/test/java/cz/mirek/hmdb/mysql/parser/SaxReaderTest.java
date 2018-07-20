package cz.mirek.hmdb.mysql.parser;

import cz.mirek.hmdb.mysql.parser.domain.Domain;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SaxReaderTest {

    @Test
    public void shouldParseTheTestingFile() throws ParserConfigurationException, SAXException, IOException {
        List<Domain> domains = new ArrayList<>();

        SaxReader saxReader = new SaxReader();
        Consumer<Domain> domainConsumer = domains::add;

        saxReader.parseFile(this.getFilePath("oneMetabolite.xml"), domainConsumer);

        assertThat(domains.size(), is(137));

        long pathwaysCnt = domains.stream().filter(domain -> "pathway".equals(domain.getTagName())).count();
        assertThat(pathwaysCnt, is(2L));

        long diseaseCnt = domains.stream().filter(domain -> "disease".equals(domain.getTagName())).count();
        assertThat(diseaseCnt, is(10L));
    }

    private String getFilePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(fileName).getFile();
    }
}