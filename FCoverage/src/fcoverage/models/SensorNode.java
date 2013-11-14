/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models;

import static fcoverage.FCoverage.alreadyAdded;
import fcoverage.models.messaging.HelloMessage;
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
    
    private HelloMessage myHelloMessage;

    public SensorNode() {
    }

    public SensorNode(Coordinate myPosition) {
        this.myPosition = myPosition;
        myHelloMessage = new HelloMessage(myPosition);
        Clock.addClockListener(this, 1);
        this.setLocation(myPosition.getRealX(), myPosition.getRealY());
        setSensingRange(0);
        enableWireless();
        setCommunicationRange(21);
        addMessageListener(this);
        this.setState(myPosition);
        
        evl.add(new Coordinate(myPosition.getX()-2, myPosition.getY()));
        evl.add(new Coordinate(myPosition.getX()+2, myPosition.getY()));
        evl.add(new Coordinate(myPosition.getX()-1, myPosition.getY()+1));
        evl.add(new Coordinate(myPosition.getX()-1, myPosition.getY()-1));
        evl.add(new Coordinate(myPosition.getX()+1, myPosition.getY()+1));
        evl.add(new Coordinate(myPosition.getX()+1, myPosition.getY()-1));
        Iterator<Coordinate> it;
        
        for (it = evl.iterator(); it.hasNext(); ) {
            Coordinate c = it.next();
            neighbours.put(c, 1);
        }
    }

    @Override
    public void onClock() {
        //System.out.println("Clock");
        send(null, myHelloMessage);
        for (Entry<Coordinate, Integer> c : neighbours.entrySet()) {
            // if node received message
            if (c.getValue().equals(0)) {
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
        Object message = msg.content;
        if (message instanceof HelloMessage) {
            HelloMessage hm = (HelloMessage) message;
            neighbours.put(hm.getPosition(), 0);
        }
    }
    
    public void reactiveAdvertisingRoutine() {
        
    }
    
}
