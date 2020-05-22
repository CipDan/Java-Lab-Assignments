package Lab13.com;

import java.util.Locale;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Class implementation of the `display-locales` command.
 */
public class DisplayLocales {

    private static DisplayLocales singleInstance = null;
    private List<Locale> supportedLocales = new LinkedList<>(Arrays.asList(Locale.UK, new Locale("ro", "RO")));

    /**
     * Creates a new instance.
     */
    private DisplayLocales() {
    }

    /**
     * Returns the singleton instance of the class. If said instance has not been constructed, it calls the constructor and then returns the newly constructed instance.
     *
     * @return the singleton instance of the class.
     */
    public static DisplayLocales getInstance() {
        if (singleInstance == null)
            singleInstance = new DisplayLocales();

        return singleInstance;
    }

    /**
     * Returns a list of all the supported locales.
     *
     * @return a <code>List</code> of <code>Locale</code> instances.
     */
    public List<Locale> getSupportedLocales() {
        return supportedLocales;
    }

    /**
     * Prints all the supported locales in a pretty format and using the resource bundle of the current locale.
     *
     * @param baseName the project path to the resource bundle.
     */
    public void printAllSupportedLocales(String baseName) {
        Locale chosenLocale = SetLocale.getInstance().getCurrentLocale();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, chosenLocale);
        System.out.println(currentLocale.getString("locales"));

        for (Locale localeEntry : supportedLocales) {
            System.out.println("\uD83E\uDDED " + localeEntry.getDisplayLanguage(chosenLocale) + " (" +
                    localeEntry.getDisplayCountry(chosenLocale) + ")");
        }
    }
}
