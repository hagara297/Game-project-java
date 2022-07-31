package buildings;

import exceptions.BuildingInCoolDownException; 
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);

	}

	@Override
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		Infantry x=null;
		if(this.isCoolDown()==true) {
			throw new BuildingInCoolDownException();
		}if(this.getCurrentRecruit()==this.getMaxRecruit()) {
			throw new MaxRecruitedException();
		}
		if(this.getLevel()==1)
			x=new Infantry(1,50,0.5,0.6,0.7);
		
	else if(this.getLevel()==2)
		x=new Infantry(2,50,0.5,0.6,0.7);
		
	else if(this.getLevel()==3)
		x=new Infantry(3,60,0.6,0.7,0.8);
		
		this.setCurrentRecruit(this.getCurrentRecruit()+1);

		return x;
	
	}
		
			
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
	       super.upgrade();
			 
				 if(this.getLevel()==1) {
					 this.setLevel(2);
					 this.setUpgradeCost(1500);
					 this.setRecruitmentCost(550);
					 this.setCoolDown(true);
					 
				 } 
				 else  if(this.getLevel()==2) {
					 this.setLevel(3);
					 this.setRecruitmentCost(600);
					 this.setCoolDown(true);
					 
					
				 }
		}

	}


