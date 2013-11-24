/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models.messaging;

import fcoverage.models.Coordinate;
import java.awt.geom.Point2D;


/**
 *
 * @author nagysan
 */
public class BeaconMessage{
    private Point2D position;
    private Integer robotId;

    public BeaconMessage(Point2D position, Integer robotId) {
        this.position = position;
        this.robotId = robotId;
    }


    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Integer getRobotId() {
        return robotId;
    }

    @Override
    public String toString() {
        return "BeaconMessage{" + "position=" + position + ", robotId=" + robotId + '}';
    }
    
    
    
}
