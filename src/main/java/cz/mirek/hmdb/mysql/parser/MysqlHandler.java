package cz.mirek.hmdb.mysql.parser;

import cz.mirek.hmdb.mysql.parser.domain.Domain;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MysqlHandler extends DefaultHandler {
    private List<String> ignorableParents = Arrays.asList("pathways", "diseases", "references");

    private List<Domain> domainPath = new ArrayList<>(20);

    private long idCounter = 0;

    private Predicate<Domain> filter;
    private Consumer<Domain> consumer;

    public MysqlHandler(Predicate<Domain> filter, Consumer<Domain> consumer) {
        this.filter = filter;
        this.consumer = consumer;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!ignorableParents.contains(qName)) {
            Domain parent = domainPath.size() > 0 ? domainPath.get(domainPath.size() - 1): new Domain();

            Domain current = new Domain(qName, parent.getTagName(), idCounter, parent.getDbId());

            domainPath.add(current);

            idCounter++;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (ignorableParents.contains(qName)) {
            return;
        }

        Domain current = domainPath.remove(domainPath.size() - 1);

        if (filter.test(current)) {
            consumer.accept(current);
        }
    }

    public void characters (char ch[], int start, int length) throws SAXException {
        String value = String.copyValueOf(ch, start, length);
        value = value.replace("\n", " ").trim();

        if ("".equals(value)) {
            return;
        }

        Domain current = domainPath.get(domainPath.size() - 1);
        Domain parent = domainPath.get(domainPath.size() - 2);

        parent.add(current.getTagName(), value);
    }
}
