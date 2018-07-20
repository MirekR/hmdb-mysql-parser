package cz.mirek.hmdb.mysql.parser;

import cz.mirek.hmdb.mysql.parser.domain.Tag;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeHandler extends DefaultHandler {
    private Map<String, Tag> paths = new HashMap<>();
    private List<String> currentPath = new ArrayList<>();

    public TreeHandler() {
        paths.put("[]", new Tag(""));
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Tag current = new Tag(qName);
        Tag parent = paths.get(currentPath.toString());
        parent.children.add(current);

        currentPath.add(qName);
        paths.put(currentPath.toString(), current);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentPath.remove(currentPath.size() - 1);
    }

    public Map<String, Tag> getPaths() {
        return this.paths;
    }
}
