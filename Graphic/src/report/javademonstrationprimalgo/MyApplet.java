package report.javademonstrationprimalgo;

import javax.swing.*;
import java.applet.*;
import java.awt.BorderLayout;

public class MyApplet extends Applet{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Dessin d=new Dessin();
    North n = new North(d);
    South s=new South(d);
    JFrame MaFrame = new JFrame ("Algorithms");

    public void init(){

        setLayout(new BorderLayout());
        this.add(n,BorderLayout.NORTH);
        this.add(d, BorderLayout.CENTER);
        this.add(s,BorderLayout.SOUTH);
    }
    public void start(){}
    public void stop(){}
    public void destroy(){}
}
