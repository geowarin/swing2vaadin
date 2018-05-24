package com.geowarin;

import spoon.Launcher;
import spoon.OutputType;
import spoon.support.DefaultOutputDestinationHandler;
import spoon.support.compiler.FileSystemFile;
import spoon.support.compiler.FileSystemFolder;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        File inputDir = new File("/Users/geowarin/dev/bisam/BankApplication/src/main/java");
        File outputDir = new File("/Users/geowarin/dev/bisam/BankApplication/src/main/java");
//
        launcher.addInputResource(new FileSystemFolder(inputDir));
//        launcher.addInputResource("/Users/geowarin/dev/bisam/BankApplication/src/main/java");

        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setCopyResources(false);
        launcher.setSourceOutputDirectory(outputDir);

//        DefaultOutputDestinationHandler outputDestinationHandler =
//                new DefaultOutputDestinationHandler(outputDir, launcher.getEnvironment());
//        launcher.getEnvironment().setOutputDestinationHandler(outputDestinationHandler);
//        launcher.getEnvironment().setOutputType(OutputType.NO_OUTPUT);


        launcher.addTemplateResource(new FileSystemFile("./src/main/java/com/geowarin/AddClickTemplate.java"));

        // if true, the model can be built even if the dependencies of the analyzed source code are not known or incomplete
        // the classes that are in the current classpath are taken into account
//        launcher.getEnvironment().setNoClasspath(true); // optional

        launcher.addProcessor(new FieldProcessor());
        launcher.addProcessor(new AssignmentProcessor());
        launcher.addProcessor(new ButtonProcessor());
        launcher.run();
    }
}
