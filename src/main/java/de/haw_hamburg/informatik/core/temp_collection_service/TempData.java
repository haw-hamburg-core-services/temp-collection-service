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
     * temperature
     */
    private final double temperature;

    public TempData(long id, Timestamp timestamp, String srcId, double temperature) {
        this.id = id;
        this.timestamp = timestamp;
        this.srcId = srcId;
        this.temperature = temperature;
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

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "TempData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", srcId='" + srcId + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
