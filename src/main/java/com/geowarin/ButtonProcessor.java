package com.geowarin;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
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


            CtVariableRead button = (CtVariableRead) element.getTarget();
//            CtBlock body = element.getExecutable().getExecutableDeclaration().getBody();
            CtBlock body = (CtBlock<?>) element.getElements(el -> el instanceof CtBlock).get(1);
            AddClickTemplate addClickTemplate = new AddClickTemplate(button, body);

            CtStatement statement = addClickTemplate.apply(null);
            element.replace(statement);
        }
    }
}
