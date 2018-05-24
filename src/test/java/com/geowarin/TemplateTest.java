package com.geowarin;

import com.geowarin.test.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spoon.Launcher;
import spoon.OutputType;
import spoon.reflect.CtModel;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.PrettyPrinter;

import static java.util.Arrays.asList;

class TemplateTest {

    @Test
    void testExampleTemplate() {

        Factory factory = TestUtils.createTestFactory(
                asList("./template/InTemplate.java"),
                asList("./src/test/java/com/geowarin/CheckBoundTemplate.java")
        );

        CtClass<?> c = factory.Class().get("template.InTemplate");
        CtMethod<?> method = c.getMethodsByName("method").get(0);

        CheckBoundTemplate t = new CheckBoundTemplate();
        CtParameter<?> param = method.getParameters().get(0);
        t.setVariable(param);

        // getting the final AST
        CtStatement injectedCode = t.apply(null);

        // adds the bound check at the beginning of a method
        method.getBody().insertBegin(injectedCode);

        Assertions.assertEquals(TestUtils.getResourceText("./template/OutTemplate.java"), c.toString());
    }

    @Test
    void addClick() {
        Factory factory = TestUtils.createTestFactory(
                asList("./buttonTemplate/ButtonTemplate.java"),
                asList("./src/main/java/com/geowarin/AddClickTemplate.java")
        );
        Launcher launcher = new Launcher(factory);
        launcher.addProcessor(new ButtonProcessor());

        launcher.run();

        CtClass<Object> result = factory.Class().get("template.ButtonTemplate");
        Assertions.assertEquals(TestUtils.getResourceText("./buttonTemplate/OutButtonTemplate.java"), result.toString());
    }

    @Test
    void testAddClickTemplate() {

        Factory factory = TestUtils.createTestFactory(
                asList("./buttonTemplate/ButtonTemplate.java"),
                asList("./src/main/java/com/geowarin/AddClickTemplate.java")
        );

        CtClass<?> c = factory.Class().get("template.ButtonTemplate");
        CtMethod<?> method = c.getMethodsByName("method").get(0);
        CtInvocation statement = method.getBody().getStatement(1);

        CtVariableRead target = (CtVariableRead) statement.getTarget();
//        CtBlock<?> body = statement.getExecutable().getExecutableDeclaration().getBody();
        // FIXME: ugly
        CtBlock<?> body = (CtBlock<?>) statement.getElements(element -> element instanceof CtBlock).get(1);

        AddClickTemplate t = new AddClickTemplate(target, body);
//        CtParameter<?> param = method.getParameters().get(0);

        // getting the final AST
        CtInvocation injectedCode = (CtInvocation) t.apply(null);
//        injectedCode.setB

        // adds the bound check at the beginning of a method
//        method.getBody().insertBegin(injectedCode);
//        injectedCode.getExecutable().set
        statement.replace(injectedCode);

        Assertions.assertEquals(TestUtils.getResourceText("./buttonTemplate/OutButtonTemplate.java"), c.toString());
    }
}
