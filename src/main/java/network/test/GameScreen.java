package network.test;

import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.GameHandler;
import inf112.skeleton.app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GameScreen extends JFrame {
    CardLayout cardLayout;
    JList messageList;
    JTextField sendText;
    JButton sendButton;
    JList nameList;
    GameHandler game;

    public GameScreen (String host) {
        super("Game Client");
        game = new GameHandler();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 200);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        {
            JPanel panel = new JPanel(new BorderLayout());
            contentPane.add(panel, "chat");
            {
                JPanel topPanel = new JPanel(new GridLayout(1, 2));
                panel.add(topPanel);
                {
                    topPanel.add(new JScrollPane(messageList = new JList()));
                    messageList.setModel(new DefaultListModel());
                }
                {
                    topPanel.add(new JScrollPane(nameList = new JList()));
                    nameList.setModel(new DefaultListModel());
                }
                DefaultListSelectionModel disableSelections = new DefaultListSelectionModel() {
                    public void setSelectionInterval (int index0, int index1) {
                    }
                };
                messageList.setSelectionModel(disableSelections);
                nameList.setSelectionModel(disableSelections);
            }
            {
                JPanel bottomPanel = new JPanel(new GridBagLayout());
                panel.add(bottomPanel, BorderLayout.SOUTH);
                bottomPanel.add(sendText = new JTextField(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                bottomPanel.add(sendButton = new JButton("Send"), new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
            }
        }

        sendText.addActionListener(e -> sendButton.doClick());
    }

    public void setSendListener (final Runnable listener) {
        sendButton.addActionListener(evt -> {
            if (getSendText().length() == 0) return;
            listener.run();
            sendText.setText("");
            sendText.requestFocus();
        });
    }

    public void setCloseListener (final Runnable listener) {
        addWindowListener(new WindowAdapter() {
            public void windowClosed (WindowEvent evt) {
                listener.run();
            }

            public void windowActivated (WindowEvent evt) {
                sendText.requestFocus();
            }
        });
    }

    public String getSendText () {
        return sendText.getText().trim();
    }

    public void setNames (final String[] names) {
        // This listener is run on the client's update thread, which was started by client.start().
        // We must be careful to only interact with Swing components on the Swing event thread.
        EventQueue.invokeLater(() -> {
            cardLayout.show(getContentPane(), "chat");
            DefaultListModel model = (DefaultListModel)nameList.getModel();
            model.removeAllElements();
            for (String name : names)
                model.addElement(name);
        });
    }
    public void updateScreen() {
        Container contentPane = getContentPane();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        JPanel panel = new JPanel(new BorderLayout());
        contentPane.add(panel, "progress");
        panel.add(new JLabel("Welcome player"));
        System.out.println("Updated screen");

    }

    public void addMessage (final String message) {
        EventQueue.invokeLater(() -> {
            DefaultListModel model = (DefaultListModel)messageList.getModel();
            model.addElement(message);
            messageList.ensureIndexIsVisible(model.size() - 1);
        });
    }


}
