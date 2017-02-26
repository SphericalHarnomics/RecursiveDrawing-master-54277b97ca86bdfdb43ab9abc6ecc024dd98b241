import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public abstract class RecursiveDrawing implements Runnable, ActionListener {

	/**
	 *  RecursiveDrawing - this class creates a drawing GUI.  It is extended 
	 *  with a draw method implemented with the particular drawing you would like.
	 * 
	 * @author Eric McCreath GPLV2
	 */
	
	
	
	abstract public void draw(Canvas canvas, int step, double time);
	
	

	JFrame jframe;
	JButton animatebutton;
	Canvas canvas;
	Timer timer;

	public RecursiveDrawing() {
		SwingUtilities.invokeLater(this);
	}

	public void run() {
		jframe = new JFrame("RecursvieDrawing");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		animatebutton = new JButton("Animate");
		animatebutton.addActionListener(this);
		canvas = new Canvas(this);
		jframe.getContentPane().add(canvas, BorderLayout.CENTER);
		jframe.getContentPane().add(animatebutton, BorderLayout.PAGE_END);
		timer = new Timer(50, this);
		jframe.setVisible(true);
		jframe.pack();
	}

    public void actionPerformed(ActionEvent ae) {
    	if (ae.getSource() == animatebutton) {
    		if (timer.isRunning()) timer.stop();
    		else timer.start();
    	} else if (ae.getSource() == timer) {
    		canvas.time += 0.05;
    		if (canvas.time > 10.0) canvas.time = 0.0;
    		canvas.repaint();
    	}
		
	}

}
