import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.reference.CtTypeReference;

public class AssignmentProcessor extends AbstractProcessor<CtConstructorCall> {
    @Override
    public void process(CtConstructorCall element) {

        CtTypeReference type = element.getType();
        if (type.getSimpleName().equals("JButton")) {
            CtConstructorCall newElement = element.clone();
            CtClass<Object> objectCtClass = getFactory().Class().create("com.vaadin.flow.component.button.Button");
            newElement.setType(objectCtClass.getReference());
            element.replace(newElement);
        }
    }
}
