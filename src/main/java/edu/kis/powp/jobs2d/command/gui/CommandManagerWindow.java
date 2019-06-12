package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.JSONCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CommandManagerWindow extends JFrame implements WindowComponent {

    /**
     *
     */
    private static final long serialVersionUID = 9204679248304669948L;
    private DriverCommandManager commandManager;
    private JTextArea currentCommandField;
    private JTextArea newCommandField;
    private String observerListString;
    private JTextArea observerListField;
    private String JSONText;

    public CommandManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("Command Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(observerListField, c);
        updateObserverListField();

        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);
        updateCurrentCommandField();


        newCommandField = new JTextArea("{ \"commands\":[\n" +
                "{\"command\":\"OperateTo\", \n" +
                "\"x\":10," +
                "\"y\":5 \n" +
                "}]\n" +
                "}");
        newCommandField.setEditable(true);
        newCommandField.setLineWrap(true);
        newCommandField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(newCommandField, c);
        updateCurrentCommandField();


        JButton btClearCommand = new JButton("Add command");
        btClearCommand.addActionListener((ActionEvent e) -> this.newCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btClearCommand, c);

        /*JButton btFileCommand = new JButton("Add file");
        btClearCommand.addActionListener((ActionEvent e) -> this.FileCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btClearCommand, c);*/

        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnClearObservers = new JButton("Delete observers");
        btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearObservers, c);
    }

    private void clearCommand() {
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    private void deleteObservers() {
        commandManager.getChangePublisher().clearObservers();
        this.updateObserverListField();
    }

    private void updateObserverListField() {
        observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        observerListField.setText(observerListString);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        updateObserverListField();
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    /*private void FileCommand(){
        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
            File file = fileChooser.getSelectedFile(); //utworzenie obiektu i przypisanie mu wybranego pliku
            Scanner in = null; //zdeklarowanie obiektu scanner który posłuży do odczytania plikiu
            try {
                in = new Scanner(file);//utworzenie obiektu który wczytuje plik zawarty w obiekcie file
                while (in.hasNext()) { //dopóki file ma nastepny znak
                    String line = in.nextLine(); //wczytanie linijki z file
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                in.close();
            }

        }
    }*/

    private void newCommand() {
        JSONText = newCommandField.getText();
        commandManager.setCommandReader(new JSONCommand());
        commandManager.getCommandReader().read(JSONText);
        commandManager.setCurrentCommand(commandManager.getCommandReader().getCommandsList(), commandManager.getCommandReader().getName());
    }
}
