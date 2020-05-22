package Lab13.app;

import Lab13.com.DisplayLocales;
import Lab13.com.Info;
import Lab13.com.SetLocale;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

/**
 * The main class.
 */
public class LocaleExplore {

    private static Properties props = new Properties();
    private static String SET_COMMAND_NAME;
    private static String DISPLAY_COMMAND_NAME;
    private static String INFO_COMMAND_NAME;
    private static Map<String, String> commandMapping = new HashMap<>();
    private static Set<String> commandMappingKeys = new HashSet<>();
    private static Map<String, String> implMapping = new HashMap<>();

    /**
     * Attempts to access the .properties file for the commands.
     *
     * @return <code>true</code> if the access is successful and can load the data from the file, otherwise <code>false</code>.
     */
    public static boolean accessProperties() {
        try {
            props.load(new FileReader("src/Lab13/res/Commands.properties"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sets the names for the class-implemented commands using loaded properties.
     */
    public static void getCommandNames() {
        SET_COMMAND_NAME = props.getProperty("set-locale.command");
        DISPLAY_COMMAND_NAME = props.getProperty("display-locales.command");
        INFO_COMMAND_NAME = props.getProperty("info.command");
    }

    /**
     * Prints an introductory statement using the current locale's resource bundle.
     *
     * @param baseName the project path to the resource bundle.
     */
    public static void introduction(String baseName) {
        SetLocale setLocale = SetLocale.getInstance();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, setLocale.getCurrentLocale());
        String introductoryMsg = currentLocale.getString("locale.set");
        Locale initialLocale = Locale.getDefault();
        Object[] arguments = {initialLocale.getDisplayLanguage(initialLocale) + " (" +
                initialLocale.getDisplayCountry(initialLocale) + ")"};
        System.out.println(new MessageFormat(introductoryMsg).format(arguments));
    }

    /**
     * Prints simple messages using the current locale's resource bundle.
     *
     * @param baseName    the project path to the resource bundle.
     * @param messageCode the code of the message to be printed.
     */
    public static void simpleMsg(String baseName, String messageCode) {
        SetLocale setLocale = SetLocale.getInstance();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, setLocale.getCurrentLocale());
        if (messageCode.equals("prompt")) {
            System.out.print(currentLocale.getString(messageCode) + ' ');
        } else {
            System.out.println(currentLocale.getString(messageCode));
        }
    }

    /**
     * Displays all supported locales.
     *
     * @param baseName the project path to the resource bundle.
     */
    public static void displayCommand(String baseName) {
        DisplayLocales.getInstance().printAllSupportedLocales(baseName);
    }

    /**
     * Sets a new locale as the current one.
     *
     * @param baseName      the project path to the resource bundle.
     * @param restOfCommand a <code>String</code> representing the command's parameters.
     */
    public static void setCommand(String baseName, String restOfCommand) {
        SetLocale setLocale = SetLocale.getInstance();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, setLocale.getCurrentLocale());
        String[] commandData;
        if (restOfCommand.length() == 0) {
            System.out.println(currentLocale.getString("invalid"));
        } else {
            commandData = restOfCommand.trim().split(" ");
            if (commandData.length < 2) {
                System.out.println(currentLocale.getString("invalid"));
            } else {
                if (commandData.length > 2)
                    System.out.println(currentLocale.getString("invalid"));
                else {
                    setLocale.setCurrentLocale(baseName, new Locale(commandData[0], commandData[1]));
                }
            }
        }
    }

    /**
     * Displays info about a given locale or the current one, if none is given.
     *
     * @param baseName      the project path to the resource bundle.
     * @param restOfCommand a <code>String</code> representing the command's parameters.
     */
    public static void infoCommand(String baseName, String restOfCommand) {
        Info info = Info.getInstance();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, SetLocale.getInstance().getCurrentLocale());
        String[] commandData;
        if (restOfCommand.length() != 0) {
            commandData = restOfCommand.trim().split(" ");
            if (commandData.length < 2) {
                System.out.println(currentLocale.getString("invalid"));
            } else {
                if (commandData.length > 2)
                    System.out.println(currentLocale.getString("invalid"));
                else
                    info.displayInfoAboutLocale(baseName, new Locale(commandData[0], commandData[1]));
            }
        } else {
            info.displayInfoAboutLocale(baseName, null);
        }
    }

    /**
     * Creates a mapping of the commands using information from the loaded properties.
     */
    public static void createCommandMapping() {
        int count = 0;
        String mapKey = null, mapValue = null;
        Set<Object> allKeys = new TreeSet<>(props.keySet());
        for (Object pseudoKey : allKeys) {
            String key = (String) pseudoKey;
            if (key.contains("command")) {
                mapKey = props.getProperty(key);
                commandMappingKeys.add(mapKey);
            } else {
                mapValue = props.getProperty(key);
                implMapping.put(mapValue, key);
            }
            count++;
            if (count == 2) {
                count = 0;
                commandMapping.put(mapKey, mapValue);
            }
        }
    }

    /**
     * Displays the mapping of the commands.
     *
     * @param baseName the project path to the resource bundle.
     */
    public static void mapCommands(String baseName) {
        simpleMsg(baseName, "mapping");
        for (String commandMappingKey : commandMappingKeys) {
            System.out.println("\uD83D\uDDC3 " + commandMappingKey + " \u27FE " + commandMapping.get(commandMappingKey));
        }
    }

