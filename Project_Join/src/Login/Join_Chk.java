package Login;

public class Join_Chk {
	public Join_Chk() {

	}

	public boolean RepetiteID(String sid) // ���̵� �ߺ� Ȯ��
	{
		for (int i = 0; i < Member.MemeverVec.size(); i++) {
			if (Member.MemeverVec.get(i).getSid().equals(sid)) {
				return false;
			}
		}
		return true;
	}

	public boolean PasswordLengthChk(String spw) {
		// ��й�ȣ ���� üũ"�� ����, ���ڷ� 8�� �̻�"
		if (spw.length() < 8)
			return false;
		else
			return true;
	}

	public boolean PasswordMatchChk(String spw, String sRepw) {
		// ��й�ȣ �Է� �� �� ��й�ȣ�� ��Ȯ�� ��й�ȣ�� ��ġ�ϴ��� Ȯ��
		if (spw.equals(sRepw))
			return true;
		else
			return false;
	}
}
