package com.geowarin;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.reference.CtExecutableReference;

public class ButtonProcessor extends AbstractProcessor<CtInvocation> {
    @Override
    public void process(CtInvocation element) {

//        CtTypeReference type = element.getType();

        CtExecutableReference executable = element.getExecutable();
        if (executable.getDeclaringType().getSimpleName().equals("AbstractButton")
                && executable.getSimpleName().equals("addActionListener")) {


            AddClickTemplate addClickTemplate = new AddClickTemplate();
            CtExpression target1 = element.getTarget();
            if (target1 == null) {
                throw new IllegalArgumentException("null");
            }
//            addClickTemplate._but_ = creatarget1;

//            CtType<?> target = element.getExecutable().getDeclaringType().getDeclaration();
            CtStatement statement = addClickTemplate.apply(null);
            element.replace(statement);

//            CtInvocation newElement = element.clone();
//            CtExecutableReference<Object> executableReference = getFactory().createExecutableReference();

//            executableReference.setSimpleName("addClickListener");

//            newElement.setExecutable(executableReference);
        }
    }
}
