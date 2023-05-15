package th.go.dxc.share.util.jeasy.randomizer;

import org.jeasy.random.api.Randomizer;

public class ThaiFullNameRandomizer implements Randomizer<String>{

	private final ThaiGivenNameRandomizer givenName;
	private final ThaiSurNameRandomizer surName;
	
	
	public ThaiFullNameRandomizer() {
		super();
		this.givenName = new ThaiGivenNameRandomizer();
		this.surName = new ThaiSurNameRandomizer();
	}


	@Override
	public String getRandomValue() {
		return givenName.getRandomValue() + " "+surName.getRandomValue();
	}

}