    /**
     * Checks if the parameters for the `remap-command` command are the right ones.
     *
     * @param baseName      the project path to the resource bundle.
     * @param restOfCommand a <code>String</code> representing the command's parameters.
     * @return an array of <code>String</code> representing the command's parameters if they were the required ones, otherwise <code>null</code>.
     */
    public static String[] verifyParameters(String baseName, String restOfCommand) {
        String[] commandData;
        if (restOfCommand.length() == 0) {
            simpleMsg(baseName, "invalid");
            return null;
        } else {
            commandData = restOfCommand.trim().split(" ");
            if (commandData.length < 2) {
                simpleMsg(baseName, "invalid");
                return null;
            } else {
                if (commandData.length > 2) {
                    simpleMsg(baseName, "invalid");
                    return null;
                } else {
                    if (commandMapping.get(commandData[0]) == null) {
                        simpleMsg(baseName, "invalid.mapping.key");
                        return null;
                    } else
                        return commandData;
                }
            }
        }
    }

    /**
     * Changes the name of a class-implemented command.
     *
     * @param oldCommandName the old command name.
     * @param newCommandName the new command name.
     * @return <code>true</code> if the operation was successful, otherwise <code>false</code>.
     */
    public static boolean remapCommand(String oldCommandName, String newCommandName) {
        String oldKeyValue = commandMapping.get(oldCommandName);
        String propertiesKey = implMapping.get(oldKeyValue);
        int position = propertiesKey.indexOf('.');
        String propertyGroupRoot = propertiesKey.substring(0, position);
        String commandPropertyName = propertyGroupRoot + ".command";
        props.setProperty(commandPropertyName, newCommandName);
        try {
            props.store(new FileWriter("src/Lab13/res/Commands.properties"), null);
            switch (propertyGroupRoot) {
                case "set-locale":
                    SET_COMMAND_NAME = newCommandName;
                case "display-locales":
                    DISPLAY_COMMAND_NAME = newCommandName;
                case "info":
                    INFO_COMMAND_NAME = newCommandName;
            }
            String commandMappingValue = commandMapping.remove(oldCommandName);
            commandMapping.put(newCommandName, commandMappingValue);
            commandMappingKeys.remove(oldCommandName);
            commandMappingKeys.add(newCommandName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates and sends a JSON POST request to a REST Web Service.
     *
     * @param currentLocale the current locale.
     * @return a <code>HttpURLConnection</code> if the operation was successful, otherwise <code>null</code>.
     */
    public static HttpURLConnection prepareJsonPOSTRequest(Locale currentLocale) {
        try {
            URL url = new URL("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/FullCountryInfo/JSON");
            String query = "{\"sCountryISOCode\":\"" + currentLocale.getISO3Country() + "\"}";
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
            http.setRequestProperty("Accept", "application/json");

            http.setDoOutput(true);
            byte[] out = query.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
            return http;
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * Prints additional information about the current locale's country in a pretty format.
     *
     * @param baseName the project path to the resource bundle.
     */
    public static void getAdditionalInfoAboutLocaleCoutry(String baseName) {
        SetLocale setLocale = SetLocale.getInstance();
        ResourceBundle currentLocale = ResourceBundle.getBundle(baseName, setLocale.getCurrentLocale());

        HttpURLConnection http = prepareJsonPOSTRequest(setLocale.getCurrentLocale());
        if (http != null) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JSONObject json = (JSONObject) JSONValue.parse(response.toString());
                System.out.println(currentLocale.getString("info.extra"));
                System.out.println("\uD83D\uDCA0 " + currentLocale.getString("capital") + " " + json.get("sCapitalCity"));
                System.out.println("\uD83D\uDCA0 " + currentLocale.getString("continent.code") + " " + json.get("sContinentCode"));
                System.out.println("\uD83D\uDCA0 " + currentLocale.getString("country.flag") + " " + json.get("sCountryFlag"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * The `main` method.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);
        String baseName = "Lab13.res.Messages";
        String restOfCommand;

        if (!accessProperties()) {
            return;
        }
        getCommandNames();
        createCommandMapping();
        introduction(baseName);

        while (true) {
            simpleMsg(baseName, "prompt");
            command = scanner.next();
            if (command.equals("quit") || command.equals("exit")) {
                simpleMsg(baseName, "exit");
                return;
            } else {
                if (command.equals(DISPLAY_COMMAND_NAME)) {
                    displayCommand(baseName);
                } else {
                    if (command.equals(SET_COMMAND_NAME)) {
                        restOfCommand = scanner.nextLine();
                        setCommand(baseName, restOfCommand);
                    } else {
                        if (command.equals(INFO_COMMAND_NAME)) {
                            restOfCommand = scanner.nextLine();
                            infoCommand(baseName, restOfCommand);
                        } else {
                            if (command.equals("map-commands")) {
                                mapCommands(baseName);
                            } else {
                                if (command.equals("remap-command")) {
                                    restOfCommand = scanner.nextLine();
                                    String[] commandData = verifyParameters(baseName, restOfCommand);
                                    if (commandData != null) {
                                        if (!remapCommand(commandData[0], commandData[1]))
                                            return;
                                        else {
                                            mapCommands(baseName);
                                        }
                                    }
                                } else {
                                    if (command.equals("locale-country-extra-info")) {
                                        getAdditionalInfoAboutLocaleCoutry(baseName);
                                    } else
                                        simpleMsg(baseName, "invalid");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
