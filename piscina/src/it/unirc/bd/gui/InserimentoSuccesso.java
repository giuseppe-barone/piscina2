package it.unirc.bd.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InserimentoSuccesso extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserimentoSuccesso dialog = new InserimentoSuccesso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserimentoSuccesso() {
		setTitle("OK!");
		setModal(true);
		setType(Type.UTILITY);
		setResizable(false);
		setBounds(100, 100, 360, 139);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(121, 68, 97, 25);
		contentPanel.add(btnOk);
		
		JLabel lblInserimentoAvvenutoCon = new JLabel("INSERIMENTO AVVENUTO CON SUCCESSO!");
		lblInserimentoAvvenutoCon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInserimentoAvvenutoCon.setBounds(12, 0, 384, 55);
		contentPanel.add(lblInserimentoAvvenutoCon);
	}
}
