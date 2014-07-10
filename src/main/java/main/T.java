package main;

public class T {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String remark="[type=1&fmt=yyyy-MM-dd]";
		int idx=remark.indexOf("type=");
		String type=remark.substring(idx+5, idx+6);
		String fmt=null;
		if ("1".equals(type)) {//
			int idxFmtStart=remark.indexOf("&")+5;
			int idxFmtEnd=remark.indexOf("]", idx);
			fmt=remark.substring(idxFmtStart,idxFmtEnd);
			System.out.println("--"+fmt);
		}
		System.out.println(type+","+fmt);
	}

}
