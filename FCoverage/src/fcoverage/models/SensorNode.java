/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models;

import static fcoverage.FCoverage.alreadyAdded;
import static fcoverage.models.RobotNode.getNextId;
import fcoverage.models.messaging.FailureMessage;
import fcoverage.models.messaging.HelloMessage;
import fcoverage.models.messaging.SearchMessage;
import fcoverage.models.messaging.SearchResultMessage;
import fcoverage.models.messaging.SensorSearchMessage;
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
public class SensorNode extends Node implements ClockListener, MessageListener {

    private Coordinate myPosition;
    private HashSet<Coordinate> evl = new HashSet<Coordinate>();    // empty or death nodes
    private ConcurrentHashMap<Coordinate, Integer> neighbours = new ConcurrentHashMap<Coordinate, Integer>();
    private HashSet<SensorSearchMessage> receivedMessages = new HashSet<SensorSearchMessage>();
    private HashSet<FailureMessage> receivedFailureMessages = new HashSet<FailureMessage>();
    private final int MAX_TICK = 5;
    Integer id = getNextId();
    private static Integer idSeq = 0;

    public static synchronized Integer getNextId() {
        return idSeq++;
    }
    private HelloMessage myHelloMessage;
    private boolean working = false;

    
    public static void reset(){
        idSeq = 0;
    }
    public SensorNode() {
    }

    public SensorNode(Coordinate myPosition) {
        this.myPosition = myPosition;
        myHelloMessage = new HelloMessage(myPosition,id);
        Clock.addClockListener(this, 1);
        this.setLocation(myPosition.getRealX(), myPosition.getRealY());
        setSensingRange(0);
        enableWireless();
        setCommunicationRange(21);
        addMessageListener(this);
        this.setState(myPosition);

        evl.add(new Coordinate(myPosition.getX() - 2, myPosition.getY()));
        evl.add(new Coordinate(myPosition.getX() + 2, myPosition.getY()));
        evl.add(new Coordinate(myPosition.getX() - 1, myPosition.getY() + 1));
        evl.add(new Coordinate(myPosition.getX() - 1, myPosition.getY() - 1));
        evl.add(new Coordinate(myPosition.getX() + 1, myPosition.getY() + 1));
        evl.add(new Coordinate(myPosition.getX() + 1, myPosition.getY() - 1));
        Iterator<Coordinate> it;

        for (it = evl.iterator(); it.hasNext();) {
            Coordinate c = it.next();
            neighbours.put(c, 1);
        }
    }

    @Override
    public void onClock() {
        if (working){
        //System.out.println("Clock");
        send(null, myHelloMessage);
        for (Entry<Coordinate, Integer> c : neighbours.entrySet()) {
            // if node received message
            if (c.getValue().equals(0)) {
                evl.remove(c.getKey());
            } else if (c.getValue() > MAX_TICK) {
                evl.add(c.getKey());
                send(null,new FailureMessage(c.getKey(), myPosition));
            }
            neighbours.put(c.getKey(), c.getValue() + 1);
        }
        }
    }

    @Override
    public void onMessage(Message msg) {
        if (working){
        Object message = msg.content;
        if (message instanceof SearchMessage) 
        {
            SearchMessage sm = (SearchMessage) message;
            SensorSearchMessage ssm = new SensorSearchMessage(sm.getLocation(), id, myPosition);
            ssm.getEvl().addAll(evl);
            send(null, ssm);
        }
        if (message instanceof SensorSearchMessage) {
            SensorSearchMessage ssm = (SensorSearchMessage) message;
            if (isBorderNode()) { // Border node
                if (!receivedMessages.contains(ssm)){
                    SensorSearchMessage sensorSearchMessage = new SensorSearchMessage(ssm.getPosition(), id, myPosition);
                    sensorSearchMessage.getEvl().addAll(evl);
                    sensorSearchMessage.getEvl().addAll(ssm.getEvl());
                    send(null, sensorSearchMessage);
                } else {
                    Coordinate c = ssm.getEvl().iterator().next();
                    SearchResultMessage srm = new SearchResultMessage(c);
                    send(null,srm);
                }
            }
            receivedMessages.add(ssm);
        }
        if (message instanceof FailureMessage){
            FailureMessage fm = (FailureMessage) message;
            if (isBorderNode()){
                evl.add(fm.getPosition());
            }
            if (!receivedFailureMessages.contains(fm)){
                send(null,new FailureMessage(fm.getPosition(), myPosition));
            }
            receivedFailureMessages.add(fm);
        }
        if (message instanceof HelloMessage) {
            HelloMessage hm = (HelloMessage) message;
            neighbours.put(hm.getPosition(), 0);
        }
        }
    }

    private boolean isBorderNode() {
        return true;//throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void arm() {working = true;}
    public void unArm() {working = false;}
}
