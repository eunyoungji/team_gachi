package com.example.team_gachi.column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Column {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
