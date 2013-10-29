/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
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
    private HashSet<Coordinate> evl = new HashSet<Coordinate>();    // empty or death nodes
    private ConcurrentHashMap<Coordinate, Integer> neighbours = new ConcurrentHashMap<Coordinate, Integer>();
    private final int MAX_TICK = 5;

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
        double distanceFromPOI = myPosition.distanceFromPOI();
        
        evl.add(new Coordinate(myPosition.getX()-2, myPosition.getY()));
        evl.add(new Coordinate(myPosition.getX()+2, myPosition.getY()));
        evl.add(new Coordinate(myPosition.getX()-1, myPosition.getY()+1));
        evl.add(new Coordinate(myPosition.getX()-1, myPosition.getY()-1));
        evl.add(new Coordinate(myPosition.getX()+1, myPosition.getY()+1));
        evl.add(new Coordinate(myPosition.getX()+1, myPosition.getY()-1));
        Iterator<Coordinate> it;
        
        for (it = evl.iterator(); it.hasNext(); ) {
            neighbours.put(it.next(), 0);
        }
    }

    @Override
    public void onClock() {
        for (Entry<Coordinate, Integer> c : neighbours.entrySet()) {
            // if node received message
            if (c.getValue() == 0) {
                evl.remove(c.getKey());
            } else if (c.getValue() > MAX_TICK) {
                evl.add(c.getKey());
                // TODO: send failure notification
            }
            neighbours.put(c.getKey(), c.getValue() + 1);
        }
    }

    @Override
    public void onMessage(Message msg) {
        System.out.println(msg.content);
    }
    
    public void reactiveAdvertisingRoutine() {
        
    }
    
}
