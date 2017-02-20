package de.haw_hamburg.informatik.core.temp_collection_service;

import java.sql.Timestamp;

/**
 * Created by TimoHäckel on 30.01.2017.
 */
public class TempData {

    /**
     * identifier for the data object
     */
    private final long id;

    /**
     * timestamp of the data set.
     */
    private final Timestamp timestamp;

    /**
     * identifier for the source
     */
    private final String srcId;

    /**
     * state if its raining or not
     */
    private final boolean raining;

    /**
     * intensity of the rain from 0 (no rain at all) to 10 (heavy rain)
     */
    private final int intensity;

    public RainData(long id, Timestamp timestamp, String srcId, boolean raining, int intensity) {
        this.id = id;
        this.timestamp = timestamp;
        this.srcId = srcId;
        this.raining = raining;
        if(intensity > 10){
            intensity = 10;
        } else if(intensity < 0){
            intensity = 0;
        }
        this.intensity = intensity;
    }

    public long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getSrcId() {
        return srcId;
    }

    public boolean isRaining() {
        return raining;
    }

    public int getIntensity() {
        return intensity;
    }

    @Override
    public String toString() {
        return "RainData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", srcId='" + srcId + '\'' +
                ", raining=" + raining +
                ", intensity=" + intensity +
                '}';
    }
}
