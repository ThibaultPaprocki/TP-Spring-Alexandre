package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import javax.persistence.*;

@Entity
@Table(name = "statistiques")
public class StatistiquesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "entry_type")
    private String entry_type;

    @Column(name = "date_time")
    private String date_time;

    @Column(name = "value")
    private Integer value;

    public StatistiquesEntity() {
    }

    public StatistiquesEntity(Integer id, String name, String entry_type, String date_time, Integer value) {
        this.id = id;
        this.name = name;
        this.entry_type = entry_type;
        this.date_time = date_time;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntry_type() {
        return entry_type;
    }

    public void setEntry_type(String entry_type) {
        this.entry_type = entry_type;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}