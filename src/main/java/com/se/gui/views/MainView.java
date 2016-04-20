package com.se.gui.views;

import com.se.model.objects.dto.Hotel;
import com.se.process.control.HotelSearch;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by AuHuR on 20.04.2016.
 */
public class MainView extends VerticalLayout implements View {

    private int suchCounter;
    private Hotel hotelSelected;

    public void setUp() {

        setMargin(true);
        setResponsive(true);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setResponsive(true);
        horizontalLayout.setSpacing(true);

        final TextField textField = new TextField();
        final Label labelText = new Label("Ort eingeben: ");

        final BeanContainer<Integer, Hotel> data;
        data = new BeanContainer<Integer, Hotel>(Hotel.class);
        data.setBeanIdProperty("id");

        final Table table = new Table("Treffer", data);
        table.setSizeFull();
        table.setSelectable(true);

        Button buchenButton = new Button("Buchen", FontAwesome.BOOK);
        buchenButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                if(MainView.this.hotelSelected == null) {
                    return;
                } else {
                    //öffne neues Window (hotelSelected) zur Buchung

                    System.out.println("Hotel selektiert " + MainView.this.hotelSelected.getName());
                }
            }
        });

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {

                System.out.println("Zeile selektiert: " + itemClickEvent.getItemId());

                BeanItem<Hotel> hotelBean = data.getItem(itemClickEvent.getItemId());
                hotelSelected = hotelBean.getBean();
            }
        });

        //Tutorial Ereignis
        Button button = new Button("Suchen", FontAwesome.SEARCH);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                //HorizontalLayout btnClick = new HorizontalLayout();
                //btnClick.setResponsive(true);

                String ort = textField.getValue();
                if(ort.equals("")) {
                    data.removeAllItems();
                    Notification.show(null, "Bitte geben Sie einen Ort ein!", Notification.Type.WARNING_MESSAGE);
                } else {
                    //btnClick.addComponent(table);
                    MainView.this.suchCounter++;

                    addComponent(table);

                    table.setCaption("Treffer für: " + ort + " (Anzahl der Suchen: " + MainView.this.suchCounter + ")");

                    List<Hotel> listHotel = HotelSearch.getInstance().getHotelByOrt(ort);

                    data.removeAllItems();
                    data.addAll(listHotel);

                    table.setPageLength(table.size());

                    addComponent(buchenButton);
                    setComponentAlignment(buchenButton, Alignment.MIDDLE_CENTER);
                }

                //layout.addComponent(btnClick);
                //layout.setComponentAlignment(btnClick, Alignment.BOTTOM_CENTER);
            }
        });


        //Test für PopUp Fenster bei Button Click
        // button.addClickListener(new Button.ClickListener() {

        //@Override

        //public void buttonClick(ClickEvent clickEvent) {

        //MyPopUp popup = new MyPopUp();

        //popup.setString(textField.getValue());

        //

        //UI.getCurrent().addWindow(popup);

        //}

        //});

        horizontalLayout.addComponents(labelText, textField, button);

        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        this.setUp();
    }

}
