package com.sugar.jzaif;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.nyatla.jzaif.types.CurrencyPair;



public class SugarMain {

	public static void main(String[] args){
		System.out.println("TSUMITATE ORDERS! " + new Date());
		
		
		List<SugarZaifBuyer> sbuyers = new ArrayList();
		final String APIKEY = SugarZaifKeyReader.getApiKey();
		final String SECKEY = SugarZaifKeyReader.getSecretKey(); 
		// memo Zaifでは restapikey, apikeyが一緒になっている　→　一旦、seckeyはRestclientに引き渡す。
		try {
			SugarZaifOrderValues xemValues = SugarZaifKeyReader.getCoinValue(CurrencyPair.XEMJPY);
			SugarZaifOrderValues ethValues = SugarZaifKeyReader.getCoinValue(CurrencyPair.ETHJPY);
			if(xemValues != null) {
				sbuyers.add(new SugarZaifBuyer(CurrencyPair.XEMJPY, new BigDecimal(10), xemValues.getBaseAmountJPY(), xemValues.getBaseAmountJPYLow(), 3, 1, APIKEY, SECKEY));
			}else {
				sbuyers.add(new SugarZaifBuyer(CurrencyPair.XEMJPY, new BigDecimal(10), new BigDecimal(150), new BigDecimal(250), 3, 1, APIKEY, SECKEY));
			}
			if(ethValues != null) {
				sbuyers.add(new SugarZaifBuyer(CurrencyPair.ETHJPY, new BigDecimal(10), ethValues.getBaseAmountJPY(), ethValues.getBaseAmountJPYLow(), -2, 4, APIKEY, SECKEY));
			}else {
				sbuyers.add(new SugarZaifBuyer(CurrencyPair.ETHJPY, new BigDecimal(10), new BigDecimal(150), new BigDecimal(250), -2, 4, APIKEY, SECKEY));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		String NEWLINE = System.lineSeparator();
		
		RestClient.setApiKey(SECKEY);
		StringBuilder sb = new StringBuilder();
		sb.append("Tsumitate Orders:");
		sb.append(NEWLINE);
		for(SugarZaifBuyer temp_buyer : sbuyers) {
			try{
				sb.append("【" + temp_buyer.getCurrentPair() + "】");
				sb.append(NEWLINE);
				sb.append(temp_buyer.sendBuyOrder());
				sb.append(NEWLINE);
				Thread.sleep(1000);
				sb.append(temp_buyer.sendBuyOrderLower());
				Thread.sleep(1000);
				sb.append(NEWLINE);
				sb.append(temp_buyer.getCurrentPrice());
				sb.append(NEWLINE);
				sb.append(NEWLINE);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		RestClient.get("Orders in Zaif", sb.toString());
		System.out.println("#### END ####");
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
