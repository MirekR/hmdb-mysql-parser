package cz.mirek.hmdb.mysql.parser;

import cz.mirek.hmdb.mysql.parser.domain.Domain;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SaxReader {
    private List<String> defaultInclude = Arrays.asList("metabolite", "pathway");

    public void parseFile(InputStream inputStream, Consumer<Domain> domainConsumer) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        Reader reader = new InputStreamReader(inputStream,"UTF-8");

        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");

        MysqlHandler handler = new MysqlHandler(this::filterDomains, domainConsumer);

        saxParser.parse(is, handler);
    }

    public void parseFile(String filePath, Consumer<Domain> domainConsumer) throws IOException, ParserConfigurationException, SAXException {
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);

        this.parseFile(inputStream, domainConsumer);
    }

    private boolean filterDomains(Domain domain) {

        if ("reference".equals(domain.getTagName()) && "disease".equals(domain.getParentTagName())) {
            return true;
        } else if (defaultInclude.contains(domain.getTagName())) {
            return true;
        } else {
            return false;
        }
    }
}
