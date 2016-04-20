package com.se.process.control;

import com.se.process.control.exceptions.NoSuchUserPassword;
import com.se.services.util.Views;
import com.vaadin.ui.UI;

/**
 * Created by AuHuR on 20.04.2016.
 */
public class LoginControl {

    public static void checkAuthentication (String login, String pword) throws NoSuchUserPassword {

        //DB-Zugriff

        //Benutzer ist vorhanden:
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

        //Fehlerfall
        //throw new NoSuchUserPassword();
    }
}
