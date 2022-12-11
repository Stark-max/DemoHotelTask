package models;

import models.enums.Category;

import java.time.LocalDate;
import java.util.*;

public class Room {
    public int roomNumber;
    public Category category;
    private Client[] clients;
    private LocalDate startData;
    private LocalDate duration;

    public Room(int num) {
        Random random = new Random();
        roomNumber = num;
        category = Category.values()[random.nextInt(3)];
        clients = new Client[category.getAccommodation()];
    }

    @Override
    public String toString() {
        return "Room{" +
                " roomNumber=" + roomNumber +
                ", category=" + category +
                ", clients=" + Arrays.toString(clients) +
                ", startData=" + startData +
                ", duration=" + duration +
                " }";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean roomIsEmpty() {
        int count = 0;
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                count += 1;
            }
            if (count == clients.length) {
                return true;
            }
        }
        return false;
    }

    public void checkIn(Client clients) {
        System.out.println("День заселения: ");
        /*SimpleDateFormat dayFormat = new SimpleDateFormat("dd,MM,yyyy", Locale.getDefault());*/
        startData = LocalDate.of(inputInteger("Year: "), inputInteger("Month: "), inputInteger("Day: "));

        System.out.println("День выезда: ");
        duration = LocalDate.of(inputInteger("Year: "), inputInteger("Month: "), inputInteger("Day: "));

        if (roomIsEmpty()) {
            this.clients[0] = clients;
        } else {
            System.out.println("Комната занята!");
        }

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

    public Client[] getClients() {
        return clients;
    }

    public void checkIn(Client[] clients) {
        this.clients = clients;
    }

    public LocalDate getStartData() {
        return startData;
    }

    public void setStartData(LocalDate startData) {
        this.startData = startData;
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setClient(Client client) {
        for (int i = 0; i < this.clients.length; i++) {
            if (!roomIsEmpty()) {
                this.clients[0] = client;
            }
        }
    }
}
