package IOStream;

import java.io.File;
import java.util.ArrayList;

public class TEST {

	public static void main(String[] args) {
//		insertMau();
//		insertThe();
//		insertKichThuoc();
//		insertHinh();
	}

	public static void insertHinh() {
		String maSP = "";
		String mauSP = "";
		String url = "E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTweb_thayLong\\aokhoac";
		File f = new File(url);
		File[] folderSPs = f.listFiles();
		ArrayList<String> queryString = new ArrayList<String>();
		for (File folderSP : folderSPs) {
			maSP = folderSP.getName();
			if (!maSP.equals("sp") && !maSP.equals("SP1") && !maSP.equals("SP2") && !maSP.equals("test")) {
				File[] imgs = folderSP.listFiles();
				for (File img : imgs) {
					String imgName = img.getName();
					String mau = imgName.split("_")[1];
					if(mau.equals("reu")) {
						mau = "xanh_reu";
					}
					mauSP = mau.toUpperCase();
					String query = "INSERT INTO HINHANH(MA_SP, MA_CT_MAU, DUONG_DAN_ANH) VALUES('"+maSP+"', '"+mauSP+"', '/assets/imgs/product-imgs/"+imgName+"');";
					queryString.add(query);
				}
			}
		}
		for (String query : queryString) {
			System.out.println(query);
		}
	}
	
	public static void insertKichThuoc() {
		for (int i = 3; i <= 50; i++) {
			String sp = "SP"+i;
			String sql = "INSERT INTO KICHTHUOC(MA_SP, MA_CT_KICH_THUOC) VALUES('"+sp+"', 'S');\r\n"
					+ "INSERT INTO KICHTHUOC(MA_SP, MA_CT_KICH_THUOC) VALUES('"+sp+"', 'M');\r\n"
					+ "INSERT INTO KICHTHUOC(MA_SP, MA_CT_KICH_THUOC) VALUES('"+sp+"', 'L');\r\n"
					+ "INSERT INTO KICHTHUOC(MA_SP, MA_CT_KICH_THUOC) VALUES('"+sp+"', 'XL');\r\n"
					+ "";
			System.out.println(sql);
		}
	}
	public static void insertThe() {
		for (int i = 3; i <= 50; i++) {
			String sp = "SP"+i;
			String tag = "";
			if(i <=17) {
				tag = "AOKHOAT";
			} else {
				tag = "QUANTAY";
			}
			System.out.println("INSERT INTO THE(MA_SP, MA_CT_THE) VALUES('"+sp+"', '"+tag+"');");
		}
	}

	public static void insertMau() {
		String maSP = "";
		String mauSP = "";
		String url = "E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTweb_thayLong\\aokhoac";
		File f = new File(url);
		File[] folderSPs = f.listFiles();
		ArrayList<String> queryString = new ArrayList<String>();
		for (File folderSP : folderSPs) {
			maSP = folderSP.getName();
			if (!maSP.equals("sp") && !maSP.equals("SP1") && !maSP.equals("SP2") && !maSP.equals("test")) {
				File[] imgs = folderSP.listFiles();
				for (File img : imgs) {
					String imgName = img.getName();
					String mau = imgName.split("_")[1];
					mauSP = mau.toUpperCase();
					String query = "INSERT INTO MAU(MA_SP, MA_CT_MAU) VALUES('" + maSP + "', '" + mauSP + "');";
					if (!queryString.contains(query)) {
						queryString.add(query);
					}
				}
			}
		}
		for (String query : queryString) {
			System.out.println(query);
		}
	}

	public static void xapXepHinh() {
		String url = "E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTweb_thayLong\\aokhoac";
		File f = new File(url);
		File[] imgs = f.listFiles();
		for (File img : imgs) {
			if (img.isFile()) {
				String namefile = img.getName();
				String folderName = "SP" + namefile.split("_")[2];
				File folder = new File("E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTweb_thayLong\\aokhoac\\" + folderName);
				if (!folder.exists()) {
					folder.mkdir();
				}
				String path = folder.getAbsolutePath() + "\\" + img.getName();
				BT1.copyFile(img.getAbsolutePath(), path, true);
			}
		}
	}
}
