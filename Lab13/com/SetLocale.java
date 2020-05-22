package Lab13.com;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class implementation of the `set-locale` command.
 */
public class SetLocale {

    private static SetLocale singleInstance = null;
    private Locale currentLocale = Locale.getDefault();

    /**
     * Creates a new instance.
     */
    private SetLocale() {
    }

    /**
     * Returns the singleton instance of the class. If said instance has not been constructed, it calls the constructor and then returns the newly constructed instance.
     *
     * @return the singleton instance of the class.
     */
    public static SetLocale getInstance() {
        if (singleInstance == null)
            singleInstance = new SetLocale();

        return singleInstance;
    }

    /**
     * Returns the current locale.
     *
     * @return a <code>Locale</code>
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Sets a given locale as the current one.
     *
     * @param baseName  the project path to the resource bundle.
     * @param newLocale the locale to be set as the current one.
     */
    public void setCurrentLocale(String baseName, Locale newLocale) {
        List<Locale> supportedLocales = DisplayLocales.getInstance().getSupportedLocales();
        if (supportedLocales.contains(newLocale)) {
            currentLocale = newLocale;
            ResourceBundle setLocale = ResourceBundle.getBundle(baseName, currentLocale);
            String pattern = setLocale.getString("locale.set");
            Object[] arguments = {currentLocale.getDisplayLanguage(currentLocale) + " (" +
                    currentLocale.getDisplayCountry(currentLocale) + ")"};
            System.out.println(new MessageFormat(pattern).format(arguments));
        } else {
            ResourceBundle setLocale = ResourceBundle.getBundle(baseName, currentLocale);
            System.out.println(setLocale.getString("locale.invalid"));
        }
    }
}
