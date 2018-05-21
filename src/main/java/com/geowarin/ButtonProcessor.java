package com.geowarin;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.reference.CtExecutableReference;

public class ButtonProcessor extends AbstractProcessor<CtInvocation> {
    @Override
    public void process(CtInvocation element) {

        CtExecutableReference executable = element.getExecutable();
        if (executable.getDeclaringType().getSimpleName().equals("AbstractButton")
                && executable.getSimpleName().equals("addActionListener")) {


            AddClickTemplate addClickTemplate = new AddClickTemplate();
            CtVariableRead target1 = (CtVariableRead) element.getTarget();
            addClickTemplate.setVariable(target1);

            CtStatement statement = addClickTemplate.apply(null);
            element.replace(statement);
        }
    }
}
