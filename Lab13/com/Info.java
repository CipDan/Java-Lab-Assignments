package Lab13.com;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class implementation of the `info` command.
 */
public class Info {

    private static Info singleInstance = null;

    /**
     * Creates a new instance.
     */
    private Info() {
    }

    /**
     * Returns the singleton instance of the class. If said instance has not been constructed, it calls the constructor and then returns the newly constructed instance.
     *
     * @return the singleton instance of the class.
     */
    public static Info getInstance() {
        if (singleInstance == null)
            singleInstance = new Info();

        return singleInstance;
    }

    /**
     * Prints info about the given locale in the format of the current locale and using the current locale's resource bundle.
     *
     * @param locale        the given locale.
     * @param chosenLocale  the current locale.
     * @param currentLocale the resource bundle of the current locale.
     */
    public static void printInfo(Locale locale, Locale chosenLocale, ResourceBundle currentLocale) {
        String pattern = currentLocale.getString("info");
        Object[] arguments = {locale.getDisplayLanguage(chosenLocale) + " (" +
                locale.getDisplayCountry(chosenLocale) + ")"};
        System.out.println(new MessageFormat(pattern).format(arguments));
        System.out.println("\t" + currentLocale.getString("country") + " " + locale.getDisplayCountry(chosenLocale));
        System.out.println("\t" + currentLocale.getString("language") + " " + locale.getDisplayLanguage(chosenLocale));
        System.out.println("\t" + currentLocale.getString("currency") + " " +
                Currency.getInstance(locale).getCurrencyCode() + " (" +
                Currency.getInstance(locale).getDisplayName(chosenLocale) + ")");
        System.out.println("\t" + currentLocale.getString("week.days") + " " +
                String.join(", ", DateFormatSymbols.getInstance(locale).getWeekdays()).substring(2));
        System.out.println("\t" + currentLocale.getString("months") + " " +
                String.join(", ", DateFormatSymbols.getInstance(locale).getMonths()));
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter givenLocaleFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
        DateTimeFormatter currentLocaleFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(chosenLocale);
        System.out.println("\t" + currentLocale.getString("today") + " " + today.format(givenLocaleFormatter)
                + " (" + today.format(currentLocaleFormatter) + ")");
    }

    /**
     * Displays info about a given locale or the current, one if none is given, in a pretty format.
     *
     * @param baseName the project path to the resource bundle.
     * @param locale   the given locale.
     */
    public void displayInfoAboutLocale(String baseName, Locale locale) {
        locale = locale != null ? locale : SetLocale.getInstance().getCurrentLocale();
        Locale chosenLocale = SetLocale.getInstance().getCurrentLocale();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, chosenLocale);

        List<Locale> supportedLocales = DisplayLocales.getInstance().getSupportedLocales();
        if (supportedLocales.contains(locale)) {
            printInfo(locale, chosenLocale, currentLocale);
        } else {
            ResourceBundle setLocale = ResourceBundle.getBundle(baseName, chosenLocale);
            System.out.println(setLocale.getString("locale.invalid"));
        }
    }
}
