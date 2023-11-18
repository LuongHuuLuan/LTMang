package midEX2;

import java.io.IOException;
import java.util.ArrayList;

public class test {
	public static void main(String[] args) {
		
		Candidate c1 = new Candidate("TS01", "Võ Tấn Toàn", 40, "src\\midEX2\\Test\\TS01CV.pdf");
		
		Candidate c2 = new Candidate("SV01", "Nguyễn Văn A", 19, "src\\midEX2\\Test\\SV01CV.pdf");
		Candidate c3 = new Candidate("SV02", "Trần Thị B", 19, "src\\midEX2\\Test\\SV02CV.pdf");
		Candidate c4 = new Candidate("SV03", "Lê Văn E", 19, "src\\midEX2\\Test\\SV03CV.pdf");
		Candidate c5 = new Candidate("SV04", "Lương Thị F", 19, "src\\midEX2\\Test\\SV04CV.pdf");
		
		Candidate c6 = new Candidate("SV05", "Mặc Văn G", 20, "src\\midEX2\\Test\\SV05CV.pdf");
		Candidate c7 = new Candidate("SV06", "Nguyễn Trấn H", 20, "src\\midEX2\\Test\\SV06CV.pdf");
		Candidate c8 = new Candidate("SV07", "Vũ Tấn I", 20, "src\\midEX2\\Test\\SV07CV.pdf");
		Candidate c9 = new Candidate("SV08", "Khương Tử J", 20, "src\\midEX2\\Test\\SV08CV.pdf");
		Candidate c10 = new Candidate("SV09", "Trương Tam K", 20, "src\\midEX2\\Test\\SV09CV.pdf");
		Candidate c11 = new Candidate("SV10", "Hoàng L", 20, "src\\midEX2\\Test\\SV10CV.pdf");
		
		ArrayList<Candidate> listCan = new ArrayList<Candidate>();
		listCan.add(c1);
		listCan.add(c2);
		listCan.add(c3);
		listCan.add(c4);
		listCan.add(c5);
		listCan.add(c6);
		listCan.add(c7);
		listCan.add(c8);
		listCan.add(c9);
		listCan.add(c10);
		listCan.add(c11);
		
		CandidateManagement canM = new CandidateManagement();
		try {
//			canM.saveInfo(listCan, "src\\midEX2\\Test\\saveInfo.txt");
//			canM.getCVById("src\\midEX2\\Test\\saveInfo.txt", "TS01", "src\\midEX2\\Test\\getPDFById");
			canM.getCVByAge("src\\midEX2\\Test\\saveInfo.txt", 20, "src\\midEX2\\Test\\getPDFByAge");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
