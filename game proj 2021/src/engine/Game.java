package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import buildings.Farm;
import buildings.Market;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {

		player = new Player(playerName);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equals(playerCity))

				player.getControlledCities().add(c);                    

			else
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");

		}
	}

	private void loadCitiesAndDistances() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
		String currentLine = br.readLine();
		ArrayList<String> names = new ArrayList<String>();

		while (currentLine != null) {

			String[] content = currentLine.split(",");
			if (!names.contains(content[0])) {
				availableCities.add(new City(content[0]));
				names.add(content[0]);
			} else if (!names.contains(content[1])) {
				availableCities.add(new City(content[1]));
				names.add(content[1]);
			}
			distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
			currentLine = br.readLine();

		}
		br.close();
	}

	public void loadArmy(String cityName, String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = br.readLine();
		Army resultArmy = new Army(cityName);
		while (currentLine != null) {
			String[] content = currentLine.split(",");
			String unitType = content[0].toLowerCase();
			int unitLevel = Integer.parseInt(content[1]);
			Unit u = null;
			if (unitType.equals("archer")) {

				if (unitLevel == 1)
					u = (new Archer(1, 60, 0.4, 0.5, 0.6));

				else if (unitLevel == 2)
					u = (new Archer(2, 60, 0.4, 0.5, 0.6));
				else
					u = (new Archer(3, 70, 0.5, 0.6, 0.7));
			} else if (unitType.equals("infantry")) {
				if (unitLevel == 1)
					u = (new Infantry(1, 50, 0.5, 0.6, 0.7));

				else if (unitLevel == 2)
					u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
				else
					u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
			} else if (unitType.equals("cavalry")) {
				if (unitLevel == 1)
					u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));

				else if (unitLevel == 2)
					u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
				else
					u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
			}
			u.setParentArmy(resultArmy);
			resultArmy.getUnits().add(u);
			currentLine = br.readLine();
		}
		br.close();
		for (City c : availableCities) {
			if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
				c.setDefendingArmy(resultArmy);
		}
	}public void endTurn() {
		this.currentTurnCount++;
		this.helpreset();
		for(int i=0;i<player.getControlledCities().size();i++) {
			for(int j=0;j<player.getControlledCities().get(i).getEconomicalBuildings().size();j++) {
				if(player.getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Farm) {
					player.setFood(player.getControlledCities().get(i).getEconomicalBuildings().get(j).harvest()+player.getFood());
				}else {
					player.setTreasury(player.getControlledCities().get(i).getEconomicalBuildings().get(j).harvest()+player.getTreasury());
				}
			}
		}double food=0;
		for(int i=0;i<player.getControlledArmies().size();i++) {
			food=food+player.getControlledArmies().get(i).foodNeeded();
			
		}player.setFood(player.getFood()-food);
		if(player.getFood()<0)
			player.setFood(0);
		if(player.getFood()==0) {
			for(int i=0;i<player.getControlledArmies().size();i++) {
				for(int j=0;j<player.getControlledArmies().get(i).getUnits().size();j++) {
					player.getControlledArmies().get(i).getUnits().get(j).setCurrentSoldierCount(player.getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()-
							(int)(player.getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()*0.1));
				}
			}
		}for(int i=0;i<player.getControlledArmies().size();i++) {
			if(!player.getControlledArmies().get(i).getTarget().equalsIgnoreCase("")){
				player.getControlledArmies().get(i).setDistancetoTarget(player.getControlledArmies().get(i).getDistancetoTarget()-1);
			if(player.getControlledArmies().get(i).getDistancetoTarget()==0) {
				player.getControlledArmies().get(i).setCurrentLocation(player.getControlledArmies().get(i).getTarget());
				player.getControlledArmies().get(i).setTarget("");
			}
		}}for(int i=0;i<availableCities.size();i++) {
			if(availableCities.get(i).isUnderSiege()==true) {
				availableCities.get(i).setTurnsUnderSiege(availableCities.get(i).getTurnsUnderSiege()+1);
				for(int j=0;j<availableCities.get(i).getDefendingArmy().getUnits().size();j++) {
					availableCities.get(i).getDefendingArmy().getUnits().get(j).setCurrentSoldierCount(availableCities.get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount()-
							(int)(availableCities.get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount()*0.1));
				}
			}
			
		}
		
		
		
	} public void targetCity(Army army, String targetName) {
		army.setTarget(targetName);
		for(int i=0;i<distances.size();i++) {
			if(distances.get(i).getFrom().equalsIgnoreCase(army.getTarget()) 
				&& distances.get(i).getTo().equalsIgnoreCase(army.getCurrentLocation())) {
				army.setDistancetoTarget(distances.get(i).getDistance());
			}
				
		}
		
		
		
	}public void occupy(Army a,String cityName) {
		City x=null;
		for(int i=0;i<availableCities.size();i++) {
			if(availableCities.get(i).getName().equalsIgnoreCase(cityName))
				x=availableCities.get(i);
		} x.setDefendingArmy(a);
		x.setTurnsUnderSiege(getCurrentTurnCount()-2);
		x.setUnderSiege(false);
		this.getPlayer().getControlledCities().add(x);
		
	}
	public void autoResolve(Army attacker, Army defender) throws
	FriendlyFireException{
		Boolean flag=false;
		while(attacker.getUnits().size()!=0 && defender.getUnits().size()!=0) {	
			
			Unit x =attacker.getRandom(attacker.getUnits());
			Unit y=defender.getRandom(defender.getUnits());
			if(flag==false)
				x.attack(y);
			else if(flag==true)
				y.attack(x);
			if(attacker.getUnits().size()==0 && defender.getUnits().size()!=0)
				this.occupy(attacker, defender.getCurrentLocation());
			else if(defender.getUnits().size()==0 && attacker.getUnits().size()!=0)
				this.occupy(defender, attacker.getCurrentLocation());
			this.endTurn();
			
		}
		
		
	}public boolean  isGameOver() {
		
		if (this.currentTurnCount>this.getMaxTurnCount()|| player.getControlledCities().size() == this.availableCities.size() ){
			return true;
		}
		else return false;
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}public void helpreset() {
		for(int i=0;i<player.getControlledCities().size();i++) {
			player.getControlledCities().get(i).resetmilitaryBuildings();
			player.getControlledCities().get(i).reseteconomicalBuildings();
			player.getControlledCities().get(i).resetcurrentRecruit();
		}
	}
			
		
		

	}


  

