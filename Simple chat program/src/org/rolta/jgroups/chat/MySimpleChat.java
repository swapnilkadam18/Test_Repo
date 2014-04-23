package org.rolta.jgroups.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

/**
 * Sample implementation of the chat window.
 * 
 * @author subin.babu
 * 
 */
public class MySimpleChat extends JFrame implements Receiver {

	private JPanel contentPane;
	private JTextField txtInputMessages;
	private JTextPane txtMessages;
	private JScrollPane scrollPane;

	private DefaultListModel listModel;
	private JChannel channel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		MySimpleChat frame = new MySimpleChat();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MySimpleChat() {
		/**
		 * set title to the window
		 */
		setTitle("Simple Chat Implementation");
		/**
		 * set default props to the window
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		/**
		 * create jpanel to the window
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * initialization and events for the send button
		 */
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Document doc = null;
				try {
					doc = txtMessages.getDocument();
					doc.insertString(doc.getLength(),
							"\n" + txtInputMessages.getText(), null);
					Message msg = new Message(null, null, txtInputMessages
							.getText().toString());
					channel.send(msg);
				} catch (BadLocationException exc) {
					exc.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				txtMessages.setDocument(doc);
				txtInputMessages.setText("");
			}
		});
		btnSend.setBounds(21, 228, 154, 23);
		contentPane.add(btnSend);

		/**
		 * initialization and events for the find all button
		 */
		JButton btnFindAll = new JButton("Find all");
		btnFindAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listModel.addElement("One");
				listModel.addElement("Two");
				listModel.addElement("Three");
			}
		});
		btnFindAll.setBounds(259, 228, 154, 23);
		contentPane.add(btnFindAll);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 257, 175);
		contentPane.add(scrollPane);

		txtMessages = new JTextPane();
		scrollPane.setViewportView(txtMessages);
		txtMessages.setEditable(false);

		txtInputMessages = new JTextField();
		txtInputMessages.setBounds(10, 197, 414, 20);
		contentPane.add(txtInputMessages);
		txtInputMessages.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 11, 126, 175);
		contentPane.add(scrollPane_1);

		listModel = new DefaultListModel();
		JList listAvailableViews = new JList(listModel);
		scrollPane_1.setViewportView(listAvailableViews);

		String props = "udp.xml";
		try {
			startConnection(props, null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void startConnection(String props, String name) throws Exception {
		channel = new JChannel(props);
		if (name != null)
			channel.setName(name);
		channel.setReceiver(this);
		channel.connect("ChatCluster");
		channel.close();
	}

	@Override
	public void getState(OutputStream arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void receive(Message msg) {
		Document doc = null;
		try {
			doc = txtMessages.getDocument();
			doc.insertString(doc.getLength(), "\n" + msg.getObject(), null);
		} catch (BadLocationException exc) {
			exc.printStackTrace();
		}
		txtMessages.setDocument(doc);

	}

	@Override
	public void setState(InputStream arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void block() {
		// TODO Auto-generated method stub

	}

	@Override
	public void suspect(Address arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unblock() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);

	}
}
