/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage;

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

    public SensorNode() {
        Random r = new Random(System.currentTimeMillis());
        int x = r.nextInt(800);
        int y = r.nextInt(600);
        this.setLocation(x, y);
        this.setDirection(-Math.PI/2);
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
    
}
