package models.enums;

public enum Category {
    STANDARD(1),BUSINESS(3),VIP(4);
    private int accommodation;

    Category(int accommodation) {
        this.accommodation = accommodation;
    }

    public int getAccommodation() {
        return accommodation;
    }
}
