package Login;

import java.util.ArrayList;
import java.util.Vector;

public class Member {
	public static Vector<Member> MemeverVec = new Vector<Member>(30);

	private String scustomernum; // ����ȣ
	private String sid; // ���̵�
	private String spw; // ��й�ȣ

	private String sName; // �̸�
	private String sBirth; // �������
	private String sPhone; // ����ȣ

	public Member() {

	}

	public Member(String sid, String spw) {
		this.sid = sid;
		this.spw = spw;
	}

	public Member(String scustomernum, String sid, String spw, String sName, String sBirth, String sPhone) {
		this.scustomernum = scustomernum;
		this.sid = sid;
		this.spw = spw;
		this.sName = sName;
		this.sBirth = sBirth;
		this.sPhone = sPhone;
	}

	public String getCustomernum() {
		return this.scustomernum;
	}

	public String getName() {
		return this.sName;
	}

	public String getBirth() {
		return this.sBirth;
	}

	public String getPhone() {
		return this.sPhone;
	}

	public String getSid() {
		return this.sid;
	}

	public String getSpw() {
		return this.spw;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setSpw(String spw) {
		this.spw = spw;
	}

	public void setMemberInfo(String sid, String spw, String sName, String sBirth, String sPhone) {
		this.sid = sid;
		this.spw = spw;
		this.sName = sName;
		this.sBirth = sBirth;
		this.sPhone = sPhone;
	}

	public ArrayList<String> RetrunList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("����ȣ");
		arrayList.add("�̸�");
		arrayList.add("���̵�");
		arrayList.add("��й�ȣ");
		arrayList.add("��ȭ��ȣ");
		return arrayList;
	}
}
