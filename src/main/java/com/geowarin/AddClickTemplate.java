package com.geowarin;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtVariableRead;
import spoon.template.Parameter;
import spoon.template.StatementTemplate;
import spoon.template.TemplateParameter;

public class AddClickTemplate extends StatementTemplate {
    public TemplateParameter<Button> _but_;

    @Parameter
    CtBlock _original_;

    public AddClickTemplate(CtVariableRead _but_, CtBlock _original_) {
        this._but_ = _but_;
        this._original_ = _original_;
    }

    @Override
    public void statement() {
        _but_.S().addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                _original_.S();
            }
        });
    }
}
