package com.geowarin;

import com.google.common.reflect.ClassPath;
import org.vafer.jdependency.utils.DependencyUtils;

import java.io.IOException;
import java.util.Set;

public class AnalyzeDeps {

    public static void main(String[] args) throws IOException {
        ClassLoader cl = AnalyzeDeps.class.getClassLoader();
        Set<ClassPath.ClassInfo> classesInPackage = ClassPath.from(cl).getTopLevelClassesRecursive("com.bankapplication");
        System.out.println(classesInPackage);


        System.out.println(classesInPackage.size());

        Set<String> dependencies = DependencyUtils.getDependenciesOfClass(Main.class);
        System.out.println(dependencies);
    }
}
