package BookManagement;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import FileIO.FileIO;
import Handling.Handling;
import Login.LoginMain;
import Login.LoginMain.Status;
import Login.Member;

public class BookMain extends JFrame {

	private static JPanel contentPane;
	// ����Ʈ Ŭ���� / ���� / ���� / ���� �������̽�
	private static Handling handling = null;

	private static JLabel lblNewLabel_Title = new JLabel("");
	private static int iselectSearchIndex = 0;

	private JTextField textField_Search;

	private JButton btnLogIn = new JButton("�α���"); // ������ ��� ��ư ���� // �α��� üũ

	public ArrayList<String> array_combox = new ArrayList<String>();

	private JTable table = new JTable(model);
	private JPanel panel_2 = new JPanel();
	private JLabel lbListSize = null; // �� ����Ʈ ���� ����

	// ���� ����Ʈ ���
	private static JLabel[] Label = new JLabel[6];
	private static JTextField[] textField = new JTextField[6];

	private static Vector<String> comlumn = new Vector<String>(); // ��
	public static DefaultTableModel model = new DefaultTableModel(comlumn, 0) {
		public boolean isCellEditable(int row, int column) {// ���̺���������X
			return false;
			// TODO Auto-generated method stub
		}
	};

	public BookMain() { // ������

	}

