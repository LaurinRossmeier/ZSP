package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.border.EmptyBorder;

import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDateTime;
import java.util.Date;

public class MainFrame extends JFrame {

	static int lineCount = 1;
	static JPanel panel = new JPanel();
	static String tempValue;

	private JPanel contentPane;
	private JTextField textField = new JTextField();;
	private JTextField textField_1 = new JTextField();;
	private JTextField textField_2 = new JTextField();;
	private JTextField textField_3 = new JTextField();;
	private JTextField textField_4 = new JTextField();;

	public Choice choice = new Choice();
	public Choice choice_1 = new Choice();
	public Choice choice_2 = new Choice();
	public Choice choice_3 = new Choice();
	public Choice choice_4 = new Choice();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][grow][][]"));

		addMedium(choice);
		panel.add(choice, "cell 0 0");

		textField.addFocusListener(new ZahlenFeldListener());
		panel.add(textField, "cell 1 0,growx");
		textField.setColumns(10);

		Button btnNewLine = new Button("Zeile hinzuf\u00FCgen");
		btnNewLine.setEnabled(false);
		btnNewLine.addActionListener(new newLineListener());

		addMedium(choice_1);
		panel.add(choice_1, "cell 0 1");

		textField_1.setColumns(10);
		panel.add(textField_1, "cell 1 1,growx");

		addMedium(choice_2);
		panel.add(choice_2, "cell 0 2");

		textField_2.setColumns(10);
		panel.add(textField_2, "cell 1 2,growx");

		addMedium(choice_3);
		panel.add(choice_3, "cell 0 3");

		textField_3.setColumns(10);
		panel.add(textField_3, "cell 1 3,growx");

		addMedium(choice_4);
		panel.add(choice_4, "cell 0 4");

		textField_4.setColumns(10);
		panel.add(textField_4, "cell 1 4,growx");

		panel.add(btnNewLine, "flowx,cell 1 7");

		JButton btnNewButton = new JButton("Absenden");
		btnNewButton.addActionListener(new submitListener());
		panel.add(btnNewButton, "cell 1 7");

		JLabel lblBitteZhlerstndeEintragen = new JLabel("Bitte Z\u00E4hlerst\u00E4nde eintragen:");
		contentPane.add(lblBitteZhlerstndeEintragen, BorderLayout.NORTH);

	}

	public void addMedium(Choice c) {
		c.add("");
		c.add("Wasser");
		c.add("Strom");
		c.add("Öl");
		c.add("Gas");
		c.add("Pellets");

	}

	public class newLineListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Choice[] choice = new Choice[10];
			for (int i = 0; i < choice.length; i++) {
				choice[i] = new Choice();
				choice[i].add("Wasser");
				choice[i].add("Strom");
				choice[i].add("Öl");
				choice[i].add("Gas");
				choice[i].add("Pellets");
			}

			JTextField[] textFields = new JTextField[10];

			panel.add(choice[lineCount], "cell 0 " + lineCount);

			panel.add(textFields[lineCount], "cell 1 " + lineCount);
			textFields[lineCount].setColumns(10);

		}

	}

	public class ZahlenFeldListener implements FocusListener {

		boolean hilfe = true;

		public void focusGained(FocusEvent f) {
			if (hilfe) {

				hilfe = false;

			}

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			hilfe = true;

		}

		public class sendListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

			}

		}

	}

	public class submitListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			LocalDateTime now = LocalDateTime.now();

			for (int i = 0; i < 6; i++) {
				String typ = "";
				String value = "";
				switch (i) {
				case 0:
					typ = choice.getSelectedItem();
					value = textField.getText();
					break;
				case 1:
					typ = choice_1.getSelectedItem();
					value = textField_1.getText();
					break;
				case 2:
					typ = choice_2.getSelectedItem();
					value = textField_2.getText();
					break;
				case 3:
					typ = choice_3.getSelectedItem();
					value = textField_3.getText();
					break;
				case 4:
					typ = choice_4.getSelectedItem();
					value = textField_4.getText();
					break;
				}
				String url = "http://192.168.0.111:821/consumption?typ=" + typ + "&date=" + now.toString() + "&value="
						+ value;
			}

		}

	}
	public class Zahlenfeld{
		
		String number = "";
		JLabel lbl = new JLabel("0000000000,00");
		JDialog meinJDialog = new JDialog();
		meinJDialog.setTitle("Wert eingeben");
        meinJDialog.setSize(200,200);
        meinJDialog.getContentPane().setLayout(new MigLayout("", "[][][]", "[][][][][]"));
        JButton b0 = new JButton("0");
        b0.setSize(10,10);
        b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton temp = (JButton) e.getSource();
				number = number+temp.getText();
				lbl.setText(number);
			}
		});
        JButton b1 = new JButton("1");
        b1.setSize(10,10);
        addListener(b1);
        JButton b2 = new JButton("2");
        b2.setSize(10,10);
        addListener(b2);
        JButton b3 = new JButton("3");
        b3.setSize(10,10);
        addListener(b3);
        JButton b4 = new JButton("4");
        b4.setSize(10,10);
        addListener(b4);
        JButton b5 = new JButton("5");
        b5.setSize(10,10);
        addListener(b5);
        JButton b6 = new JButton("6");
        b6.setSize(10,10);
        addListener(b6);
        JButton b7 = new JButton("7");
        b7.setSize(10,10);
        addListener(b7);
        JButton b8 = new JButton("8");
        b8.setSize(10,10);
        addListener(b8);
        JButton b9 = new JButton("9");
        b9.setSize(10,10);
        addListener(b9);
        JButton bc = new JButton(",");
        bc.setSize(10,10);
        addListener(bc);
        JButton send = new JButton("Übernehmen");
        meinJDialog.getContentPane().add(lbl, "cell 0 0 2 1");
        meinJDialog.getContentPane().add(b1, "cell 0 1");
        meinJDialog.getContentPane().add(b2, "cell 1 1");
        meinJDialog.getContentPane().add(b3, "cell 2 1");
        meinJDialog.getContentPane().add(b4, "cell 0 3");
        meinJDialog.getContentPane().add(b5, "cell 1 3");
        meinJDialog.getContentPane().add(b6, "cell 2 3");
        meinJDialog.getContentPane().add(b7, "cell 1 4");
        meinJDialog.getContentPane().add(b8, "cell 2 4");
        meinJDialog.getContentPane().add(b9, "cell 0 4");
        meinJDialog.getContentPane().add(b0, "cell 1 5");
        meinJDialog.getContentPane().add(bc, "cell 2 5");
        meinJDialog.getContentPane().add(send,BorderLayout.SOUTH);
        meinJDialog.setVisible(true);
        
        
        
	
        static void addListener(JButton b){
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton temp = (JButton) e.getSource();
					number = number+temp.getText();
					lbl.setText(number);
				}
			});
		}
	}

}
