package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPw;

	private String sID = "";
	private String sPW = "";

	public Login() {
		setTitle("�ߵ� �������� ���Ű� ȯ���մϴ�.");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 164);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(40, 30, 47, 15);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setText("");
		// sID = txtId.getText();
		txtId.setBounds(99, 27, 167, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setBounds(40, 55, 47, 15);
		contentPane.add(lblPw);

		txtPw = new JPasswordField();
		txtPw.setText("");
		// sPW = txtId.getText();
		txtPw.setBounds(99, 52, 167, 21);
		contentPane.add(txtPw);
		txtPw.setColumns(10);

		JButton btnNewButton_Login = new JButton("�α���");
		btnNewButton_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �α��� ���� �� �۾�
				boolean bLoginChk = false;
				GetIdandPw(txtId, txtPw); // �Էµ� �� ���� �Ѱ� �ֱ�
				LogIn_Chk loginchk = new LogIn_Chk();
				bLoginChk = loginchk.LogInList_Chk(sID, sPW); // false�� �α��� ����

				if (!bLoginChk) { // �α��� ����s
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ȯ�� ��\n�ٽ� �α������ּ���.");
				} else { // �α��� ����
					LoginMain.login.setVisible(false); // ������ �α��� ȭ�� ���ֱ�
					LoginMain.bLogInStatus = true;// �α��� �����̸� ���º�ȯ �� �α��� ��ư ����
					LoginMain.bookmain.Login(); // ȭ�� ����
				}
				// �̰��� ���ο� ȭ�� ����
			}
		});
		btnNewButton_Login.setBounds(274, 27, 102, 46);
		contentPane.add(btnNewButton_Login);

		JButton btnNewButton_Sign = new JButton("ȸ�� ����");
		btnNewButton_Sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// // ȸ������ ���� �� �۾�
				// LoginMain.login.setVisible(false); // ������ �α��� ȭ�� ���ֱ�

				LoginMain.join.setVisible(true); // ȸ�� ���� ȭ�� ���ֱ�
			}
		});
		btnNewButton_Sign.setBounds(274, 80, 102, 30);
		contentPane.add(btnNewButton_Sign);

		JButton button_FindID = new JButton("ID ã��");
		button_FindID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// IDã�� ���� �� �۾�
				// LoginMain.login.setVisible(false); // �α��� ȭ�� off
				LoginMain.findId.setVisible(true);
			}
		});
		button_FindID.setBounds(40, 80, 109, 30);
		contentPane.add(button_FindID);

		JButton button_Findepw = new JButton("PWã��");
		button_Findepw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// PWã�� ���� �� �۾�
				// LoginMain.login.setVisible(false); // �α��� ȭ�� off
				LoginMain.findpw.setVisible(true);
			}
		});
		button_Findepw.setBounds(152, 80, 118, 30);
		contentPane.add(button_Findepw);

		JButton button = new JButton("�Խ�Ʈ ����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginMain.login.setVisible(false); // ������ �α��� ȭ�� ���ֱ�
			}
		});
		button.setBounds(388, 26, 102, 84);
		contentPane.add(button);

		Reset(txtId, txtPw);
	}

	private void GetIdandPw(JTextField txtId, JTextField txtPw) {
		// TODO Auto-generated method stub
		sID = txtId.getText();
		sPW = txtPw.getText();
	}

	private void Reset(JTextField txtId, JTextField txtPw) {
		txtId.setText(null);
		txtPw.setText(null);
	}
}