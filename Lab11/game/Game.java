package com.example.lab11.game;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * An entity representation of a game.
 */
@Entity
@Table(name = "games", schema = "springboot")
public class Game {
    @Id
    private Integer id;

    @NotNull
    private Integer idPlayer1;

    @NotNull
    private Integer idPlayer2;

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
