package cinema.Services;

import cinema.Models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class SeatService {
    @Autowired
    private Theater theater = new Theater();
    private ConcurrentMap<UUID, Seat> purchasedSeats = new ConcurrentHashMap<>();
    @JsonProperty("super_secret")
    private final String superSecret = "super_secret";

    public Theater getSeatingInfo() {
        return this.theater;
    }

    public ResponseEntity<Object> purchaseTicket(Seat seat) {
        if(this.theater.getSeats().contains(seat)) {
            Token token = new Token();
            this.theater.getSeats().remove(seat);
            purchasedSeats.put(token.getUuid(), seat);
            return new ResponseEntity<>(new Ticket(token.getUuid(), seat), HttpStatus.OK);
        } else if(purchasedSeats.containsValue(seat)) {
            return new ResponseEntity<>(new SeatError("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new SeatError("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> returnTicket(String str) {
        System.out.println(str);
        Token token = new Token(str);
        if(!purchasedSeats.containsKey(token.getUuid())) {
            return new ResponseEntity<>(new SeatError("Wrong token!"), HttpStatus.BAD_REQUEST);
        } else {
            Seat seat = purchasedSeats.get(token.getUuid());
            ResponseEntity<Object> re = new ResponseEntity<>(new ReturnedTicket(seat), HttpStatus.OK);
            purchasedSeats.remove(token.getUuid());
            return re;
        }
    }

    public ResponseEntity<?> getStatistics(String password) {
        if(password == null) {
            return new ResponseEntity<>(new SeatError("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
        if(password.equals(superSecret)) {
            return new ResponseEntity<>(new Metrics(purchasedSeats), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new SeatError("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }

    public ConcurrentMap<UUID, Seat> getPurchasedSeats() {
        return this.purchasedSeats;
    }
}

class SeatError {
    private String error;

    public SeatError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
