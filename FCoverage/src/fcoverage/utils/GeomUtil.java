/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.utils;

import java.awt.geom.Point2D;

/**
 *
 * @author nagysan
 */
public class GeomUtil {
    public static double getAngle(Point2D a, Point2D b){
        double scalarProduct = a.getX()*b.getX()+a.getY()*b.getY();
        double lengthA = Math.sqrt(a.getX()*a.getX()+a.getY()*a.getY());
        double lengthB = Math.sqrt(b.getX()*b.getX()+b.getY()*b.getY());
        double result = Math.acos(scalarProduct/lengthA/lengthB);
        return result;
    }
}
