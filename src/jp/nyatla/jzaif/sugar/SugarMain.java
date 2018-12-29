package jp.nyatla.jzaif.sugar;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.nyatla.jzaif.types.CurrencyPair;


public class SugarMain {

	public static void main(String[] args){
		System.out.println("TSUMITATE ORDERS! " + new Date());
		
		SugarBuyer sbuyer;
		final String APIKEY = "";
		final String SECKEY = "";
		try {
			sbuyer = new SugarBuyer(CurrencyPair.XEMJPY, new BigDecimal(10), new BigDecimal(150), new BigDecimal(250), 3, 3, APIKEY, SECKEY);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		String NEWLINE = System.lineSeparator();
		
		RestClient.setRestApiKey(SugarKeyReader.getRestApiKey());
		StringBuilder sb = new StringBuilder();
		sb.append("Tsumitate Orders:");
		sb.append(NEWLINE);
		try{
			
			sb.append(sbuyer.sendBuyOrder());
			sb.append(NEWLINE);
			Thread.sleep(1000);
			sb.append(sbuyer.sendBuyOrderLower());
			Thread.sleep(1000);
			sb.append(NEWLINE);
			sb.append(NEWLINE);
			sb.append(sbuyer.getCurrentPrice());
			RestClient.get("Orders in Zaif", sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean hasTradePair(String[] args, String pair) {
		if(args == null || args.length == 0) {
			return true;
		}
		for(String tempPair : args) {
			if(tempPair.equalsIgnoreCase(pair)) {
				return true;
			}
		}
		return false;
	}

}
