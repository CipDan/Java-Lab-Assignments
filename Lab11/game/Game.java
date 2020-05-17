package com.example.lab11.game;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * An entity representation of a game.
 */
@ApiModel(description = "Details about a game")
@Entity
@Table(name = "games", schema = "springboot")
public class Game {
    @ApiModelProperty(notes = "The unique id of the game")
    @Id
    private Integer id;

    @ApiModelProperty(notes = "The id of the first player that is currently in the game")
    @NotNull
    private Integer idPlayer1;

    @ApiModelProperty(notes = "The id of the second player that is currently in the game")
    @NotNull
    private Integer idPlayer2;

    @ApiModelProperty(notes = "The game's name")
    @NotEmpty
    private String name;

    public Game() {
        super();
    }

    public Game(Integer id, Integer idPlayer1, Integer idPlayer2, String name) {
        super();
        this.id = id;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPlayer1() {
        return idPlayer1;
    }

    public Integer getIdPlayer2() {
        return idPlayer2;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdPlayer1(Integer idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public void setIdPlayer2(Integer idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id) &&
                idPlayer1.equals(game.idPlayer1) &&
                idPlayer2.equals(game.idPlayer2) &&
                name.equals(game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPlayer1, idPlayer2, name);
    }
}
