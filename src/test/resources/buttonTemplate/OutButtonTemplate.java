class ButtonTemplate {
    public void method() {
        javax.swing.JButton jButton = new javax.swing.JButton();
        jButton.addClickListener(new com.vaadin.flow.component.ComponentEventListener<com.vaadin.flow.component.ClickEvent<com.vaadin.flow.component.button.Button>>() {
            @java.lang.Override
            public void onComponentEvent(com.vaadin.flow.component.ClickEvent<com.vaadin.flow.component.button.Button> event) {
                java.lang.System.out.println("toto " + event);
            }
        });
    }
}