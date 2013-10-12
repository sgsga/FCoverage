/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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
