import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import spoon.template.StatementTemplate;
import spoon.template.TemplateParameter;

public class AddClickTemplate extends StatementTemplate {
    TemplateParameter<Button> _but_;

    @Override
    public void statement() throws Throwable {
        _but_.S().addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                System.out.println("tooto");
            }
        });
    }
}
