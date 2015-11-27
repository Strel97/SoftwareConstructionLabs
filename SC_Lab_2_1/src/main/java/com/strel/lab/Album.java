package com.strel.lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Properties;

/**
 * Created by Сергей on 26.11.2015.
 */
public class Album {

    private HashSet<Track>  tracklist;
    private String          group;
    private String          name;
    private int             year;


    public Album() {
        group = "<unknown>";
        name = "<unknown>";
        year = 0;
    }

    public Album(HashSet<Track> tracklist, String group, String name, int year) {
        this.tracklist = tracklist;
        this.group = group;
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getGroup() {
        return group;
    }

    public HashSet<Track> getTracklist() {
        return tracklist;
    }


    /**
     * Moves tracks with given word count into ArrayDeque.
     *
     * @param wordCnt   Parameter for word recognizing and their
     *                  further moving. Count of words in song name
     * @return          ArrayDeque with removed songs
     */
    public ArrayDeque<Track> moveTracks(int wordCnt) {
        ArrayDeque<Track> tracks = new ArrayDeque<>();
        tracklist.forEach(track -> {
            if (track.getName().split(" ").length == wordCnt)
                tracks.add(track);
        });

        tracks.forEach(tracklist::remove);
        return tracks;
    }


    /**
     * Checks whether the track with given name and performer name
     * is in current album.
     *
     * @param name      Track name
     * @param performer Performer name
     * @return          Is track stored in current album?
     */
    public boolean containsTrack(String name, String performer) {
        return tracklist.contains(new Track(name, performer));
    }


    /**
     * Loads information about album from properties file
     * into current class. All old information is deleted.
     *
     * @param filename Name of properties file with album data
     */
    public void load(String filename) {
        try {
            // Initializing file stream
            Properties data = new Properties();
            data.load(new FileInputStream(filename));

            // Main album info
            group = data.getProperty("Group");
            name = data.getProperty("Name");
            year = Integer.valueOf(data.getProperty("Year"));

            checkParamIsNull(group, "You must specify the group name!");
            checkParamIsNull(name, "You must specify the track name!");

            // Loading track list
            tracklist = new HashSet<>();
            int track_cnt = Integer.valueOf(data.getProperty("TracksNum"));

            for (int i = 0; i < track_cnt; i++) {
                tracklist.add(new Track(data.getProperty("Track" + (i + 1)), group));
            }
        }
        catch (FileNotFoundException ex) {
            System.err.println("ERR: Please specify correct name and path to the file!");
        }
        catch (IOException ex) {
            System.err.println("ERR: IO exception!");
        }
        catch (NumberFormatException ex) {
            System.err.println("ERR: Specify correct number for year!");
        }
        catch (MusicException ex) {
            System.err.println("ERR: " + ex.getMessage());
        }
    }

    public boolean checkParamIsNull(String param, String msg)
        throws MusicException
    {
        if (param == null)
            throw new MusicException(msg);

        return true;
    }
}
