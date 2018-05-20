import spoon.Launcher;

public class Main {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        // path can be a folder or a file
        // addInputResource can be called several times
        launcher.addInputResource("/Users/geowarin/dev/bisam/BankApplication/src/main/java");

        // if true, the pretty-printed code is readable without fully-qualified names
        launcher.getEnvironment().setAutoImports(true); // optional

        // if true, the model can be built even if the dependencies of the analyzed source code are not known or incomplete
        // the classes that are in the current classpath are taken into account
//        launcher.getEnvironment().setNoClasspath(true); // optional

//        launcher.buildModel();
//        CtModel model = launcher.getModel();
//        System.out.println(model);

        launcher.addProcessor(new FieldProcessor());
        launcher.addProcessor(new AssignmentProcessor());
//        launcher.addProcessor(new ButtonProcessor());
        launcher.run();

    }
}
