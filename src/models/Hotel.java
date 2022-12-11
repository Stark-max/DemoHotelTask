package models;

import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    private ArrayList<Room> rooms;
    public Client client;

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Hotel() {
        initializeRooms();
        menu();
    }

    private void initializeRooms() {
        rooms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rooms.add(new Room(i));
        }
    }

    private void menu() {
        registration();
        boolean action =true;
        while (action)
            switch (inputInteger("1.Свободные комнаты\n2.Бронирование\n3.Удаление бронирования\n4.Регистрация\n0.Exit")) {
                case 1 -> displayEmptyRooms();
                case 2 -> reservation();
                case 3 -> deleteReservation();
                case 4 -> registration();
                case 0 -> action=false;//System.exit(0);
            }
    }

    private void deleteReservation() {
        int rum = inputInteger("Введите номер комнаты для удаления брони ->");
        for(Room r: rooms){
            if(!r.roomIsEmpty()){
                if(r.getRoomNumber()==rum){
                    r.setClient((Client) null);
                    r.setStartData(null);
                    r.setDuration(null);
                }
            }
        }
    }

    private void reservation() {
        int rum = inputInteger("Введите номер комнаты для бронирования ->");
        for(Room r: rooms){
            if(r.roomIsEmpty()){
                if(r.getRoomNumber()==rum){
                    r.checkIn(client);
                }
            }
        }
    }

    private void displayEmptyRooms() {
        System.out.println("Свободные комнаты: ");
        for (Room r : rooms) {
            if (r.roomIsEmpty()) {
                System.out.println(r);
            }
        }
        System.out.println("Бронированные: ");
        for (Room r : rooms) {
            if (!r.roomIsEmpty()) {
                System.out.println(r);
            }
        }
    }

    private void registration() {
        System.out.println("Добро пожаловать!");
        System.out.println("Пройдите регистрацию чтобы забронировать номер ->");
        client = new Client(inputString("Введите ФИО: "), inputInteger("Введите возраст: "), inputString("Введите номер телефона"));
        System.out.println("Регистрация прошла успешно!");

    }

    private String inputString(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String input = scanner.nextLine();
        if (input.length() == 10) {
            return input;
        } else if (input.length() < 10) {
            System.out.println("Номер телефона должен состоят из 10");
            return inputString("Повторите попытку еще раз ->");
        }

        return input;
    }

    private int inputInteger(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        if (scanner.hasNext()) {
            return scanner.nextInt();
        } else {
            return inputInteger("Введены неверные данные, повторите попытку ->");
        }
    }

    public Room getOccupied(){
        for (Room r : rooms) {
            if (!r.roomIsEmpty()) {
                return r;
            }
        }
        return null;
    }
}
