package dto;

import java.util.List;

public class TblAttachList {
	private int bno;
	private List<TblAttach> files;
	
	public TblAttachList(int bno, List<TblAttach> files) {
		super();
		this.bno = bno;
		this.files = files;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public List<TblAttach> getFiles() {
		return files;
	}

	public void setFiles(List<TblAttach> files) {
		this.files = files;
	}

}
