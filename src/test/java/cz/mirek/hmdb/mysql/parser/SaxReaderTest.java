package cz.mirek.hmdb.mysql.parser;

import cz.mirek.hmdb.mysql.parser.domain.Domain;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SaxReaderTest {

    @Test
    public void shouldParseTheTestingFile() throws ParserConfigurationException, SAXException, IOException {
        List<Domain> domains = new ArrayList<>();

        SaxReader saxReader = new SaxReader();
        Consumer<Domain> domainConsumer = domains::add;

        saxReader.parseFile(this.getFile("oneMetabolite.xml"), domainConsumer);

        assertThat(domains.size(), is(127));

        List<Domain> pathways = domains.stream().filter(domain -> "pathway".equals(domain.getTagName())).collect(Collectors.toList());
        assertThat(pathways.size(), is(2));
    }

    private InputStream getFile(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}