	public void Book_Main() {// ������
		// ó�� 1ȸ ����
		FileIO.SetFileInputList("Book.txt");
		FileIO.SetFileInputList("Member.txt");
		// �ʱ⿡ �����ڿ� ���ÿ� ���� �����

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// ���� ����Ʈ Ʋ
		CreateLeftList();
		// ------------------- ���̺� ����Ʈ -------------------
		SetChangeable();
		// SetChangeable();
		// ------------------ �߾� ��� -------------------
		// ���� ���õǾ� �ִ� ����Ʈ ���� �̸� ����
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));
		panel.setBounds(12, 10, 855, 36);
		contentPane.add(panel);

		setTitle(lblNewLabel_Title);
		lblNewLabel_Title.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Title.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel_Title);
		// -------------- ������ ��ư ����Ʈ -------------- //
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2))); // �׵θ� ���������� ������ 2��ŭ

		panel_3.setBounds(879, 54, 145, 575);
		contentPane.add(panel_3);

		ArrayList<JButton> btnNewButton_Right = new ArrayList<JButton>();
		btnNewButton_Right.add(new JButton("Book"));
		btnNewButton_Right.add(new JButton("Customer"));
		btnNewButton_Right.add(new JButton("Employee"));
		btnNewButton_Right.add(new JButton("Publisher"));
		btnNewButton_Right.add(new JButton("Delivery"));
		btnNewButton_Right.add(new JButton("Order"));
		btnNewButton_Right.add(new JButton("Supply"));
		btnNewButton_Right.add(new JButton("Department"));
		btnNewButton_Right.add(new JButton("Position"));
		btnNewButton_Right.add(new JButton("Book_Class"));

		ButtonHandler handler = new ButtonHandler(); // ��ư �̺�Ʈ ó�� �� �۾��� �� �ֵ���

		for (int i = 0; i < btnNewButton_Right.size(); i++) { //
			btnNewButton_Right.get(i).setPreferredSize(new Dimension(125, 51));
			btnNewButton_Right.get(i).addActionListener(handler);

			panel_3.add(btnNewButton_Right.get(i));
		}

		// -------------- ���� �ϴ� ��ư -------------- //
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 504, 140, 125);
		panel_5.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

		contentPane.add(panel_5);
		JButton btnNewButton_Clear = new JButton("Clear");
		JButton btnNewButton_Insert = new JButton("Insert");
		JButton btnNewButton_Update = new JButton("Update");
		JButton btnNewButton_Delete = new JButton("Delete");

		btnNewButton_Clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Clear(); // ������ �ʱ�ȭ
				for (int i = 0; i < textField.length; i++)
					textField[i].setText("");
			}
		});
		btnNewButton_Insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Insert();
			}
		});
		btnNewButton_Update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Update();
			}
		});
		btnNewButton_Delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Delete();
			}
		});

		btnNewButton_Clear.setPreferredSize(new Dimension(120, 23));
		btnNewButton_Insert.setPreferredSize(new Dimension(120, 23));
		btnNewButton_Update.setPreferredSize(new Dimension(120, 23));
		btnNewButton_Delete.setPreferredSize(new Dimension(120, 23));

		panel_5.add(btnNewButton_Clear);
		panel_5.add(btnNewButton_Insert);
		panel_5.add(btnNewButton_Update);
		panel_5.add(btnNewButton_Delete);

		JPanel panel_1 = new JPanel(); // ������ ��� ��ư ����
		panel_1.setBounds(879, 10, 145, 36);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

		contentPane.add(panel_1);

		btnLogIn.setPreferredSize(new Dimension(125, 25));
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SetLoginLabel();
			}
		});
		panel_1.add(btnLogIn);
	}

	// ������ ��� ��ư ó�� �� ����

	public void SetLoginLabel() {// �α��� ��ư ������ �����ؾ��ϴ� ��Ȳ Setting
		// TODO Auto-generated method stub
		if (!LoginMain.bLogInStatus) {
			btnLogIn.setText("�α���");
			LoginMain.login.setVisible(true);
		} else {
			btnLogIn.setText("�α׾ƿ�");
			if (LoginMain.NowStatus == Status.CUSTOMER) {
				LoginMain.NowStatus = Status.BOOK;
				SetChangeable();
			}
			LoginMain.bLogInStatus = false;
			Login();// �α��� ��Ȳ�̸� �α׾ƿ� ��, �ٽ��ѹ� ȭ�鰻��
		}
	}

	public void Login() { // �α��� �����̸� �α׾ƿ��̶�� ���� ����
		// TODO Auto-generated method stub
		if (!LoginMain.bLogInStatus) {
			btnLogIn.setText("�α���");
		} else {
			btnLogIn.setText("�α׾ƿ�");
		}
	}

	private JComboBox<String> SetComboBoxList() { // �޺� �ڽ� ����
		// TODO Auto-generated method stub
		JComboBox<String> comboBox = new JComboBox<String>(new Vector<String>(array_combox));
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub;
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				iselectSearchIndex = cb.getSelectedIndex();
				System.out.println(iselectSearchIndex);
			}
		});
		return comboBox;
	}

	public void SetChangeable() // �ʱ� ����Ʈ �� ����
	{
		ArrayList<String> arrayList = null;

		// model.setColumnCount(0);
		model.setNumRows(0);
		// if (model.getRowCount() != 0) {// �ʱ�ȭ�� ������ ���, ������ ���� ����ؼ� ���̱� ������ �ʱ�ȭ
		// for (int i = 0; i < model.getRowCount(); i++)
		// model.removeRow(i);
		// }

		setTitle(lblNewLabel_Title); // �ֻ�� �̸� �ٲ�� ��

		switch (LoginMain.NowStatus) {
		case BOOK:
			arrayList = BookInfoList();// ���� ����Ʈ
			array_combox = BookInfoList(); // �޺� �ڽ� ����Ʈ
			lbListSize = new JLabel();

			break;
		case CUSTOMER:
			arrayList = MemeberInfoList();// ���� ����Ʈ80
			array_combox = MemeberInfoList(); // �޺� �ڽ� ����Ʈ

			lbListSize = new JLabel(" >> ����Ʈ �� ���� : " + Member.MemeverVec.size());
			break;
		case EMPLOYEE: // ����
			break;
		default:
			break;
		}
		Handling();
		handling.Clear(); // ���� ������ ����Ʈ�� ���� �ʱ�ȭ

		SetLeftList(arrayList); // ���� ���
		comlumn.clear();
		comlumn.addElement(arrayList.get(0));
		comlumn.addElement(arrayList.get(1));
		comlumn.addElement(arrayList.get(2));
		comlumn.addElement(arrayList.get(3));
		comlumn.addElement(arrayList.get(4));
		comlumn.addElement(arrayList.get(5));

		model.setColumnIdentifiers(comlumn); // ������ ��� �� ���� �޼ҵ�
		SetComboBox(); // �޺��ڽ� ����Ʈs

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int iselectRow = 0; // �������� ����Ʈ �� ���õ� ����Ʈ�� ��
				iselectRow = table.getSelectedRow();
				SetLeftListInfo(iselectRow); // ������ ���̴� ����Ʈ�� ���õ� ���� �� �������ֱ�
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		table.setBounds(150, 100, 200, 200);
		table.setPreferredScrollableViewportSize(new Dimension(700, 350)); // ���̺�
		table.repaint();// ���� �ʺ� ����, ���� ���� // �̹��� �ٽ� �׷��ֱ�

		JScrollPane listJs = new JScrollPane(table); // ��ũ��
		
		switch (LoginMain.NowStatus) {
		case BOOK:
			for (int i = 0; i < Book.BookVec.size(); i++)
				addBook(Book.BookVec.elementAt(i));
			break;
		case CUSTOMER:
			for (int i = 0; i < Member.MemeverVec.size(); i++)
				addMember(Member.MemeverVec.elementAt(i));
			lbListSize = new JLabel(" >> ����Ʈ �� ���� : " + Member.MemeverVec.size());
			break;
		case EMPLOYEE: // ����
			break;
		default:
			break;
		}
		panel_2.setLayout(new BorderLayout());
		panel_2.setBounds(164, 100, 703, 529);
		panel_2.add(listJs, BorderLayout.CENTER);
		panel_2.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));
		panel_2.add(lbListSize, BorderLayout.AFTER_LAST_LINE);

		contentPane.add(panel_2);
	}

	private void RefreshList() { // ����Ʈ�� �����ǰų� ������ �� ���� ��ġ�� ����
		switch (LoginMain.NowStatus) {
		case BOOK:
			addBook(Book.BookVec.elementAt(Book.BookVec.size()));
			lbListSize = new JLabel(" >> ����Ʈ �� ���� : " + Book.BookVec.size());
			break;
		case CUSTOMER:
			addMember(Member.MemeverVec.elementAt(Member.MemeverVec.size()));
			lbListSize = new JLabel(" >> ����Ʈ �� ���� : " + Member.MemeverVec.size());
			break;
		case EMPLOYEE: // ����
			break;
		default:
			break;
		}
	}

	public String gettextField_Search() { // �˻��� �ޱ�
		return textField_Search.getText();
	}

	public ArrayList<String> BookInfoList() // å ����Ʈ�� ���� ���
	{
		ArrayList<String> arrayList = null;
		arrayList = new ArrayList<String>();
		arrayList.clear();
		arrayList.add("���� ��ȣ");
		arrayList.add("������");
		arrayList.add("����");
		arrayList.add("���۳⵵");
		arrayList.add("����");
		arrayList.add("�뿩��Ȳ");
		return arrayList;
	}

	public static void addBook(Book book) { // Book ���� ��������
		Object obj[] = new Object[6];
		obj[0] = book.getSbooknum();
		obj[1] = book.getSbookName();
		obj[2] = book.getSbookWriter();
		obj[3] = book.getSbookBorn();
		obj[4] = book.getSbookCost();
		obj[5] = book.getSbookCondition();
		model.addRow(obj);
	}

	public ArrayList<String> MemeberInfoList() // å ����Ʈ�� ���� ���
	{
		ArrayList<String> arrayList = null;
		arrayList = new ArrayList<String>();
		arrayList.clear();
		arrayList.add("����ȣ");
		arrayList.add("���̵�");
		arrayList.add("��й�ȣ");
		arrayList.add("�̸�");
		arrayList.add("�������");
		arrayList.add("����ó");
		return arrayList;
	}

	public static void addMember(Member member) { // Member ���� ��������
		Object obj[] = new Object[6];
		obj[0] = member.getCustomernum();
		obj[1] = member.getSid();
		obj[2] = member.getSpw();
		obj[3] = member.getName();
		obj[4] = member.getBirth();
		obj[5] = member.getPhone();
		model.addRow(obj);
	}

	private void SetComboBox() { // �޺� �ڽ� �� ���߾� ��ܿ� ��ġ���ִ� �κ�
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1)));
		panel_4.setBounds(164, 54, 703, 36);
		contentPane.add(panel_4);

		Book book = new Book();

		JComboBox<String> comboBox = SetComboBoxList();
		// �� �����̸� arrayLsit�� ���ԵǾ�
		// �ִ� ���ڿ� ����ŭ �������

		comboBox.setEditable(false); // ���� ������ �� ���� ����� �ִ� �޼ҵ�
		comboBox.addActionListener(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		panel_4.add(comboBox);

		textField_Search = new JTextField();
		panel_4.add(textField_Search);
		textField_Search.setColumns(20); // �� ������ ����

		JButton btnNewButton = new JButton("Searchs");
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("�˻�����Ʈ ����");
		panel_4.add(btnNewButton_1);
	}

	private void setTitle(JLabel lblNewLabel) { // ���� ��ܿ� �̸� ����
		// TODO Auto-generated method stub
		String str = "";
		switch (LoginMain.NowStatus) {
		case BOOK:
			str = "å ���";
			break;
		case CUSTOMER:
			str = "ȸ�� ����";
			break;
		default:
			str = "�غ���";
			break;
		}
		str += "�Դϴ�.";
		lblNewLabel.setText(str);
	}

	private void SetLeftList(ArrayList<String> arraylist) { // ������ �ִ� ������ ����
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 54, 140, 440);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));
		contentPane.add(panel_1);

		for (int i = 0; i < Label.length; i++) { // panel�� �߰����ְ�,
			// setColumns : ���� ���� ������, TextField���̾ƿ��� ��ȿ
			Label[i].setText(arraylist.get(i));
			panel_1.add(Label[i]);
			panel_1.add(textField[i]);
			textField[i].setColumns(10);
		}
		textField[0].setEnabled(false); // ���� �Ұ���
	}

	private void CreateLeftList() { // ���� ����Ʈ ���ϴ� ������ŭ ������ֱ�
		for (int i = 0; i < Label.length; i++) {
			Label[i] = new JLabel();
			textField[i] = new JTextField();
		}
	}

	private void Handling() { // �����ϴ� ��ư ����Ʈ
		handling = new Handling() {

			@Override
			public void Clear() {
				// TODO Auto-generated method stub
				for (int i = 0; i < textField.length; i++) {
					textField[i].setText("");
				}
			}

			@Override
			public void Insert() {
				// TODO Auto-generated method stub
				for (int i = 0; i < textField.length; i++) {
					if (textField[i].getText().equals("")) {
						LoginMain.ShowMessage("���� �����մϴ�. ��� ���� ä���ּ���.");
						return;
					}
				}
				if (!InsertUpadateChk(textField[0].getText())) {
					LoginMain.ShowMessage("���� ������ ���� �����մϴ�. ������ ������ ���Ͻø� Update��, ���� �߰��� ���Ͻø� Clear ��, Insert ���ּ���.");
					return;
				}
				// ���⼭ üũ�ϱ� ������Ʈ���� �ƴϸ� �߰� ����
				switch (LoginMain.NowStatus) {
				case BOOK:
					Book book = new Book(LoginMain.SetCodeNumring(), textField[1].getText(), textField[2].getText(),
							textField[3].getText(), textField[4].getText(), textField[5].getText());
					Book.BookVec.addElement(book);
					FileIO.SaveToFile("Book.txt");
					break;
				case CUSTOMER:
					Member member = new Member(LoginMain.SetCodeNumring(), textField[1].getText(),
							textField[2].getText(), textField[3].getText(), textField[4].getText(),
							textField[5].getText());
					Member.MemeverVec.addElement(member);
					FileIO.SaveToFile("Member.txt");
					break;
				default:
					break;
				}
				SetChangeable();
			}

			@Override
			public void Update() {
				// TODO Auto-generated method stub
				// �ڵ��ȣ�� ���� ���, �ڵ� ��ȣ �ܿ� ��� �� ���� ����
				for (int i = 0; i < Member.MemeverVec.size(); i++) {
					if (Member.MemeverVec.get(i).getCustomernum().equals(textField[0].getText())) {
						Member.MemeverVec.get(i).setMemberInfo(textField[1].getText(), textField[2].getText(),
								textField[3].getText(), textField[4].getText(), textField[5].getText());
					}
				}
			}

			@Override
			public void Delete() {
				boolean blocatedData = false; // ���������� ��Ȯ������ ���� üũ | ��� ������ ���� �°� �Է� �Ǿ��ִ��� Ȯ��
				int delectindex = 0;
				// TODO Auto-generated method stub
				// �ٳ�ͼ� ���� �����ϴ� �޼ҵ� �����
				switch (LoginMain.NowStatus) {
				case BOOK:
					for (int i = 0; i < Book.BookVec.size(); i++) {
						if (Book.BookVec.get(i).getSbooknum().equals(textField[0].getText())
								&& Book.BookVec.get(i).getSbookName().equals(textField[1].getText())
								&& Book.BookVec.get(i).getSbookWriter().equals(textField[2].getText())
								&& Book.BookVec.get(i).getSbookBorn().equals(textField[3].getText())
								&& Book.BookVec.get(i).getSbookCost().equals(textField[4].getText())
								&& Book.BookVec.get(i).getSbookCondition().equals(textField[5].getText())) {
							blocatedData = true;
							delectindex = i;
							break;
						}
					}
					if (!blocatedData) {
						LoginMain.ShowMessage("������ ������ ��Ȯ���� �ʽ��ϴ�. �ٽ� Ȯ�� �� �������ּ���.");
					} else {
						Book.BookVec.remove(delectindex); // �ش� ����Ʈ ���� �� ���ű���
						SetChangeable();
						LoginMain.ShowMessage("�����Ǿ����ϴ�.");
						// ������� �ϱ�
						FileIO.SaveToFile("Book.txt");
					}
					break;
				case CUSTOMER:
					for (int i = 0; i < Member.MemeverVec.size(); i++) {
						if (Member.MemeverVec.get(i).getCustomernum().equals(textField[0].getText())
								&& Member.MemeverVec.get(i).getSid().equals(textField[1].getText())
								&& Member.MemeverVec.get(i).getSpw().equals(textField[2].getText())
								&& Member.MemeverVec.get(i).getName().equals(textField[3].getText())
								&& Member.MemeverVec.get(i).getBirth().equals(textField[4].getText())
								&& Member.MemeverVec.get(i).getPhone().equals(textField[5].getText())) {
							blocatedData = true;
							delectindex = i;
							break;
						}
					}
					if (!blocatedData) {
						LoginMain.ShowMessage("������ ������ ��Ȯ���� �ʽ��ϴ�. �ٽ� Ȯ�� �� �������ּ���.");
					} else {
						Member.MemeverVec.remove(delectindex); // �ش� ����Ʈ ���� �� ���ű���
						SetChangeable();
						LoginMain.ShowMessage("�����Ǿ����ϴ�.");
						FileIO.SaveToFile("Member.txt");
						// ������� �ϱ�
					}
					break;
				default:
					break;
				}

			}
		};

	}

	private void SetLeftListInfo(int iselectRow) // ������ ���̴� ����Ʈ�� ���õ� ���� �� �������ֱ�
	{
		switch (LoginMain.NowStatus) {
		case BOOK:
			textField[0].setText(Book.BookVec.get(iselectRow).getSbooknum());
			textField[1].setText(Book.BookVec.get(iselectRow).getSbookName());
			textField[2].setText(Book.BookVec.get(iselectRow).getSbookWriter());
			textField[3].setText(Book.BookVec.get(iselectRow).getSbookBorn());
			textField[4].setText(Book.BookVec.get(iselectRow).getSbookCost());
			textField[5].setText(Book.BookVec.get(iselectRow).getSbookCondition());
			break;
		case CUSTOMER:
			textField[0].setText(Member.MemeverVec.get(iselectRow).getCustomernum());
			textField[1].setText(Member.MemeverVec.get(iselectRow).getSid());
			textField[2].setText(Member.MemeverVec.get(iselectRow).getSpw());
			textField[3].setText(Member.MemeverVec.get(iselectRow).getName());
			textField[4].setText(Member.MemeverVec.get(iselectRow).getBirth());
			textField[5].setText(Member.MemeverVec.get(iselectRow).getPhone());
			break;
		default:
			break;
		}
	}

	private boolean InsertUpadateChk(String str) { // ���� �����Ϸ��� �ϴ� ������ ������ �ִ� ������ �����ϴ����� ���� üũ
		boolean bChk = true;
		switch (LoginMain.NowStatus) {
		case BOOK:
			for (int i = 0; i < Book.BookVec.size(); i++) {
				if (str.equals(Book.BookVec.get(i).getSbooknum())) {
					bChk = false;
					break;
				}
			}
			break;
		case CUSTOMER:
			for (int i = 0; i < Member.MemeverVec.size(); i++) {
				str.equals(Member.MemeverVec.get(i).getCustomernum());
				bChk = false;
				break;
			}
			break;
		default:
			break;
		}
		return bChk;

	}
}
