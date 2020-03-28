package Lab5.Commands;

import Lab5.Catalog;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of 'info' command.
 */
public class Info implements Command {

    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 1) {
            int index;
            for (index = 0; index < catalogs.size(); index++) {
                if (catalogs.get(index).getName().equals(params[0]))
                    break;
            }
            if (index == catalogs.size()) {
                System.out.println("Catalog with name " + params[0] + " not found!");
            } else {
                prepareFileInfo(catalogs.get(index));
            }
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 2 parameters for this command!");
        }
    }

    /**
     * Gathers the info and prepares it.
     *
     * @param catalog the catalog from whose file will be gathered information.
     */
    private void prepareFileInfo(Catalog catalog) {
        try {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            File file = new File(catalog.getPath());
            FileInputStream inputStream = new FileInputStream(file);
            ParseContext context = new ParseContext();

            XMLParser xmlparser = new XMLParser();
            xmlparser.parse(inputStream, handler, metadata, context);
            System.out.println("Metadata of the document:");
            String[] metadataNames = metadata.names();

            for (String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));
            }
        } catch (IOException | SAXException | TikaException e) {
            System.out.println("Unexpected error when obtaining the metadata!");
        }
    }
}
