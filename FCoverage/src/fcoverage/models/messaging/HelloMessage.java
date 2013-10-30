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

    public HelloMessage(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
    
    
}
