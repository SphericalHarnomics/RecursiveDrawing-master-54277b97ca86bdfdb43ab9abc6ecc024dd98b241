import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;


public class Canvas extends JComponent {
	
	/**
	 * Canvas - a simple canvas for drawing onto.
	 * 
	 * @author Eric McCreath GPLV2
	 */
	
	private static final Dimension SIZE = new Dimension(700,700);
	RecursiveDrawing rd;
	Graphics2D g;
	ArrayList<AffineTransform> stack;
	double time;
	HashMap<Double,BufferedImage> images;
	
	public Canvas(RecursiveDrawing rd) {
		this.setPreferredSize(SIZE);
		this.rd = rd;
		stack =  new ArrayList<AffineTransform>();
		time = 0.0;
		images = new HashMap<Double,BufferedImage>();
	}

	public void line(double x1, double y1, double x2, double y2) {
		g.draw(new Line2D.Double(x1, y1, x2, y2));
	}
	
	public void translate(double x, double y) {
		g.translate(x, y);
	}
	
	public void scale(double s) {
		g.scale(s, s);
	}
	
	public void rotate(double r) {
		g.rotate(Math.PI*r/180.0);
	}
	
	@Override
	protected void paintComponent(Graphics g1) {
		
		BufferedImage bi;
		if ((bi = images.get(time)) == null) {
			bi = new BufferedImage(SIZE.width,SIZE.height, BufferedImage.TYPE_INT_RGB);
			g = bi.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, SIZE.width, SIZE.height);
			g.setColor(Color.red);
			
			g.scale(SIZE.getWidth()/100.0, -SIZE.getHeight()/100.0);
			g.translate(50.0, -50.0); 
			g.setStroke(new BasicStroke(0.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
			rd.draw(this,0,time);
			images.put(time, bi);
		}
		g1.drawImage(bi, 0, 0, SIZE.width, SIZE.height, null);
		
	}

	
	
	
	public void save() {
		stack.add(g.getTransform());
	}
	
	public void restore() {
	    g.setTransform(stack.remove(0));	
	}
}
