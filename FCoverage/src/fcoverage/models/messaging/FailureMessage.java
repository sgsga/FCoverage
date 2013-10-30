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
public class FailureMessage {
    private Coordinate position;
    private Coordinate senderPosition;

    public FailureMessage(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Coordinate getSenderPosition() {
        return senderPosition;
    }

    public void setSenderPosition(Coordinate senderPosition) {
        this.senderPosition = senderPosition;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.position != null ? this.position.hashCode() : 0);
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
        final FailureMessage other = (FailureMessage) obj;
        if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
            return false;
        }
        return true;
    }
    
    
}
