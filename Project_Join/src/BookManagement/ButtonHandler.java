package BookManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import Login.LoginMain;
import Login.LoginMain.Status;

public class ButtonHandler implements ActionListener {
	// ��ư ������ �� �׼�
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String bt = e.getActionCommand(); // ���� ����ȯ
		BookMain bookmain = new BookMain();
		bookmain.model.setNumRows(0);
		// ������ �����Ǿ� �ִ� ���� 0���� �ʱ�ȭ�� ����Ʈ ����
		if (bt.equals("Book")) {
			LoginMain.NowStatus = Status.BOOK;
		} else if (bt.equals("Customer")) {
			if (!LoginMain.bLogInStatus) {
				LoginMain.NowStatus = Status.BOOK;
				LoginMain.ShowMessage("�α��� �� �̿��� �����մϴ�.");
			} else {
				LoginMain.NowStatus = Status.CUSTOMER;
			}
		} else {
			LoginMain.ShowMessage("�غ����Դϴ�.");
			LoginMain.NowStatus = Status.EMPLOYEE;
			return;
		}
		bookmain.SetChangeable();
		// else if(bt.equals("Employee"))
		// else if(bt.equals("Publisher"))
		// else if(bt.equals("Delivery"))
		// else if(bt.equals("Supply"))
		// else if(bt.equals("Department"))
		// else if(bt.equals("Position"))
		// else if(bt.equals("Book_Class"))
	}

	public String run() {
		String str = "";
		try {
			while (true) {
				int hour1, hour2 = 0;
				int min1, min2 = 0;
				int sec1, sec2 = 0;

				Calendar rightNow = Calendar.getInstance(); // ����ð��� �Է¹޴´� �� �� Calendar �� ����� �� �ִ�

				// �ð��� �ޱ� ���� �̷� ������ ������� ����� �޴´�.

				int second_d = rightNow.get(Calendar.SECOND);
				int minute_d = rightNow.get(Calendar.MINUTE);
				int hour_d = rightNow.get(Calendar.HOUR);

				if (hour_d > 9) {
					hour1 = hour_d / 10;
					hour2 = hour_d % 10;
				} else {
					hour1 = 0;
					hour2 = hour_d;
				}
				if (minute_d > 9) {
					min1 = minute_d / 10;
					min2 = minute_d % 10;
				} else {
					min1 = 0;
					min2 = minute_d;
				}
				if (second_d > 9) {
					sec1 = second_d / 10;
					sec2 = second_d % 10;
				} else {
					sec1 = 0;
					sec2 = second_d;
				}
				str = hour1 + "" + hour2 + "" + min2 + "" + min1 + "" + sec1 + "" + sec2 + "";
				System.out.println(str);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		return str;
	}

}
