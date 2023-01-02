package cinema.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class Theater {
    @JsonProperty("total_rows")
    private final int rows = 9;
    @JsonProperty("total_columns")
    private final int columns = 9;
    @JsonProperty("available_seats")
    private Set<Seat> seats;

    public Theater() {
        this.seats = new HashSet<Seat>();
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                this.seats.add(new Seat(i+1, j+1));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
