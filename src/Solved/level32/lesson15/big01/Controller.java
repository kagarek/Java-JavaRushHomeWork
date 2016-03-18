package solved.level32.lesson15.big01;

import solved.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by igormakarychev on 12/22/15.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public static void main(String[] args)
    {
        View viewMain = new View();
        Controller controller = new Controller(viewMain);
        viewMain.setController(controller);
        viewMain.init();
        controller.init();
    }

    public Controller(View view) {this.view = view;}

    public HTMLDocument getDocument() {return document;}

    public void init() {
        createNewDocument();
    }

    public void exit() {System.exit(0);}

    public void resetDocument()
    {
        UndoListener listener = view.getUndoListener();

        if (document != null)
            document.removeUndoableEditListener(listener);

        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(listener);
        view.update();
    }

    public void setPlainText(String text)
    {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try
        {
            new HTMLEditorKit().read(stringReader, document, 0);
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        StringWriter stringWriter = new StringWriter();
        try
        {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }

        return stringWriter.toString();
    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }


    public void openDocument()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int n = jFileChooser.showOpenDialog(view);

        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader fileReader = new FileReader(currentFile))
            {
                new HTMLEditorKit().read(fileReader, document, 0);
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }

    public void saveDocument()
    {
        if (currentFile == null) saveDocumentAs();
        else
        {
            view.selectHtmlTab();
            try (FileWriter fileWriter = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int n = jFileChooser.showSaveDialog(view);

        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }
}
