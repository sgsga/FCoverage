/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models.messaging;

import fcoverage.models.Coordinate;


/**
 *
 * @author nagysan
 */
public class HelloMessage{
    private Coordinate position;
    private Integer sensorId;

    public HelloMessage(Coordinate position, Integer sensorId) {
        this.position = position;
        this.sensorId = sensorId;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Integer getSensorId() {
        return sensorId;
    }
    
    
}
