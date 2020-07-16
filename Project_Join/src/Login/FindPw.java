package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FindPw extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldBirth;

	public FindPw() {
		setTitle("PW \uCC3E\uAE30");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �ش繮���� �ּ�ó���ϸ� �ش��ϴ� �˾��� ���ŵ�
		setBounds(100, 100, 489, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblI = new JLabel("ID");

		lblI.setHorizontalAlignment(SwingConstants.RIGHT);
		lblI.setBounds(26, 20, 57, 15);
		contentPane.add(lblI);

		JLabel label = new JLabel("�̸�");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(26, 45, 57, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("�������");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(26, 70, 57, 15);
		contentPane.add(label_1);

		textFieldId = new JTextField();
		textFieldId.setBounds(95, 17, 230, 21);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(95, 42, 230, 21);
		contentPane.add(textFieldName);

		textFieldBirth = new JTextField();
		textFieldBirth.setColumns(10);
		textFieldBirth.setBounds(95, 67, 230, 21);
		contentPane.add(textFieldBirth);

		JLabel lblNewLabel = new JLabel(""); // �ȳ� ���� ����
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 113, 435, 15);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("��й�ȣ ã��");
		btnNewButton.setBounds(337, 16, 124, 72);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spw = "";
				if (textFieldId.getText().equals("") || textFieldName.getText().equals("")
						|| textFieldBirth.getText().equals("")) // ���̸�
				{
					LoginMain.ShowMessage("�̸��� ��������� �Է����ּ���.");
					return;
				} else
					spw = FindPwValue(textFieldId.getText(), textFieldName.getText(), textFieldBirth.getText());
				if (spw.equals("")) {
					LoginMain.ShowMessage("�ش��ϴ� ���̵� �������� �ʽ��ϴ�.");
				} else {
					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append(spw);
					lblNewLabel.setText("������ ��й�ȣ��" + PwOutput(strbuffer) + "�Դϴ�.");
				}
			}
		});

		contentPane.add(btnNewButton);

	}

	private String FindPwValue(String sId, String sname, String sbirth) {
		String spw = "";
		for (int i = 0; i < Member.MemeverVec.size(); i++) {
			if (Member.MemeverVec.get(i).getName().equals(null))
				continue;
			if (Member.MemeverVec.get(i).getBirth().equals(null))
				continue;
			if (Member.MemeverVec.get(i).getSid().equals(null))
				continue;
			if (Member.MemeverVec.get(i).getSid().equals(sId) && Member.MemeverVec.get(i).getName().equals(sname)
					&& Member.MemeverVec.get(i).getBirth().equals(sbirth)) {
				spw = Member.MemeverVec.get(i).getSpw();
			}
		}
		return spw;
	}

	private StringBuffer PwOutput(StringBuffer sPw) // ID �� ���ڸ� ������ �������ֱ� �������� *�� ����
	{
		String str = "";
		for (int i = 0; i < (sPw.length() - 3); i++) {
			str += "*";
		}
		if (sPw.length() >= 3)
			return sPw.replace(3, sPw.length(), str);
		return sPw;
	}
}
