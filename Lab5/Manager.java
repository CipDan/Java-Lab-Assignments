package Lab5;

import org.apache.commons.validator.routines.UrlValidator;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class Manager {
    public static void binarySave(Catalog catalog) throws IOException {
        //This is test code for the Compulsory section!
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static void plaintextSave(Catalog catalog) throws IOException {
        //This is test code for the Optional and Bonus sections!
        try (var encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(catalog.getPath())))) {
            encoder.writeObject(catalog);
        }
    }

    public static void save(Catalog catalog) throws IOException {
        //binarySave(catalog);
        plaintextSave(catalog);
    }

    public static Catalog binaryLoad(String path) throws IOException, ClassNotFoundException {
        //This is test code for the Compulsory section!
        try (var oos = new ObjectInputStream(
                new FileInputStream(path))) {
            return (Catalog) oos.readObject();
        }
    }

    public static Catalog plaintextLoad(String path) throws IOException, ClassNotFoundException {
        //This is test code for the Optional and Bonus sections!
        try (var decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(path)))) {
            return (Catalog) decoder.readObject();
        }
    }

    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        //return binaryLoad(path);
        return plaintextLoad(path);
    }

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
