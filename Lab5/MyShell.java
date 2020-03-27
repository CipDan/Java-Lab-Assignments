package Lab5;

import org.apache.commons.lang.ArrayUtils;

import java.util.*;

public class MyShell {
    public static void executeShell() {
        String[] args;
        String auxiliary;
        List<Catalog> catalogs = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        LoadCommand commandList = new LoadCommand();
        while (true) {
            System.out.print("shell>");

            auxiliary = scanner.nextLine();
            args = auxiliary.split(" ");
            String commandName = args[0];
            args = (String[]) ArrayUtils.remove(args, 0);

            if (!commandName.equals("")) {
                if (commandName.equals("exit")) {
                    System.out.println("Have a nice day \uD83D\uDE04!");
                    System.exit(0);
                } else {
                    if (commandName.equals("create-catalog")) {
                        commandList.load(commandName, catalogs, args);
                    } else {
                        if (commandName.equals("add-document")) {
                            commandList.load(commandName, catalogs, args);
                        } else {
                            if (commandName.equals("save-catalog")) {
                                commandList.load(commandName, catalogs, args);
                            } else {
                                if (commandName.equals("load-catalog")) {
                                    commandList.load(commandName, catalogs, args);
                                } else {
                                    if (commandName.equals("view-document")) {
                                        commandList.load(commandName, catalogs, args);
                                    } else {
                                        if (commandName.equals("add-tag")) {
                                            commandList.load(commandName, catalogs, args);
                                        } else {
                                            if (commandName.equals("report")) {
                                                commandList.load(commandName, catalogs, args);
                                            } else {
                                                if (commandName.equals("info")) {
                                                    commandList.load(commandName, catalogs, args);
                                                } else System.out.println("I don't know what you want \uD83D\uDE30!");
                                            }//end eight else
                                        }//end seventh else
                                    }//end sixth else
                                }//end fifth else
                            }//end forth else
                        }//end third else
                    }//end second else
                }//end first else
            }//end if
        }//end while
    }
}
