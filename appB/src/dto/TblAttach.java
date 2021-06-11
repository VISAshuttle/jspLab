package dto;

import java.util.Date;

public class TblAttach {
	private String fullName;
	private int bno;
	private Date regdate;
	
	public TblAttach(String fullName, int bno, Date regdate) {
		super();
		this.fullName = fullName;
		this.bno = bno;
		this.regdate = regdate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
