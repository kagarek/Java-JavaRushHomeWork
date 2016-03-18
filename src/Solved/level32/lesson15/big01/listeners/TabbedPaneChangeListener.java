package solved.level32.lesson15.big01.listeners;

import solved.level32.lesson15.big01.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by igormakarychev on 12/22/15.
 */
public class TabbedPaneChangeListener implements ChangeListener
{
    private View view;

    public TabbedPaneChangeListener(View view) {this.view = view;}

    @Override
    public void stateChanged(ChangeEvent e)
    {
        view.selectedTabChanged();
    }
}
