/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage;

import java.util.HashSet;
import java.util.Random;
import jbotsim.Clock;
import jbotsim.Message;
import jbotsim.Node;
import jbotsim.Topology;
import jbotsim.event.ClockListener;
import jbotsim.event.MessageListener;

/**
 *
 * @author sgsga
 */
public class SensorNode extends Node implements ClockListener, MessageListener{
    private Coordinate myPosition;
    private HashSet<Coordinate> evl = new HashSet<Coordinate>();

    public SensorNode() {
        /*Random r = new Random(System.currentTimeMillis());
        int x = r.nextInt(800);
        int y = r.nextInt(600);
        this.setLocation(x, y);
        this.setDirection(-Math.PI/2);*/   
    }

    public SensorNode(Coordinate myPosition) {
        this.myPosition = myPosition;
        Clock.addClockListener(this, 1);
        setSensingRange(0);
        enableWireless();
        setCommunicationRange(40);
        addMessageListener(this);
    }

    @Override
    public void onClock() {
        this.move(1);//To change body of generated methods, choose Tools | Templates.
        send(null, "Hello");
    }

    @Override
    public void onMessage(Message msg) {
        System.out.println(msg.content);
    }
    
    public void reactiveAdvertisingRoutine() {
        
    }
    
}
