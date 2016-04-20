package com.se.gui.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Created by Moritz on 19.04.16.
 */
public class MyPopUp extends Window {

    //Testklasse für Popup Fenster aus MainView Button Click
    private String string;

    public MyPopUp() {
        super();
        center();

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.addComponent(new Label("Sie suchen in: " + string));
        setClosable(false);
        setModal(true);
        setContent(content);

        Button btn = new Button("OK");
        btn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        content.addComponent(btn);
    }

    public void setString (String string) {
        this.string=string;
    }

}
