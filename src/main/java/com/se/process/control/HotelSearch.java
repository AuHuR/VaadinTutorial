package com.se.process.control;

import com.se.model.objects.dto.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AuHuR on 20.04.2016.
 */
public class HotelSearch {

    public static HotelSearch search = null;
    Hotel hotel1 = new Hotel("Hotel Maier", 1, "Köln", "Ein schönes Hotel");
    Hotel hotel2 = new Hotel("Hotel Maritim", 2, "Bonn", "Ein wunderschönes Hotel");
    Hotel hotel3 = new Hotel("Hotel Königshof", 3, "Bonn", "Zentrales Hotel");

    public static HotelSearch getInstance() {
        if (search == null) {
            search = new HotelSearch();
        }
        return search;
    }

    public List<Hotel> getHotelByOrt(String ort) {
        //Datenbank-Zugriff (später)

        ArrayList<Hotel> list = new ArrayList<Hotel>();

        if (ort.equals("Bonn")) list.add(hotel2);
        if (ort.equals("Bonn")) list.add(hotel3);
        if (ort.equals("Köln")) list.add(hotel1);

        return list;
    }
}
