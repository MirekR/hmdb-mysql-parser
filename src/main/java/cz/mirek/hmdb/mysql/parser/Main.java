package cz.mirek.hmdb.mysql.parser;

import cz.mirek.hmdb.mysql.parser.domain.Domain;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Main {
    public static void main(String [] args) throws ParserConfigurationException, SAXException, IOException {
        SaxReader saxReader = new SaxReader();

        String file = "/Users/mirekrousal/Downloads/hmdb/hmdb_metabolites.xml";
        String results = "/Users/mirekrousal/Downloads/hmdb/results.sql";

        FileWriter fw = new FileWriter(new File(results));

        fw.write("USE metabolomics; \n\n");

        Consumer<Domain> domainConsumer = domain -> {
            try {
                fw.write(domain.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        saxReader.parseFile(file, domainConsumer);

        fw.flush();
    }
}
