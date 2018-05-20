package com.geowarin;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.reference.CtTypeReference;

public class FieldProcessor extends AbstractProcessor<CtField> {
    @Override
    public void process(CtField element) {

        CtTypeReference type = element.getType();
        if (type.getSimpleName().equals("JButton")) {
            CtField newElement = element.clone();
            CtClass<Object> objectCtClass = getFactory().Class().create("com.vaadin.flow.component.button.Button");
            newElement.setType(objectCtClass.getReference());
            element.replace(newElement);
        }
    }
}
