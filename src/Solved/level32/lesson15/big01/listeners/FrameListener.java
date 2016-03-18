package solved.level32.lesson15.big01.listeners;

import solved.level32.lesson15.big01.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by igormakarychev on 12/22/15.
 */
public class FrameListener extends WindowAdapter
{
    private View view;

    public FrameListener(View view) {this.view = view;}

    public void windowClosing(WindowEvent windowEvent) {view.exit();}
}
