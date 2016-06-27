package me.academeg.listItem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import me.academeg.api.dataSet.Audio;

public class AudioListItem {

    private Audio audio;
    private BooleanProperty download;

    public AudioListItem(Audio audio) {
        this.audio = audio;
        this.download = new SimpleBooleanProperty();
    }

    public Audio getAudio() {
        return audio;
    }

    public boolean getDownload() {
        return download.get();
    }

    public BooleanProperty downloadProperty() {
        return download;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", audio.getArtist(), audio.getTitle());
    }
}
