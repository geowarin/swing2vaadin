package com.geowarin.test;

import spoon.Launcher;
import spoon.compiler.SpoonResource;
import spoon.reflect.factory.Factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static spoon.compiler.SpoonResourceHelper.resources;

public class TestUtils {


    public static Factory createTestFactory(List<String> inputFiles, List<String> templateFiles) {
        Launcher spoon = new Launcher();
        spoon.getEnvironment().setAutoImports(true); // optional
//            spoon.getEnvironment().setNoClasspath(true); // optional

        Factory factory = spoon.createFactory();

        List<SpoonResource> inputSources = inputFiles.stream().flatMap(s -> rsrc(getResource(s))).collect(toList());
        List<SpoonResource> templateSources = templateFiles.stream().flatMap(s -> rsrc(s)).collect(toList());

        spoon.createCompiler(factory, inputSources, templateSources).build();
        return factory;
    }

    public static Factory createTestFactory(List<String> inputFiles) {
        return createTestFactory(inputFiles, null);
    }

    public static Stream<SpoonResource> rsrc(String resource) {
        try {
            return resources(resource).stream();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Could not create test factory", e);
        }
    }

    public static String getResource(String name) {
        URL resource = TestUtils.class.getClassLoader().getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("Could not find resource " + name);
        }
        return resource.getFile();
    }

    public static String getResourceText(String name) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(getResource(name)));
            return new String(bytes);
        } catch (IOException e) {
            throw new IllegalStateException("JAVA", e);
        }
    }
}
