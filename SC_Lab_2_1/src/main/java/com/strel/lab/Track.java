package com.strel.lab;

/**
 * Created by Сергей on 26.11.2015.
 */
public class Track {

    private String name;
    private String performer;


    public Track(String name, String performer) {
        this.name = name;
        this.performer = performer;
    }

    public String getName() {
        return name;
    }

    public String getPerformer() {
        return performer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Track track = (Track) obj;
        return name.equalsIgnoreCase(track.getName()) &&
                performer.equalsIgnoreCase(track.getPerformer());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + name.hashCode();
        hash = 59 * hash + performer.hashCode();

        return hash;
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}
