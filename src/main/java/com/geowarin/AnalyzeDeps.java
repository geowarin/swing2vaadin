package com.geowarin;

import org.vafer.jdependency.utils.DependencyUtils;

import java.io.IOException;
import java.util.Set;

public class AnalyzeDeps {

    public static void main(String[] args) throws IOException {
        Set<String> dependencies = DependencyUtils.getDependenciesOfClass(Main.class);
        System.out.println(dependencies);
    }
}
