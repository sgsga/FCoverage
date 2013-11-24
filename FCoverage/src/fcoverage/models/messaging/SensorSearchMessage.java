/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models.messaging;

import fcoverage.models.Coordinate;
import java.awt.geom.Point2D;
import java.util.HashSet;


/**
 *
 * @author nagysan
 */
public class SensorSearchMessage{
    private Point2D position;
    private Integer sensorId;
    private Coordinate senderCoordinate;
    private HashSet<Coordinate> evl = new HashSet<Coordinate>();

    public SensorSearchMessage(Point2D position, Integer sensorId, Coordinate senderCoordinate) {
        this.position = position;
        this.sensorId = sensorId;
        this.senderCoordinate = senderCoordinate;
    }

    public HashSet<Coordinate> getEvl() {
        return evl;
    }

    
    public Coordinate getSenderCoordinate() {
        return senderCoordinate;
    }

    public void setSenderCoordinate(Coordinate senderCoordinate) {
        this.senderCoordinate = senderCoordinate;
    }


    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    @Override
    public String toString() {
        return "SensorSearchMessage{" + "position=" + position + ", sensorId=" + sensorId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 61 * hash + (this.sensorId != null ? this.sensorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SensorSearchMessage other = (SensorSearchMessage) obj;
        if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
            return false;
        }
        if (this.sensorId != other.sensorId && (this.sensorId == null || !this.sensorId.equals(other.sensorId))) {
            return false;
        }
        return true;
    }
    
    
 
}
