package cinema.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {
    @JsonProperty("returned_ticket")
    private Seat seat;

    public ReturnedTicket(Seat seat) {
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
