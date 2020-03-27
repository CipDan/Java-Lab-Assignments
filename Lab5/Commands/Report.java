package Lab5.Commands;

import Lab5.Catalog;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

public class Report implements Command {

    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 2) {
            int index;
            for (index = 0; index < catalogs.size(); index++) {
                if (catalogs.get(index).getName().equals(params[1]))
                    break;
            }
            if (index == catalogs.size()) {
                System.out.println("Catalog with name " + params[1] + " not found!");
            } else {
                if (params[0].toUpperCase().equals("HTML")) {
                    try {
                        prepareReportHTML(catalogs.get(index));
                        System.out.println("Report created and loaded successfully!");
                    } catch (IOException e) {
                        System.out.println("Unexpected error when loading the report!");
                    }
                } else {
                    System.out.println("Format is not supported!");
                }
            }
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 2 parameters for this command!");
        }
    }

    private void prepareReportHTML(Catalog catalog) throws IOException {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.put("file.resource.loader.path", "./src/Lab5/Templates");
        velocityEngine.init(props);

        Template template = velocityEngine.getTemplate("VelocityTemplate.vm");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("catalog", catalog);

        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);

        FileWriter fileWriter = new FileWriter("report.html");
        fileWriter.write(writer.toString());
        fileWriter.close();
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("report.html"));
    }
}
