package com.se.process.control;

import com.se.model.objects.dto.User;
import com.se.process.control.exceptions.NoSuchUserPassword;
import com.se.services.db.JDBCConnection;
import com.se.services.util.Roles;
import com.se.services.util.Views;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by AuHuR on 20.04.2016.
 */
public class LoginControl {

    public static void checkAuthentication (String login, String pword) throws NoSuchUserPassword {

        ResultSet set = null;

        //DB-Zugriff
        Statement statement = JDBCConnection.getInstance().getStatement();
        try {
            set = statement.executeQuery("SELECT *"
                    + "FROM user "
                    + "WHERE login = \'"
                    + login + "\' "
                    + "AND password = \'"
                    + pword + "\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = null;

        try {
            if (set.next()) {
                user = new User();
                user.setLogin(set.getString(1));
                user.setVorname(set.getString(3));
            } else {
                throw new NoSuchUserPassword();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        VaadinSession session = UI.getCurrent().getSession();
        session.setAttribute(Roles.CURRENT_USER, user);

        //Benutzer ist vorhanden:
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }
}
