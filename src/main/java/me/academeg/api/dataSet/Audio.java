package me.academeg.api.dataSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audio {

    private long id;
    @JsonProperty(value = "owner_id")
    private long ownerId;
    @JsonProperty(value = "artist", defaultValue = "123", required = true)
    private String artist = "";
    private String title = "";
    private long duration;
    private long date;
    private String url;
    @JsonProperty(value = "lyrics_id")
    private long lyricsId;
    @JsonProperty(value = "genre_id")
    private long genreId;

    public Audio() {
    }

    public long getId() {
        return id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public long getDuration() {
        return duration;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public long getLyricsId() {
        return lyricsId;
    }

    public long getGenreId() {
        return genreId;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", date=" + date +
                ", url='" + url + '\'' +
                ", lyricsId=" + lyricsId +
                ", genreId=" + genreId +
                '}';
    }
}
