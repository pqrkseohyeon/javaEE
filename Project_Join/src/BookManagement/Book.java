package BookManagement;

import java.util.Vector;

public class Book {

	public static Vector<Book> BookVec = new Vector<Book>(30);
	private String sbooknum; // ���� ��ȣ
	private String sbookName; // ������
	private String sbookWriter; // ����
	private String sbookBorn; // ���۳⵵
	private String sbookCost; // ����
	private String sbookCondition;// �뿩 ��Ȳ

	public Book(String sbooknum, String sbookName, String sbookWriter, String sbookBorn, String sbookCost,
			String sbookCondition) {
		this.sbooknum = sbooknum;
		this.sbookName = sbookName;
		this.sbookWriter = sbookWriter;
		this.sbookBorn = sbookBorn;
		this.sbookCost = sbookCost;
		this.sbookCondition = sbookCondition;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getSbooknum() {
		return this.sbooknum;
	}

	public String getSbookName() {
		return this.sbookName;
	}

	public String getSbookWriter() {
		return this.sbookWriter;
	}

	public String getSbookBorn() {
		return this.sbookBorn;
	}

	public String getSbookCost() {
		return this.sbookCost;
	}

	public String getSbookCondition() {
		return this.sbookCondition;
	}

}
