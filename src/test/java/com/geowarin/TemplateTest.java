package com.geowarin;

import com.geowarin.test.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.factory.Factory;

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
}
