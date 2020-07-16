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

public class FindId extends JFrame {

	private JPanel contentPane;
	private JTextField textname;
	private JTextField textbirth;

	public FindId() {
		setTitle("ID ã��");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("�̸�");
		lblId.setBounds(30, 20, 57, 15);
		contentPane.add(lblId);

		JLabel lblPW = new JLabel("�������");
		lblPW.setBounds(30, 43, 57, 15);
		contentPane.add(lblPW);

		textname = new JTextField();
		textname.setBounds(99, 17, 202, 21);
		contentPane.add(textname);
		textname.setColumns(10);

		textbirth = new JTextField();
		textbirth.setColumns(10);
		textbirth.setBounds(99, 40, 202, 21);
		contentPane.add(textbirth);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(31, 83, 420, 15);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("���̵� ã��");
		btnNewButton.setBounds(313, 17, 136, 44);
		btnNewButton.setActionCommand("FindID"); // �ش� ��ư�� �ο��� �׼� ��ɿ� ���� ��Ī�� �ο�
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// �̰�����ư�� ������ �� ����Ǵ� �� �ۼ��ϱ�
				String sid = "";
				if (textname.getText().equals("") || textbirth.getText().equals("")) // ���̸�
				{
					LoginMain.ShowMessage("�̸��� ��������� �Է����ּ���.");
					return;
				} else
					sid = FindIdValue(textname.getText(), textbirth.getText());
				if (sid.equals("")) {
					LoginMain.ShowMessage("�ش��ϴ� ���̵� �������� �ʽ��ϴ�.");
				} else {
					StringBuffer strbuffer = new StringBuffer();
					strbuffer.append(sid);
					lblNewLabel_1.setText("������ ���̵��" + IDOutput(strbuffer) + "�Դϴ�.");
				}
			}
		});
		contentPane.add(btnNewButton);
	}

	private String FindIdValue(String sname, String sbirth) {
		String sid = "";
		for (int i = 0; i < Member.MemeverVec.size(); i++) {
			if(Member.MemeverVec.get(i).getName().equals(null))
				continue;
			if(Member.MemeverVec.get(i).getBirth().equals(null))
				continue;
			if (Member.MemeverVec.get(i).getName().equals(sname) &&Member.MemeverVec.get(i).getBirth().equals(sbirth)) {
				sid = Member.MemeverVec.get(i).getSid();
			}
		}
		return sid;
	}

	private StringBuffer IDOutput(StringBuffer sId) // ID �� ���ڸ� ������ �������ֱ� �������� *�� ����
	{
		String str = "";
		for (int i = 0; i < (sId.length() - 3); i++) {
			str += "*";
		}
		if (sId.length() >= 3)
			return sId.replace(3, sId.length(), str);
		return sId;
	}
}
