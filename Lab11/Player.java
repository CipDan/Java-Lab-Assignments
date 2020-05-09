package com.example.lab11;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * An entity representation of a player.
 */
@Entity
@Table(name = "players", schema = "springboot")
public class Player {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private int gameId;

    public Player() {
        super();
    }

    public Player(Integer id, String name, int gameId) {
        super();
        this.id = id;
        this.name = name;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getGameId() {
        return gameId;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return gameId == player.gameId &&
                id.equals(player.id) &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gameId);
    }
}
