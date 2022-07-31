package buildings;

import exceptions.BuildingInCoolDownException; 
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);

	}

	@Override
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		Cavalry x=null;
		if(this.isCoolDown()==true) {
			throw new BuildingInCoolDownException();
		}if(this.getCurrentRecruit()==this.getMaxRecruit()) {
			throw new MaxRecruitedException();
		}
		if(this.getLevel()==1)
			x=new Cavalry(1,40,0.6,0.7,0.75);
		
	else if(this.getLevel()==2)
		x=new Cavalry(2,40,0.6,0.7,0.75);
		
	else if(this.getLevel()==3)
		x=new Cavalry(3,60,0.7,0.8,0.9);
		
		this.setCurrentRecruit(this.getCurrentRecruit()+1);

		return x;
	
	
		
		
		
}public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
	 super.upgrade();
	 if(this.getLevel()==1) {
		 this.setLevel(2);
		 this.setUpgradeCost(1500);
		 this.setRecruitmentCost(550);
		 this.setCoolDown(true);
		 
	 } 
	 else if(this.getLevel()==2) {
		 this.setLevel(3);
		 this.setRecruitmentCost(600);
		 this.setCoolDown(true);		 
	 }
}
		
	}
