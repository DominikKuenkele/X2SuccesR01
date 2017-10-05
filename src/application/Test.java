package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		
		String[] test = new String[3];
		test[0]="eins";
		test[1]="zwei";
		test[2]="drei";
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(test);
		System.out.println(json);
		String[] result = gson.fromJson(json, String[].class);
		for(int i=0; i<3; i++) {
			System.out.println(result[i]);
		}
//		Verwaltung verwaltung = new Verwaltung();
		// verwaltung.createUser("Dominik", "Kuenkele", "m", "71384", "Weinstadt",
		// "Teckstrasse", "19", LocalDate.of(1999, 02, 05),
		// "dominik.kuenkele@gmail.com",
		// "password");
//		 NutzerDAO nutzerdao = new NutzerDAO();
//		 nutzerdao.deleteNutzer("dominik.kuenkele@gmail.com");
//		// System.out.println(nutzerdao.getAllNutzer());
//		// System.out.println(dao.getNutzer(6));
//		Nutzer nutzer;
//		UnternehmensprofilDAO u = new UnternehmensprofilDAO();

//		try {
//			verwaltung.register("Dominik", "Kuenkele", "m", "71384", "Weinstadt", "Teckstrasse", "19",
//					LocalDate.of(1999, 02, 05), "dominik.kuenkele@gmail.com", "password");
////			verwaltung.createUnternehmen("BurgerKing", "GmbH", "71384", "Weinstadt", "Hauptstrasse", "50",
////					LocalDate.of(2000, 12, 01), 20, "This is a description.", "These are benefits:", "IT",
////					"www.test.com", "Torsten", "Schmidt");
//			// nutzer = new Nutzer("Dominik", "Kuenkele", "m", LocalDate.of(1999, 02, 05),
//			// "dominik.kuenkele@outlook.com",
//			// "password", new Adresse("71384", "Weinstadt", "Teckstrasse", "19"), "U");
//			// u.addUnternehmensprofil(new Unternehmensprofil("BurgerKing", "GmbH",
//			// new Adresse("71384", "Weinstadt", "Hauptstrasse", "50"), LocalDate.of(2000,
//			// 12, 01), 20,
//			// "This is a description.", "These are benefits:", "IT", "www.test.com",
//			// "Torsten", "Schmidt",
//			// nutzer));
//
//		} catch (UserInputException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
