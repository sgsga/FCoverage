/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models;

/**
 *
 * @author Richárd
 */
public final class Coordinate {
    private int h;
    private int x;
    private int y;
    public final float A = 20;
    public final float EX = A/2;
    public final float EY = (float) (Math.sqrt(3)*EX);
    public final float DX = 0;
    public final float DY = 0;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.h = (int) Math.ceil(distanceFromPOI()/A);    
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
        return x*EX + DX;
    }
    
    public float getRealY() {
        return y*EY + DY;
    }
    
    public double distanceFrom(Coordinate coordinate) {
        return Math.sqrt(Math.pow(coordinate.getRealX()-this.getRealX(), 2) + Math.pow(coordinate.getRealY()-this.getRealY(), 2));
    }
    
    public double distanceFromPOI() {
        return distanceFrom(new Coordinate(0, 0));
    }
}
