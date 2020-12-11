package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Statistiques {

    private Integer id;
    private String name;
    private String entry_type;
    private String date_time;
    private Integer value;

    public Statistiques(){
    }

    public Statistiques(Integer id, String name, String entry_type, Integer value) {
        this.id = id;
        this.name = name;
        this.entry_type = entry_type;
        this.date_time = LocalDateTime.now().toString();
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
