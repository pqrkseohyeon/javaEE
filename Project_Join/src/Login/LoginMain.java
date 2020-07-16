package Login;

import java.util.UUID;
import java.util.Vector;

import javax.swing.JOptionPane;

import BookManagement.Book;
import BookManagement.BookMain;

public class LoginMain {

	public enum Status { // Tab ��Ȳ �����ϱ� ���� Enum ����
		BOOK, CUSTOMER, EMPLOYEE, PUBLISHER, DELIVERY, ORDER, SUPPLY, DEPARTMENT, POSITION, BOOKCLASS
	};

	public static Status NowStatus = Status.BOOK; // �� tab ��Ȳ ���� //�⺻�� book

	public static FindId findId = new FindId(); // ���̵� ã��
	public static FindPw findpw = new FindPw(); // ��� ��ȣ ã��
	public static Join join = new Join(); // ȸ�� ����
	public static Login login = new Login(); // �α��� ȭ�� (���� �⺻ main)

	public static BookMain bookmain = new BookMain(); // å ���� �Ŵ���
	public static boolean bLogInStatus = false; // �� ��Ȳ�̷α��� �� �������� üũ���ִ� ����

	public static void main(String[] args) {
		bookmain.Book_Main();
		bookmain.setVisible(true);
	}

	public static void ShowMessage(String str) {
		JOptionPane.showMessageDialog(null, str);
	}

	public static String SetCodeNumring() { // �ڵ� �ѹ� �������� �޾��ִ� �޼ҵ� || å�� ���, �տ� B�� / ����� ���, �տ� M
		String str = "";
		if (LoginMain.NowStatus == Status.BOOK)
			str = "B";
		else if (LoginMain.NowStatus == Status.CUSTOMER)
			str = "M";
		str += UUID.randomUUID().toString().replaceFirst("-", "").substring(0, 6);
		return str;
		// �� ���̰� 32�ڸ��� ������ ������ ���� ����
		// ���� ����Ʈ�� ���� ������ �߰��� 6�ڸ��� �������� �޾��� �ۼ���
	}

	public static Vector<?> ReturnVec() {
		switch (LoginMain.NowStatus) {
		case BOOK:
			return Book.BookVec;
		case CUSTOMER:
			return Member.MemeverVec;
		default:
			return Book.BookVec;
		}
	}
}
