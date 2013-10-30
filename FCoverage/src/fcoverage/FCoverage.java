/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage;

import fcoverage.models.RobotNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import jbotsim.Node;
import jbotsim.ui.JTopology;
import jbotsim.ui.JViewer;

/**
 *
 * @author nagysan
 */
public class FCoverage {
    static JTopology topology;
    static JViewer viewer;
    
    static void printPoints(double  u, double  v, double  x, double  y, double  r){
        double  vx = y-v;
        double  vy = -(x-u);
        double  length = (double ) Math.sqrt(vx*vx+vy*vy);
        vx = vx/length;
        vy = vy/length;
        double  a  = vy;
        double  b  = -vx;
        double  c  = vy*u-vx*v;
        double  A  = c-a*u;
        double  C  = a*a + b*b;
        double  D  = -2*(A*b+v*a*a);
        double  E  = A*A + a*a*(v*v -r*r);
        double  sy1 = (double ) (-D + Math.sqrt(D*D-4*C*E))/(2*C);
        double  sy2 = (double ) (-D - Math.sqrt(D*D-4*C*E))/(2*C);
        double  sx1 = (u!=x)?(c-b*sy1)/a:u;
        double  sx2 = (u!=x)?(c-b*sy2)/a:u;
        System.out.println(Arrays.toString(new double []{vx,vy,a,b,c,A,C,D,E,D*D-4*C*E}));
        System.out.println("(" + sx1 + "," + sy1 + ")");
        System.out.println("(" + sx2 + "," + sy2 + ")");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        printPoints(3f,3f,6f,3f,2f);
        printPoints(6f,3f,3f,3f,2f);
        Integer obstacleCount = 4;
        Integer minObstacleSize = 5;
        Integer maxObstacleSize = 25;
        Random r = new Random(System.currentTimeMillis());
        for(int i = 0; i< obstacleCount;++i){
            Integer size = r.nextInt(maxObstacleSize-minObstacleSize)+minObstacleSize;
            System.out.println(size);
        }
        Node.setModel("default", new RobotNode());
        topology = new JTopology();
        viewer = new JViewer(topology);
    }
}
