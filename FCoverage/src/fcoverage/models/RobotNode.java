/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.models;

import fcoverage.models.messaging.BeaconMessage;
import fcoverage.models.messaging.HelloMessage;
import fcoverage.models.messaging.SearchMessage;
import fcoverage.models.messaging.SearchResultMessage;
import fcoverage.utils.GeomUtil;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
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
public class RobotNode extends Node implements ClockListener, MessageListener {

    Coordinate basePoint;
    Integer id = getNextId();
    Coordinate marchingPoint = null;
    BeaconMessage myBeaconMessage;
    boolean beaconing = true;
    Integer sensorAtBP = 100;
    final Integer CAPACITY = 50;
    Integer sensorLoaded = 0;
    double speed = 1;
    private static Integer idSeq = 0;
    private static final ArrayBlockingQueue<Coordinate> baseCoordinates = new ArrayBlockingQueue<Coordinate>(6);

    static {
        baseCoordinates.add(new Coordinate(0, 0));
        baseCoordinates.add(new Coordinate(40, -20));
        baseCoordinates.add(new Coordinate(-40, 20));
        baseCoordinates.add(new Coordinate(-40, -20));
        baseCoordinates.add(new Coordinate(40, 20));
    }
    boolean working = true;
    boolean marching = true;

    public static synchronized Integer getNextId() {
        return idSeq++;
    }

    public static synchronized Coordinate getBasePoint() {
        return baseCoordinates.poll();
    }

    public RobotNode() {
        setColor("green");
        basePoint = getBasePoint();
        this.setLocation(basePoint.getRealLocation());
        myBeaconMessage = new BeaconMessage(basePoint.getRealLocation(), id);
        marchingPoint = new Coordinate(0, 0);
        Clock.addClockListener(this, 1);
        setSensingRange(0);
        enableWireless();
        setCommunicationRange(40);
        addMessageListener(this);
    }


    private void setMarchingPoint(Coordinate mp) {
        marchingPoint = mp;
        marching = (marchingPoint != null);
    }

    @Override
    public void onClock() {
        if (working) {
            if (marching) {
                this.setDirection(marchingPoint.getRealLocation());
                this.move(1 * speed);
                if (getLocation().distance(marchingPoint.getRealLocation()) < 1) {
                    setLocation(marchingPoint.getRealLocation());
                    setMarchingPoint(null);
                }
                myBeaconMessage.setPosition(getLocation());
            }
            if (getLocation().equals(new Coordinate(0, 0).getRealLocation())) {
                sensorLoaded--;
            }
            if (sensorLoaded == 0) {
                if (distance(basePoint.getRealLocation()) == 0) {
                    if (sensorAtBP > CAPACITY) {
                        sensorLoaded += CAPACITY;
                        sensorAtBP -= CAPACITY;
                    } else {
                        sensorLoaded = sensorAtBP;
                        sensorAtBP = 0;

                    }
                    if (sensorAtBP == sensorLoaded && sensorAtBP == 0) {
                        working = false;
                        setColor("orange");
                    }
                    setMarchingPoint(new Coordinate(0, 0));
                } else {
                    setMarchingPoint(basePoint);
                }
            }
            //this.move(1);//To change body of generated methods, choose Tools | Templates.
            if (beaconing) {
                send(null, myBeaconMessage);
            }
        }
    }

    @Override
    public void onMessage(Message msg) {
        if (working) {
            if (msg.content instanceof HelloMessage) {
                HelloMessage hm = (HelloMessage) msg.content;
                marching = false;
                beaconing = false;
                send(null, new SearchMessage(getLocation(), id, hm.getSensorId()));
            }
            if (msg.content instanceof SearchResultMessage) {
                SearchResultMessage srm = (SearchResultMessage) msg.content;
                setMarchingPoint(srm.getPosition());
                beaconing = true;
                
            }
        }
    }
}
