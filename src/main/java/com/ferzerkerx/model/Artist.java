package com.ferzerkerx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @Column(name = "artist_id")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "artist_artist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private int id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "artist", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Record> records;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Record> getRecords() {
        if (Objects.nonNull(records)) {
            return Collections.unmodifiableList(records);
        }
        return Collections.emptyList();
    }

    public void setRecords(List<Record> records) {
        this.records = new ArrayList<>(records);
    }
}
