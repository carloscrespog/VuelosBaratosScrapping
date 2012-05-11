import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.*;






public class VuelosBaratosScrapper {

	VuelosBaratosConvenion queryGenerator;
	int vuelosEncontrados;
	
	public VuelosBaratosScrapper(){
		this.queryGenerator=new VuelosBaratosConvenion();
		this.vuelosEncontrados=0;
		
	}
	
	public List<Journey> getSchedule (String origin, String destination, 
			  String day, String month, String year,
			  String adults) throws IOException {
	
		
		List<Journey> journeyList = new LinkedList<Journey>();
		String queryUrl = queryGenerator.generateQuery(origin, destination, day, month, year,adults);
		System.out.println(queryUrl);
		System.out.println("Scrapping:");
		Document doc = Jsoup.connect(queryUrl).timeout(0).get(); //importante el timeout!
		//File aFile=new File("/Users/carloscrespog/blong2.js");
		//Writer output = new BufferedWriter(new FileWriter(aFile));
		try {
			Elements rows = doc.select("script");
			
			
			for (int i=0;i<rows.size();i++){
				String script=rows.get(i).html();
				if(script.startsWith("viewMgr")){
					
					String[] firstSplit= script.split("flights");
					
					//output.write(firstSplit[0]);output.write("\n\n\n");
					//output.write(firstSplit[1]);output.write("\n\n\n\n\n\n\n");
					
					String[] secondSplit= firstSplit[1].split(",updated");
					String flights="{flights"+secondSplit[0];
					flights=flights.concat("}");
					JSONSerializer jsonSer=new JSONSerializer();
					JSONObject flightObject=(JSONObject) jsonSer.toJSON(flights);
					JSONArray flightArray=flightObject.getJSONArray("flights");
					
					int size= flightArray.size();
					
					for(int j=0;j<size;j++){
						JSONObject flight=(JSONObject) flightArray.get(j);
						JSONObject outBound=(JSONObject) flight.get("outbound");
						String departureTime=outBound.getString("time");
						String arrivalTime=outBound.getString("arrive");
						String duration=outBound.getString("durationText");
						String originJ=flight.getString("dep");
						String destinationJ=flight.getString("dest");
						int fee=flight.getInt("value");
						String company=flight.getString("airline");
						if (company.equals("")){
							company=outBound.getString("airline");
						}
						if (company.equals("")){
							company=outBound.getString("aircode");
						}
						Journey journey=new Journey(departureTime, arrivalTime, duration,
								originJ, destinationJ, fee, company);
						//output.write(journey.toString());
						//output.write("\n\n\n");
						vuelosEncontrados++;
						//output.write("---------------"+vuelosEncontrados+"------------------");
						//output.write("\n\n\n");
						journeyList.add(journey);
					}
					//output.write("\n\n\n");
					
					//output.write("\n\n\n");
					//output.write("---------------"+i+"------------------");
					//output.write("\n\n\n");
					//output.write(flightArray.toString());
					//output.write("\n\n\n");
					
				}
				
			}
			//output.write(flightArray.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		     // output.close();
		      System.out.println("done");
		      System.out.println("Vuelos encontrados: "+vuelosEncontrados);
		    }
		
		
		
		
		
		return journeyList;
		
	}
	
	public Journey cheapest(List<Journey> list){
		
		Journey bestJourney=new Journey();
		bestJourney.setFee(99999);
		for (Journey j: list){
			if(j.getFee()<bestJourney.getFee()){
				bestJourney=j;
			}
		}
		return bestJourney;
		
	}
	public static void main(String [] args) throws IOException{
		VuelosBaratosScrapper vBS=new VuelosBaratosScrapper();
		List<Journey> list=vBS.getSchedule("MAD", "BER", "13", "08", "2012", "1");
		Journey bestJourney=vBS.cheapest(list);
		System.out.println(bestJourney.toString());
	}
}
