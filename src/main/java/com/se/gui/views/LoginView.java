package com.se.gui.views;

import com.se.process.control.LoginControl;
import com.se.process.control.exceptions.NoSuchUserPassword;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

/**
 * Created by AuHuR on 20.04.2016.
 */
public class LoginView extends VerticalLayout implements View {

    public void setUp() {
        this.setSizeFull();
        this.setResponsive(true);

        final TextField userID = new TextField();
        userID.setCaption("UserID: ");

        final PasswordField userPassword = new PasswordField();
        userPassword.setCaption("Passwort: ");

        Button loginButton = new Button("Login", FontAwesome.SEARCH);

        VerticalLayout content = new VerticalLayout();
        content.setResponsive(true);

        content.addComponent(userID);
        content.addComponent(userPassword);
        content.addComponent(loginButton);
        content.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);

        Panel panel = new Panel("Bitte Login Daten eingeben:");
        panel.addStyleName("login");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(content);
        panel.setSizeUndefined();

        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String login = userID.getValue();
                String pword = userPassword.getValue();

                try {
                    LoginControl.checkAuthentication(login, pword);

                } catch (NoSuchUserPassword noSuchUserPassword) {

                    Notification.show("Fehler", "UserID oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                    userID.setValue("");
                    userPassword.setValue("");
                }
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        this.setUp();
    }
}
