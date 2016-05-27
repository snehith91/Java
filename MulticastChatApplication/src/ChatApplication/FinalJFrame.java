package ChatApplication;

//To create the MulticastSocket and InetAddress(IP Address)
import java.net.*;
//Use input and output through data streams
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalJFrame extends javax.swing.JFrame {

    //Creation of MulticastSocket and InetAddress variables for sending and receving Data Packets
    MulticastSocket chat;
    InetAddress group;
    int portNumber = 9757;
    //ConnectionStatus is used to check whether user is online or not
    boolean connectionStatus = false;

    //FinalJFrame Constructor for creating the GUI
    public FinalJFrame() {
        this.setTitle("Multicast Chat Application");
        initComponents();
        initEnter();
    }

    //Receiver class to receive the DatagramPacket
    public class Receiver implements Runnable {

        @Override
        //Thread executes the following code when it executes its start() function
        public void run() {
            if (connectionStatus) {
                try {
                    while (true) {
                        byte receiveBuffer[] = new byte[1000];
                        //Creating DatagramPacket object to receive the packet
                        DatagramPacket receiveDatagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                        //Receive the DatagramPacket
                        chat.receive(receiveDatagramPacket);
                        //to make sure USER MESSAGE not to receive again
                        boolean result = InetAddress.getLocalHost().getHostAddress().equals(receiveDatagramPacket.getAddress().getHostName());
                        if (!result) {
                            String receivedMessage = new String(receiveDatagramPacket.getData()).trim();
                            //To print the message in text area
                            textArea.append(receiveDatagramPacket.getAddress().getHostName() + ": " + receivedMessage + "\n");
                        }
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    // To invoke the Receive Thread, to receive the data packet parallelely while sending the data packet
    public void invokeReceiveThread() {
        Thread Receiver = new Thread(new Receiver());
        if (!Receiver.isAlive()) {
            Receiver.start();
        }
    }

    //Hit enter key to send the message typed in text field

    private void initEnter() {
        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActionPerformed(evt);
            }
        });
    }

    // Auto generated code for generating GUI
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        textField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        signInButton = new javax.swing.JButton();
        signOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        signInButton.setText("Sign In");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        signOutButton.setText("Sign Out");
        signOutButton.setEnabled(false);
        signOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(signInButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signOutButton)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signInButton)
                    .addComponent(signOutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {

        if (connectionStatus) {
            try {
                // Receive the text entered by user
                String userText = textField.getText();
                // DatagramPacket object creation for sending the packet 
                DatagramPacket sendDatagramPacket = new DatagramPacket(userText.getBytes(), 0, userText.length(), group, portNumber);
                //Send the packet to MulticastSocket
                chat.send(sendDatagramPacket);
                // To print the user message in text area
                textArea.append(InetAddress.getLocalHost().getHostName() + ": " + userText + "\n");
                // To wrap the text printed in text area
                textArea.setLineWrap(true);
                // To set the text field back to blank 
                textField.setText("");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            try {
                textArea.append("\n \t" + InetAddress.getLocalHost().getHostName() + " Please sign in to send the message");
            } catch (UnknownHostException ex) {
                Logger.getLogger(FinalJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    // TO send the Data Packet
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        if (connectionStatus) {
            try {
                // Receive the text entered by user
                String userText = textField.getText();
                // DatagramPacket object creation for sending the packet 
                DatagramPacket sendDatagramPacket = new DatagramPacket(userText.getBytes(), 0, userText.length(), group, portNumber);
                //Send the packet to MulticastSocket
                chat.send(sendDatagramPacket);
                // To print the user message in text area
                textArea.append(InetAddress.getLocalHost().getHostName() + ": " + userText + "\n");
                // To wrap the text printed in text area
                textArea.setLineWrap(true);

                // To set the text field back to blank 
                textField.setText("");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            try {
                textArea.append("\n \t" + InetAddress.getLocalHost().getHostName() + " Please sign in to send the message");
            } catch (UnknownHostException ex) {
                Logger.getLogger(FinalJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        if (!connectionStatus) {
            try {
                // To make sure user wont sign in again
                signInButton.setEnabled(false);
                //Creating connection for multicast chat
                chat = new MulticastSocket(portNumber);
                group = InetAddress.getByName("224.2.2.5");
                chat.joinGroup(group);
                //As the user is signed in, connectionStatus variable is made true
                connectionStatus = true;
                //Initializing Receive Thread to start receiving the packets 
                invokeReceiveThread();
                //As the user is signed in, user can now sign out so sign out button is enabled
                signOutButton.setEnabled(true);
                textArea.setText("");
                //Sign In message
                textArea.append("\n \t" + InetAddress.getLocalHost().getHostName() + " logged in successfully \n");
                String signInMessage = " joined the group chat";
                DatagramPacket signInMessagePacket = new DatagramPacket(signInMessage.getBytes(), 0, signInMessage.length(), group, portNumber);
                //Sending the sign in message to the peers connected on network
                chat.send(signInMessagePacket);

            } catch (IOException IO) {
                System.out.println(IO);
            }
        } else {
            try {
                // If user tries to re-sign in, the following message is displayed to the user
                textArea.append("\n \t " + InetAddress.getLocalHost().getHostName() + " you are already logged in!!");
            } catch (IOException IO) {
                System.out.println(IO);
            }
        }

    }//GEN-LAST:event_signInButtonActionPerformed
    //Sign out button to close the group chat
    private void signOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButtonActionPerformed

        if (connectionStatus) {
            //Sending sign out message to user and to peers
            String signOutMessage = " left the group";
            DatagramPacket signOutMessagePacket = new DatagramPacket(signOutMessage.getBytes(), 0, signOutMessage.length(), group, portNumber);
            try {
                chat.send(signOutMessagePacket);
                connectionStatus = false;
                //Sign out message sent and the user is made to leave the group
                chat.leaveGroup(group);
                chat.close();
                //Sign in button is enabled for user to sign in
                signInButton.setEnabled(true);
                //Sign out button is disabled as the user is already signed out
                signOutButton.setEnabled(false);
                chat.close();
                textArea.setText("");
                textArea.append("\n \tSuccessfully signed out of group chat");
            } catch (IOException IO) {
                System.out.println(IO);
            }
        }

    }//GEN-LAST:event_signOutButtonActionPerformed

    public static void main(String args[]) throws IOException {
        //Create JFrame object
        FinalJFrame jFrame = new FinalJFrame();
        //to display the jFrame as soon as the code is run
        jFrame.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton signInButton;
    private javax.swing.JButton signOutButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
