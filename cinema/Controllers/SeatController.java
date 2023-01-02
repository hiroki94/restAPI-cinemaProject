package cinema.Controllers;

import cinema.Models.Seat;
import cinema.Models.Theater;
import cinema.Models.Token;
import cinema.Services.SeatService;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class SeatController {
    private final SeatService seatService;
    private Set<Seat> purchasedSeats = new HashSet<>();
    private ConcurrentMap<String, Seat> tickets = new ConcurrentHashMap<>();

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seats")
    public Theater getSeats() {
        return this.seatService.getSeatingInfo();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeats(@RequestBody Seat seat) {
        return this.seatService.purchaseTicket(new Seat(seat.getRow(), seat.getColumn()));
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnSeats(@RequestBody String str) {
        String[] strArr = str.split("\"");
        return this.seatService.returnTicket(strArr[3]);
    }

    @GetMapping("/purchase")
    public ConcurrentMap<UUID, Seat> getTickets() {
        return this.seatService.getPurchasedSeats();
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        return this.seatService.getStatistics(password);
    }
    
}
