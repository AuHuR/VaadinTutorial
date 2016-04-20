package com.se.services.util;

/**
 * Created by Moritz on 20.04.16.
 */
public class DBLoginData {

    public static final String USER = "vaadin";
    public static final String PASSWORD = "vaadin";

    private static final String DATABASE = "vaadin_tutorial";
    private static final String ADDRESS = "auhur.myds.me";
    private static final String PORT = "3306";

    public static final String URL = "jdbc:mariadb://" + ADDRESS + ":" + PORT + "/" +DATABASE;
}
