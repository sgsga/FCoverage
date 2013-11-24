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
public class SearchResultMessage{
    private Coordinate position;

    public SearchResultMessage(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
    
    
}
