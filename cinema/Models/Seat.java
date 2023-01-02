package cinema.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean available;

    public Seat() {
        this.price = this.row <= 4 ? 10 : 8;
        this.available = true;
    }
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.available = true;
    }

    @Override
    public int hashCode() {
        int hashcode = 7;
        hashcode = 31 * hashcode + (int) row;
        hashcode = 31 * hashcode + (int) column;
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Seat) {
            Seat seat = (Seat) obj;
            return (seat.row == this.row && seat.column == this.column);
        } else {
            return false;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "row: " + row + " column: " + column;
    }
}
