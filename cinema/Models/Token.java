package cinema.Models;

import java.util.UUID;

public class Token {
    private String str;
    private UUID uuid;

    public Token() {
        this.uuid = UUID.randomUUID();
        this.str = this.uuid.toString();
    }

    public Token(UUID uuid) {
        this.uuid = uuid;
        this.str = uuid.toString();
    }

    public Token(String token) {
        this.str = token;
        this.uuid = UUID.fromString(token);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getStr() {
        return str;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setToken(String token) {
        this.str = token;
    }
}
