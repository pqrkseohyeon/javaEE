package BookManagement;

import java.util.ArrayList;

public class Customer {
	private String scustomernum; // ����ȣ
	private String scustomerName; // �̸�
	private String scustomerId; // ���̵�
	private String scustomerPw; // ��й�ȣ
	private String scustomerPhone; // ��ȭ��ȣ
	private String scustomeremail;// �̸���

	public Customer() {

	}

	public Customer(String scustomernum, String scustomerName, String scustomerId, String scustomerPw,
			String scustomerPhone, String scustomeremail) {
		this.scustomernum = scustomernum;
		this.scustomerName = scustomerName;
		this.scustomerId = scustomerId;
		this.scustomerPw = scustomerPw;
		this.scustomerPhone = scustomerPhone;
		this.scustomeremail = scustomeremail;
	}

	String getScustomernum() {
		return this.scustomernum;
	}

	String getScustomerName() {
		return this.scustomerName;
	}

	String getScustomerId() {
		return this.scustomerId;
	}

	String getScustomerPw() {
		return this.scustomerPw;
	}

	String getScustomerPhone() {
		return this.scustomerPhone;
	}

	String getScustomeremail() {
		return this.scustomeremail;
	}

	public ArrayList<String> RetrunList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("����ȣ");
		arrayList.add("�̸�");
		arrayList.add("���̵�");
		arrayList.add("��й�ȣ");
		arrayList.add("��ȭ��ȣ");
		arrayList.add("�̸���");
		return arrayList;
	}
}
