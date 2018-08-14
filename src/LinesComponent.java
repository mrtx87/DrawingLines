import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


//COMMENT

public class LinesComponent extends JComponent{



public ArrayList<Polyline> POINTS = new ArrayList<Polyline>();

	
public void addLine(Polyline polyline	) {
          
	//polyline.printPoints();
	POINTS.add(polyline);
    repaint();
}

public void clearLines() {
    
	POINTS = new ArrayList<Polyline>();
	generateCoordinateSystem();
    repaint();
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    	for(Polyline polyline : POINTS) {
    		if(polyline.useOffset) {
    			
    			int[] xPoints = addOffset(polyline.getPointsX(), 500, true);
    			int[] yPoints = addOffset(polyline.getPointsY(), 600, false);
    			g.drawPolyline(xPoints, yPoints, xPoints.length);
    			
    		}else {
    			
    			int[] xPoints = polyline.getPixelPointsX();
    			int[] yPoints = polyline.getPixelPointsY();
    			g.drawPolyline(xPoints, yPoints, xPoints.length);
    		}
    	}
}


public double getValue(JTextField text) {
	return toDouble(text.getText());
}

public void generateCoordinateSystem() {
    //DRAW SURROUNDINGS
    
    Polyline x_Axis = new Polyline(2, false);
    x_Axis.addPoint(new Point(0,600));
    x_Axis.addPoint(new Point(1000, 600));
    
    Polyline y_Axis = new Polyline(2, false);
    y_Axis.addPoint(new Point(500,0));
    y_Axis.addPoint(new Point(500, 750));
    
    //Y Markings
    int yOffset = 50;
    for(int i = 1; i < 16; i++) {
    	Point p = new Point(490, yOffset * i);
    	Point p2 = new Point(510, yOffset * i);
    	Polyline pl = new Polyline(2, false);
    	pl.addPoint(p);
    	pl.addPoint(p2);
    	addLine(pl);
    }
    
    // X Markings
    int xOffset = 50;
    for(int i = 1; i < 20; i++) {
    	Point p = new Point(xOffset * i, 610);
    	Point p2 = new Point(xOffset * i, 590);
    	Polyline pl = new Polyline(2, false);
    	pl.addPoint(p);
    	pl.addPoint(p2);
    	addLine(pl);
    }
    
    addLine(x_Axis);
    addLine(y_Axis);
}

public static void main(String[] args) {
    JFrame testFrame = new JFrame();
    testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	final LinesComponent lc = new LinesComponent();
    lc.setPreferredSize(new Dimension(1000, 760));
    testFrame.getContentPane().add(lc, BorderLayout.CENTER);
    JPanel controlPanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JButton x_linear = new JButton("a * x + c");
    JButton x_squared = new JButton("a * x² * b*x + c");
    JButton x_sinus = new JButton("sin(a * x) + c");
    JButton x_squareroot = new JButton("a * sqrt(x) + c");
    JButton x_cosinus = new JButton("cos(a * x) + c");

    
    JButton clearButton = new JButton("Clear");
    
    controlPanel.add(buttonsPanel);
    controlPanel.add(inputPanel);
    
    JLabel a_label = new JLabel("a: ");
    JLabel b_label = new JLabel("b: ");
    JLabel c_label = new JLabel("c: ");
    
    JTextField a_Text = new JTextField();
    a_Text.setText("1");
    a_Text.setPreferredSize(new Dimension(50, 20));
    JTextField b_Text = new JTextField();
    b_Text.setText("0");
    b_Text.setPreferredSize(new Dimension(50, 20));
    JTextField c_Text = new JTextField();
    c_Text.setText("0");
    c_Text.setPreferredSize(new Dimension(50, 20));

    controlPanel.setLayout(new GridLayout(0, 1));
    
    buttonsPanel.add(x_linear);
    buttonsPanel.add(x_sinus);
    buttonsPanel.add(x_squared);
    buttonsPanel.add(x_squareroot);
    buttonsPanel.add(x_cosinus);
    buttonsPanel.add(clearButton);
    
    inputPanel.add(a_label);
    inputPanel.add(a_Text);
    
    inputPanel.add(b_label);
    inputPanel.add(b_Text);
    
    inputPanel.add(c_label);
    inputPanel.add(c_Text);
    
    testFrame.getContentPane().add(controlPanel, BorderLayout.EAST);
   
    x_sinus.addActionListener(new ActionListener() {
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	//sin x
        	Polyline points = lc.generateDots((x) -> Math.sin(lc.getValue(a_Text) * x) + lc.getValue(c_Text) );
        	lc.addLine(points);
        }
    });
    
    x_cosinus.addActionListener(new ActionListener() {
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	//sin x
        	Polyline points = lc.generateDots((x) -> Math.cos(lc.getValue(a_Text) * x) + lc.getValue(c_Text) );
        	lc.addLine(points);
        }
    });

    x_linear.addActionListener(new ActionListener() {
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	//a*x + c
        	Polyline points = lc.generateDots((x) -> lc.getValue(a_Text) * x + lc.getValue(c_Text) );
        	lc.addLine(points);
        }
    });
    
    x_squared.addActionListener(new ActionListener() {
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	//x² bx + c
        	Polyline points = lc.generateDots((x) -> lc.getValue(a_Text)*(x*x) + lc.getValue(b_Text) * x + lc.getValue(c_Text) );
        	lc.addLine(points);
        }
    });
    
    x_squareroot.addActionListener(new ActionListener() {
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	//x² bx + c
        	Polyline points = lc.generateDots((x) -> lc.getValue(a_Text) * Math.sqrt(x)  + lc.getValue(c_Text) );
        	lc.addLine(points);
        }
    });
    
    
    clearButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            lc.clearLines();
        }
    });
    
    lc.generateCoordinateSystem();

    testFrame.pack();
    testFrame.setVisible(true);

	}

public double toDouble(String str) {
	
	return Double.parseDouble(str);
	
}

interface DoubleMath {
	double operation(double x);   
}

public int[] addOffset(double[] axis_points, int offset, boolean positiveOffset) {
	
	int[] offsetPixels = new int[axis_points.length];
	for(int i = 0; i < axis_points.length; i++) {
		if(positiveOffset) {
			offsetPixels[i] = offset + (int) Math.round((axis_points[i] * 50));
		}else {
			offsetPixels[i] = offset - (int) Math.round((axis_points[i] * 50));
		}
	}
	return offsetPixels;
	
}

public Polyline generateDots(DoubleMath dm) {
	Polyline points = new Polyline(400, true);
	
	double x = 0;
	for(int i = 0; i < 400; i++) {
		Point p = new Point(x-10 ,  dm.operation(x-10));
		points.addPoint(p, i);
		x += 0.05d;
	}
	return points;
}

}