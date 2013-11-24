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
public class SearchMessage{
    private Point2D location;
    private Integer robotId;
    private Integer recipient;


    public SearchMessage(Point2D location, Integer robotId, Integer recipient) {
        this.location = location;
        this.robotId = robotId;
        this.recipient = recipient;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }


    

    public Integer getRobotId() {
        return robotId;
    }

    @Override
    public String toString() {
        return "SearchMessage{" + "location=" + location + ", robotId=" + robotId + ", recipient=" + recipient + '}';
    }


    
    
    
}
