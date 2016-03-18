package solved.level32.lesson15.big01.listeners;

import solved.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by igormakarychev on 12/26/15.
 */
public class TextEditMenuListener implements MenuListener
{

    private View view;

    public TextEditMenuListener(View view)
    {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e)
    {
        JMenu jMenuItem = (JMenu) e.getSource();
        Component[] menuComponents = jMenuItem.getMenuComponents();

        for (Component c : menuComponents)
        {
            c.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e)
    {

    }

    @Override
    public void menuCanceled(MenuEvent e)
    {

    }
}
