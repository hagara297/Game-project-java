package units;

import java.util.ArrayList;
import java.util.Random;

import exceptions.MaxCapacityException;

/**
 * @author mohammad.hussein
 *
 */
public class Army{
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
	@SuppressWarnings("unused")
	private final int maxToHold=10;

	public Army(String currentLocation) {
		this.currentLocation=currentLocation;
		currentStatus=Status.IDLE;
		units=new ArrayList<Unit>();
		distancetoTarget=-1;
		target="";
	}
	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public int getMaxToHold() {
		return maxToHold;
	}public void relocateUnit(Unit unit) throws MaxCapacityException{
		if(this.units.size()<this.getMaxToHold()) {
			unit.getParentArmy().units.remove(unit);
			this.units.add(unit);
			unit.setParentArmy(this);
			
		}else {
			throw new MaxCapacityException("maximum numberof units has been reached");
			
		}
		
		
		
	}
	public void handleAttackedUnit(Unit u) {
		if(u.getCurrentSoldierCount()==0)
			units.remove(u);
		
		
	}public double foodNeeded() {
		double x=0.0;
		for(int i=0;i<units.size();i++) {
			if(this.getCurrentStatus()==Status.IDLE)
			x=x+((units.get(i).getIdleUpkeep())*(units.get(i).getCurrentSoldierCount()));
			if(this.getCurrentStatus()==Status.BESIEGING)
				x=x+((units.get(i).getSiegeUpkeep())*(units.get(i).getCurrentSoldierCount()));
			if(this.getCurrentStatus()==Status.MARCHING)
				x=x+((units.get(i).getMarchingUpkeep())*(units.get(i).getCurrentSoldierCount()));
		}return x;
	}
	
	public void reset() {
		for(int i=0;i<units.size();i++) {
			units.get(i).Reset();
		
		}
	}public void lose10() {
		for(int i=0;i<this.getUnits().size();i++) {
			this.getUnits().get(i).setCurrentSoldierCount(this.getUnits().get(i).getCurrentSoldierCount()-
					(int)(this.getUnits().get(i).getCurrentSoldierCount()*0.1));
		}}
	public static Unit getRandom(ArrayList<Unit> arrayList) {
	    int rnd = new Random().nextInt(arrayList.size());
	    return arrayList.remove(rnd);
	}
	
	
}












