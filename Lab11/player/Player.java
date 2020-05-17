package com.example.lab11.player;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * An entity representation of a player.
 */
@ApiModel(description = "Details about a player")
@Entity
@Table(name = "players", schema = "springboot")
public class Player {
    @ApiModelProperty(notes = "The unique id of the player")
    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty(notes = "The player's name")
    @NotEmpty
    private String name;

    @ApiModelProperty(notes = "The id of the game in which the player is at the moment")
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
