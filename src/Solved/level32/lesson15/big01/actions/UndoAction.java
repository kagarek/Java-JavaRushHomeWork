package solved.level32.lesson15.big01.actions;

import solved.level32.lesson15.big01.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by igormakarychev on 12/26/15.
 */
public class UndoAction extends AbstractAction
{
    private View view;

    public UndoAction(View view)
    {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        view.undo();
    }
}
