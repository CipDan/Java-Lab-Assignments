package Lab5;

import org.apache.commons.validator.routines.UrlValidator;
import ucar.nc2.util.IO;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

/**
 * A file manager which handles saving, loading and viewing of contents for a file.
 */
public class Manager {
    /**
     * Saves the object in a file in a binary representation.
     *
     * @param catalog the object to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public static void binarySave(Catalog catalog) throws IOException {
        //This is test code for the Compulsory section!
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    /**
     * Saves the object in a file in a plaintext representation.
     *
     * @param catalog the object to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public static void plaintextSave(Catalog catalog) throws IOException {
        //This is test code for the Optional and Bonus sections!
        try (var encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(catalog.getPath())))) {
            encoder.writeObject(catalog);
        }
    }

    /**
     * Saves the given object in a file in binary representation or plaintext representation.
     *
     * @param catalog the object to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public static void save(Catalog catalog) throws IOException {
        //binarySave(catalog);
        plaintextSave(catalog);
    }

    /**
     * Loads the contents of a binary file.
     *
     * @param path path of a file.
     * @return a <code>Catalog</code> made with the contents of the file.
     * @throws IOException            if an I/O error occurs.
     * @throws ClassNotFoundException class of a serialized object cannot be found.
     */
    public static Catalog binaryLoad(String path) throws InvalidCatalogException, IOException, ClassNotFoundException {
        //This is test code for the Compulsory section!
        try (var oos = new ObjectInputStream(
                new FileInputStream(path))) {
            Object obj = oos.readObject();
            if (obj instanceof Catalog)
                return (Catalog) oos.readObject();
            else
                throw new InvalidCatalogException();
        }
    }

    /**
     * Loads the contents of a text file.
     *
     * @param path path of a file.
     * @return a <code>Catalog</code> made with the contents of the file.
     * @throws IOException            if an I/O error occurs.
     * @throws ClassNotFoundException class of a serialized object cannot be found.
     */
    public static Catalog plaintextLoad(String path) throws InvalidCatalogException, IOException, ClassNotFoundException {
        //This is test code for the Optional and Bonus sections!
        try (var decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(path)))) {
            Object obj = decoder.readObject();
            if (obj instanceof Catalog)
                return (Catalog) decoder.readObject();
            else
                throw new InvalidCatalogException();
        }
    }

    /**
     * Loads content from binary files or text files.
     *
     * @param path path of a file.
     * @return a <code>Catalog</code> made with the contents of the file.
     * @throws IOException            if an I/O error occurs.
     * @throws ClassNotFoundException class of a serialized object cannot be found.
     */
    public static Catalog load(String path) throws InvalidCatalogException, IOException, ClassNotFoundException {
        //return binaryLoad(path);
        return plaintextLoad(path);
    }

    /**
     * Views a document from the catalog through its location by either browsing a web page or opening a file.
     *
     * @param document the document to be viewed.
     */
    public static void view(Document document) {
        Desktop desktop = Desktop.getDesktop();
        try {
            String aux = document.getLocation();
            UrlValidator validator = new UrlValidator();
            if (validator.isValid(aux)) {
                desktop.browse(URI.create(aux));
            } else {
                desktop.open(new File(aux));
            }
        } catch (IOException e) {
            System.out.println("Unexpected error when loading the document!");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
