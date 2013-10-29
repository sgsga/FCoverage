/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage;

/**
 *
 * @author Richárd
 */
public class Coordinate {
    private int h;
    private int x;
    private int y;
    public final float A = 20;
    public final float EX = A/2;
    public final float EY = (float) (Math.sqrt(3)*EX);

    public Coordinate(int h, int x, int y) {
        this.h = h;
        this.x = x;
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.h != other.h) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.h;
        hash = 13 * hash + this.x;
        hash = 13 * hash + this.y;
        return hash;
    }
    
    public float getRealX() {
        return x*EX;
    }
    
    public float getRealY() {
        return y*EY;
    }
    
    public double distanceFrom(Coordinate coordinate) {
        return Math.sqrt(Math.pow(coordinate.x-x, 2) + Math.pow(coordinate.y-y, 2));
    }
    
    public double distanceFromPOI() {
        return distanceFrom(new Coordinate(0, 0, 0));
    }
}
