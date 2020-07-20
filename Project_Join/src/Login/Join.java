package Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import FileIO.FileIO;
import Login.LoginMain.Status;

@SuppressWarnings("serial")
public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Id;
	private JPasswordField textField_PW;
	private JPasswordField textField_PwChk;
	private JTextField textField_Name;
	private JTextField textField_Birth;
	private JTextField textField_Phone;

	private JLabel Label_pwinfo;
	private JButton button;
	private boolean brepetiteChk = false; // ���̵� �ߺ� üũ

	private void Reset(JTextField textField_Id, JTextField textField_PW, JTextField textField_PwChk,
			JTextField textField_Name, JTextField textField_Birth, JTextField textField_Phone) {
		textField_Id.setText(null);
		textField_PW.setText(null);
		textField_PwChk.setText(null);
		textField_Name.setText(null);
		textField_Birth.setText(null);
		textField_Phone.setText(null);
	}

	public Join() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_Id = new JTextField();
		textField_Id.setBounds(110, 23, 155, 21);
		contentPane.add(textField_Id);
		textField_Id.setColumns(10);

		textField_PW = new JPasswordField();
		textField_PW.setColumns(10);
		textField_PW.setBounds(110, 54, 155, 21);
		contentPane.add(textField_PW);

		textField_PwChk = new JPasswordField();
		textField_PwChk.setColumns(10);
		textField_PwChk.setBounds(110, 85, 155, 21);
		contentPane.add(textField_PwChk);

		textField_Name = new JTextField();
		textField_Name.setColumns(10);
		textField_Name.setBounds(110, 119, 155, 21);
		contentPane.add(textField_Name);

		textField_Birth = new JTextField();
		textField_Birth.setColumns(10);
		textField_Birth.setBounds(110, 150, 155, 21);
		contentPane.add(textField_Birth);

		textField_Phone = new JTextField();
		textField_Phone.setColumns(10);
		textField_Phone.setBounds(110, 181, 155, 21);
		contentPane.add(textField_Phone);

		setForeground(Color.WHITE);
		setBackground(Color.PINK);
		setTitle("\uD68C\uC6D0 \uAC00\uC785");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 268);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(30, 26, 57, 15);
		contentPane.add(lblId);

		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPw.setBounds(30, 57, 57, 15);
		contentPane.add(lblPw);

		JLabel lblPwOk = new JLabel("PW OK");
		lblPwOk.setBackground(Color.GRAY);
		lblPwOk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPwOk.setBounds(30, 88, 57, 15);
		contentPane.add(lblPwOk);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(30, 122, 57, 15);
		contentPane.add(lblName);

		JLabel lblBirth = new JLabel("Birth");
		lblBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirth.setBounds(30, 153, 57, 15);
		contentPane.add(lblBirth);

		Label_pwinfo = new JLabel("�� ����, ���ڷ� 8�� �̻�");
		Label_pwinfo.setBounds(277, 58, 145, 15);
		contentPane.add(Label_pwinfo);

		button = new JButton("�ߺ� Ȯ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join_Chk joinchk = new Join_Chk();
				if (!textField_Id.getText().equals("")) {
					brepetiteChk = joinchk.RepetiteID(textField_Id.getText());
					if (!brepetiteChk)
						LoginMain.ShowMessage("�̹� �ִ� ���̵� �Դϴ�.\n����� �Ұ����մϴ�.");
					else
						LoginMain.ShowMessage("����� ������ ���̵� �Դϴ�.");
				} else {
					LoginMain.ShowMessage("���̵� �Է����ּ���.");
				}
				// ���̵� �޾Ƶ鿩 �ߺ� üũ�ϱ�
			}
		});
		JButton button_1 = new JButton("�Ϸ�");
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean bPasswordLengthChk = false; // ��й�ȣ ���� üũ
				boolean bPasswordMatchChk = false; // ��� ��ȣ ��ġ Ȯ��

				Join_Chk joinchk = new Join_Chk();

				bPasswordLengthChk = joinchk.PasswordLengthChk(textField_PW.getText()); // ��� ��ȣ ���� üũ
				bPasswordMatchChk = joinchk.PasswordMatchChk(textField_PW.getText(), textField_PwChk.getText());
				if (textField_Name.getText().equals("") || textField_Birth.getText().equals("")
						|| textField_Phone.getText().equals("")) {
					// ��ĭ�̶� ���� ������,
					LoginMain.ShowMessage("�� ���� ��� �Է����ּ���.");
					return;
				}
				if (!bPasswordLengthChk) {
					LoginMain.ShowMessage("��� ��ȣ�� ����, ���ڷ� 8�� �̻�� �Է����ּ���.");
					return;
				}
				if (!bPasswordMatchChk) {
					LoginMain.ShowMessage("��й�ȣ�� ��ġ���� �ʽ��ϴ�. ��й�ȣ�� �ٽ� Ȯ�����ּ���.");
					return;
				}
				if (!brepetiteChk) {
					LoginMain.ShowMessage("���̵� �ߺ� üũ���ּ���.");
					return;
				}
				LoginMain.ShowMessage("ȸ�� ���� �Ǿ����ϴ�. �α��� ���ּ���");
				Member.MemeverVec.addElement(new Member(LoginMain.SetCodeNumring(), textField_Id.getText(), textField_PW.getText(),
						textField_Name.getText(), textField_Birth.getText(), textField_Phone.getText()));
				FileIO.SaveToFile("Member.txt");
				Reset(textField_Id, textField_PW, textField_PwChk, textField_Name, textField_Birth, textField_Phone);

				LoginMain.join.setVisible(false);
				LoginMain.login.setVisible(true);
			}
		});

		button.setBounds(277, 23, 145, 21);
		contentPane.add(button);
		button_1.setBounds(277, 181, 145, 21);
		contentPane.add(button_1);

		JLabel label = new JLabel("�ڵ��� ��ȣ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(19, 184, 79, 15);
		contentPane.add(label);
	}


}