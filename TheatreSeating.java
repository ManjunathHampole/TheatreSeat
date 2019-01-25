/*
Theatre Seating
Conditions:
1. Fill as many orders as possible
2. Put parties as close to the front as possible
3. If there are not enough seats available in the theatre to handle a party, tell them "Sorry, we can handle your party"
4. Each party must sit in a single row in a single section. If they wont fit, tell them "Call to split party"
*/

// 01/23/2019

import java.util.*;

public class TheatreSeating{
	public static void main(String [] args){
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> patrons = new ArrayList<String>();
		ArrayList<String> row = null;
		HashMap<String,ArrayList<String>> theatrelayout = new HashMap<String,ArrayList<String>>();
		HashMap<String,Integer> patronsReq = new HashMap<String,Integer>();
		int counter = 0;


		// Reading the layout and storing
		while(scanner.hasNext()){
			String sc = scanner.nextLine();
			if(sc.equals("")) break;
			counter++;
			row = new ArrayList<>();
			String string[] = sc.split(" ");
			for(String list:string){
				row.add(list);
			}
			theatrelayout.put("Row "+ counter, row);
		}
		
		// System.out.println(theatrelayout);

		// Reading the patron requests and storing
		while(scanner.hasNext()){
			String sc = scanner.nextLine();
			if(sc.equals("exit"))break;
			String rows[] = sc.split(" ");
			// Name
			patrons.add(rows[0]);
			// Request of patrons
			patronsReq.put(rows[0], Integer.valueOf(rows[1]));
		}
		//closing scanner
		// System.out.println(patronsReq);
		scanner.close();

		patrons.forEach((patronName)->{
			int val = patronsReq.get(patronName);
			//Service yes or no
			boolean servFlag = false;
			int newVal = 0;
			int indPositiion = 0;
			int sumofSeats = 0;

			for(int i = 0; i < theatrelayout.size(); ++i){
				ArrayList<String> seats = theatrelayout.get("Row "+ (i+1) );
				for(int j = 0 ; j < seats.size(); j++){
					if(Integer.valueOf(seats.get(j)) < val){
						sumofSeats += Integer.valueOf(seats.get(j));
					}
					else{
						System.out.println(patronName + " Row " + (i+1) + " Section "+ (j+1));
						servFlag = true;
						newVal = Integer.valueOf(seats.get(j)) - val;
						indPositiion = j;
						break;
					}
				}

				if(servFlag == true){
					seats.set(indPositiion, String.valueOf(newVal));
					break;
				}
			}

			if(servFlag == false){
				if(val > sumofSeats){
					System.out.println(patronName + " Sorry, we can't handle your party request");
				}
				else{
					System.out.println(patronName + ", Call to split party request.");
				}
			}

		});

	}
}