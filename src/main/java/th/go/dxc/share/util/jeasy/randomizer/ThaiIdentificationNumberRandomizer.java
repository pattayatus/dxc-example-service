package th.go.dxc.share.util.jeasy.randomizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThaiIdentificationNumberRandomizer implements Randomizer<String>{
	private final Random random;
	
	
	
public ThaiIdentificationNumberRandomizer() {
		super();
		this.random = new Random();
	}

	
//ขั้นตอนที่ 1 – เอาเลข 12 หลักมา เขียนแยกหลักกันก่อน (หลักที่ 13 ไม่ต้องเอามานะคร้าบ)
//1	2	0	1	5	4	1	4	6	2	2	3
//ขั้นตอนที่ 2 – เอาเลข 12 หลักนั้นมา คูณเข้ากับเลขประจำหลักของมัน
//รหัสบัตร	1	2	0	1	5	4	1	4	6	2	2	3
//ตัวคูณ	13	12	11	10	9	8	7	6	5	4	3	2
//ผลคูณ	13	24	0	10	45	32	7	24	30	8	6	6
//ขั้นตอนที่ 3 – เอาผลคูณทั้ง 12 ตัวมา บวกกันทั้งหมด จะได้ 13+24+0+10+45+32+7+24+30+8+6+6=205
//ขั้นตอนที่ 4 – เอาเลขที่ได้จากขั้นตอนที่ 3 มา mod 11 (หารเอาเศษ) จะได้ 205 mod 11 = 7
//ขั้นตอนที่ 5 – เอา 11 ตั้ง ลบออกด้วย เลขที่ได้จากขั้นตอนที่ 4 จะได้ 11-7 = 4 (เราจะได้ 4 เป็นเลขในหลัก Check Digit) ถ้าเกิด ลบแล้วได้ออกมาเป็นเลข 2 หลัก ให้เอาเลขในหลักหน่วยมาเป็น Check Digit (เช่น 11 ให้เอา 1 มา, 10 ให้เอา 0 มา เป็นต้น)
	private String randomThaiIdentificationNumber() {
		String tidn = "";
		List<Integer> numberList = new ArrayList<Integer>(13);
		Integer sumMultiply = 0;
		numberList.add(1+random.nextInt(9));
		sumMultiply = 13*numberList.get(0);
		for(int i=1;i<12;i++)
		{
			Integer value = random.nextInt(10);
			numberList.add(value);
			sumMultiply += ((13-i)*value);			
		}
		Integer modResult = sumMultiply%11;
		Integer checkDigit = (11-modResult)%10;
		numberList.add(checkDigit);
		for (Iterator<Integer> iterator = numberList.iterator(); iterator.hasNext();) {
			Integer eachValue = (Integer) iterator.next();
			tidn+=eachValue;
		}
		return tidn;
	}
	
	@Override
	public String getRandomValue() {
		return randomThaiIdentificationNumber();
	}

	public static void main(String[] args) {
		ThaiIdentificationNumberRandomizer random = new ThaiIdentificationNumberRandomizer();
		log.debug("tidn: "+random.getRandomValue());
		log.debug("tidn: "+random.getRandomValue());
		log.debug("tidn: "+random.getRandomValue());
		log.debug("tidn: "+random.getRandomValue());
	}
	
}
