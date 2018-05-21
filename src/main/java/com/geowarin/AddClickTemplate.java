package com.geowarin;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import spoon.reflect.code.CtVariableRead;
import spoon.template.StatementTemplate;
import spoon.template.TemplateParameter;

public class AddClickTemplate extends StatementTemplate {
    public TemplateParameter<Button> _but_;

    @Override
    public void statement() {
        _but_.S().addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                System.out.println("toto");
            }
        });
    }

    /**
     * Sets _col_ to be replaced by a reference to variable (a local variable, a
     * field, a parameter
     */
    public void setVariable(CtVariableRead var) {
//        CtVariableRead<Button> va = var.getFactory().Core().createVariableRead();
//        va.setVariable((CtVariableReference<Button>) var.getReference());
        _but_ = var;
    }
}
