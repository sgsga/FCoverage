/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcoverage.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Polygon;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import jbotsim.Topology;
import jbotsim.ui.JTopology;

/**
 *
 * @author sgsga
 */
public class CustomJTopology extends JTopology {

    public CustomJTopology() {
    }

    public CustomJTopology(Topology tplg) {
        super(tplg);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }
}
