import java.util.Scanner;

public class CoolNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int l=3,r=7;
		int result=0;
//		Scanner s = new Scanner(System.in);
//        String N = s.next();
//		while(l<=r){
//			result+=fn(l);
//			l++;
//		}
		System.out.println(fn(5,1,false));
		
				
		
		
	}
	
	public static int fn(int number){
		int digits = getNumOfDigits(number);
		int msb= number/(int)Math.pow(10, digits-1);
		int firstGreaterCoolNum=0;
		if(msb<=2)
			firstGreaterCoolNum=getFirstCoolNumber(digits);
		else if(msb <= 5){
			int i=digits;
			while(i>1){
				firstGreaterCoolNum=firstGreaterCoolNum*10+2;
				i--;
			}
			firstGreaterCoolNum+=5*Math.pow(10, digits-1);
		}else{
			firstGreaterCoolNum=getFirstCoolNumber(digits+1);
		}
		while(firstGreaterCoolNum<number){
			firstGreaterCoolNum=getNextCoolNumber(firstGreaterCoolNum);
		}
		return firstGreaterCoolNum;
	}
	
	
	/**
	 * count number of digits in a number
	 * @param num
	 * @return
	 */
	private static int getNumOfDigits(long num){
		int result=0;
		while(num>0){
			num=num/10;
			result++;
		}
		return result;
	}
	
	/**
	 * gets first cool number with N digits
	 * @param digits
	 * @return
	 */
	private static int getFirstCoolNumber(int digits){
		int result=0;
		while(digits>0){
			result=(result*10)+2;
			digits--;
		}
		return result;
	}
	
	/**
	 * gets next cool Number for the current cool number
	 * @param curNum
	 * @return
	 */
	public static int getNextCoolNumber(int curNum){
		int newNum=0,temp=curNum,power=0;
		boolean gotFirst2= false;
		int curDigit;
		while(temp>0){
			curDigit=temp%10;
			if(!gotFirst2 && curDigit==2){
				int modifyDigits= getNumOfDigits(temp/10);
				newNum=(int) (5*Math.pow(10, power)+getFirstCoolNumber(power-1));
				gotFirst2=true;
			}else{
				newNum=(int) (curDigit*Math.pow(10, power)+newNum);
			}
			power++;
			temp=temp/10;
			
		}
		if(newNum>curNum)
			return newNum;
		else
			return getFirstCoolNumber(power+1);
	}
	
	
	public static String fn(int number, int digits, boolean alreadyGreater){
		String result=null;
		if(digits==0)
			return "";
		if(digits==1){
			if(number<=2)
				return Integer.toString(2);
			else if(number<=5)
				return Integer.toString(5);
			else
				return Integer.toString(22);
			
		}
		if(alreadyGreater){
			StringBuilder sb = new StringBuilder("");
			for(int i=0;i<digits;i++){
				sb.append('2');
			}
			return sb.toString();
		}
		
		int cur10Power= (int) Math.pow(10, digits-1);
		int curDigit=  (number/ cur10Power);
		String carry=null;
		if(curDigit<2){
			curDigit=2;
			alreadyGreater=true;
			carry = fn(number%cur10Power, digits-1, alreadyGreater);
		}else if(curDigit==2){
			carry = fn(number%cur10Power, digits-1, alreadyGreater);
		}else if(curDigit<5){
			curDigit= 5;
			alreadyGreater=true;
			carry= fn(number%cur10Power, digits-1, alreadyGreater);
		}else if(curDigit==5){
			carry = fn(number%cur10Power, digits-1, alreadyGreater);
		}else{
			
			return Integer.toString(getFirstCoolNumber(digits+1));
		}
		
		if(alreadyGreater){
			result= curDigit+carry;
		}else{
			if(carry.charAt(0)=='2'){
				if(curDigit==5)
					result= 2+carry;
				if(curDigit==2)
					result= 5+carry.substring(1, carry.length());
		}else{
			result= curDigit+carry;
		}
			
	}
	return result;
	}
}
