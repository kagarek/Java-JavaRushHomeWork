package solved.level32.lesson15.big01;

import solved.level32.lesson15.big01.listeners.FrameListener;
import solved.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import solved.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by igormakarycev on 12/22/15.
 */
public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager) ;

    public View()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public Controller getController() {return controller;}

    public void setController(Controller controller) {this.controller = controller;}

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        switch (action) {
            case "Новый" : controller.createNewDocument(); break;
            case "Открыть" : controller.openDocument(); break;
            case "Сохранить" : controller.saveDocument(); break;
            case "Сохранить как..." : controller.saveDocumentAs(); break;
            case "Выход" : controller.exit(); break;
            case "О программе" : showAbout(); break;
            default : break;
        }
    }

    public void init() {
        View v = this;
        v.initGui();
        FrameListener frameListener = new FrameListener(v);
        v.addWindowListener(frameListener);
        v.setVisible(true);
    }

    public void exit() {controller.exit();}

    public void initMenuBar() {
        View v = this;
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(v,jMenuBar);
        MenuHelper.initEditMenu(v,jMenuBar);
        MenuHelper.initStyleMenu(v,jMenuBar);
        MenuHelper.initAlignMenu(v,jMenuBar);
        MenuHelper.initColorMenu(v,jMenuBar);
        MenuHelper.initFontMenu(v,jMenuBar);
        MenuHelper.initHelpMenu(v,jMenuBar);
        Container contentPane = getContentPane();
        contentPane.add(jMenuBar,BorderLayout.NORTH);
    }

    public void initEditor() {
        View v = this;
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(500, 300));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(v));
        Container contentPane = getContentPane();
        contentPane.add(tabbedPane,BorderLayout.CENTER);
    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged()
    {
        if (tabbedPane.getSelectedIndex() == 0)
        {
            String text = plainTextPane.getText();
            controller.setPlainText(text);
        }
        else if (tabbedPane.getSelectedIndex() == 1)
        {
            String text = controller.getPlainText();
            plainTextPane.setText(text);
        }

        resetUndo();
    }

    public boolean canUndo() { return undoManager.canUndo(); }
    public boolean canRedo() { return undoManager.canRedo(); }

    public void undo() {
        try
        {
            undoManager.undo();
        }
        catch (Exception r)
        {
            ExceptionHandler.log(r);
        }
    }

    public void redo() {
        try
        {
            undoManager.redo();
        }
        catch (Exception r)
        {
            ExceptionHandler.log(r);
        }
    }

    public void resetUndo() { undoManager.discardAllEdits();}

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(htmlTextPane, "About this program!", "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
