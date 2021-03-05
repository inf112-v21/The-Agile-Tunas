package network.test;



import com.esotericsoftware.kryonet.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameScreen extends JFrame {
    CardLayout cardLayout;
    JList messageList;
    JTextField sendText;
    JButton sendButton;
    Client client;

    public GameScreen(Client client) {
        super("Game Client");
        this.client = client;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 200);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        {
            JPanel panel = new JPanel(new BorderLayout());
            contentPane.add(panel, "game");
            {
                JPanel topPanel = new JPanel(new GridLayout(1, 1));
                panel.add(topPanel);
                {
                    topPanel.add(new JScrollPane(messageList = new JList()));
                    messageList.setModel(new DefaultListModel());
                }
                DefaultListSelectionModel disableSelections = new DefaultListSelectionModel() {
                    public void setSelectionInterval(int index0, int index1) {
                    }
                };
                messageList.setSelectionModel(disableSelections);

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

    public void setSendListener(final Runnable listener) {
        sendButton.addActionListener(evt -> {
            if (getSendText().length() == 0) return;
            listener.run();
            sendText.setText("");
            sendText.requestFocus();
        });
    }

    public void setCloseListener(final Runnable listener) {
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent evt) {
                listener.run();
            }

            public void windowActivated(WindowEvent evt) {
                sendText.requestFocus();
            }
        });
    }

    public String getSendText() {
        return sendText.getText().trim();
    }


    public void updateScreen(final String message) {
        EventQueue.invokeLater(() -> {
            DefaultListModel model = (DefaultListModel) messageList.getModel();
            model.addElement(message);
            messageList.ensureIndexIsVisible(model.size() - 1);
        });
    }
}
