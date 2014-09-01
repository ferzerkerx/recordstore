package com.ferzerkerx.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="artist")
public class Artist implements Serializable {

    @Id
    @Column(name="artist_id")
    @SequenceGenerator(name="pk_sequence", sequenceName="artist_artist_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="pk_sequence")
    private int id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="artist", cascade={CascadeType.DETACH}, fetch = FetchType.LAZY)
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
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
