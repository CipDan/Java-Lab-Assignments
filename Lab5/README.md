# Lab 5's Assignment - Notes

1. Begin code reading with class Main.
2. java.net.URI, java.beans.XMLDecoder, java.beans.XMLEncoder are used for Optional.
3. Compulsory, Optional and Bonus sections are highlighted with corresponding comments in the Main class.
4. Classes for Compulsory: Document, Catalog, Main, Manager.
5. The `@Deprecated` methods were only created for the XML encryption and decryption, since a Java Beans Object requires them.
   They normally had no reason to be created, and therefore are not used in the code.
6. The classes for all the commands where bundled in the Commands package.
7. For the HTML report, I made use of Apache Velocity, and for Metadata gathering, of Apache Tika.
8. MyShell contains the Code for the Optional and Bonus sections.
9. In Templates package is stored a .vm file serving as template for the HTML report.
10. Classes for Optional and Bonus: All classes in Lab5.Commands, all templates in Lab5.Templates, InvalidCatalogException, LoadCommand and MyShell classes.
11. Since there is no `help`command implemented and for the better understanding of how the commands work, here is a list of all the commands and their parameters (`exit` and `NEWLINE` were directly implemented in MyShell):
    * __create-catalog__ *catalog_name* *catalog_path*
    * __add-document__ *catalog_name* *document_id* *document_name* *document_location* (the location can be either a path or a web address)
    * __add-tag__ *catalog_name* *document_id* *tag_key* *tag*
    * __save-catalog__ *catalog_name*
    * __load-catalog__ *catalog_path*
    * __view-document__ *catalog_name* *document_id*
    * __report__ *format* *catalog_name*
    * __info__ *catalog_name*
