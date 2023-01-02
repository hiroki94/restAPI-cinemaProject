package cinema.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class Metrics {
    @JsonProperty("current_income")
    private int income;
    @JsonProperty("number_of_available_seats")
    private int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public Metrics(ConcurrentMap<UUID, Seat> tickets) {
        for(Seat ticket : tickets.values()) {
            this.income += ticket.getPrice();
        }
        this.availableSeats = 81 - tickets.size();
        this.purchasedTickets = tickets.size();
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(int purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
