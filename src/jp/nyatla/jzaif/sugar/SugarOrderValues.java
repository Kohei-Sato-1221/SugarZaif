package jp.nyatla.jzaif.sugar;

import java.math.BigDecimal;

import jp.nyatla.jzaif.types.CurrencyPair;


public class SugarOrderValues {
	private CurrencyPair pair;
	private BigDecimal baseAmountJPY;
	private BigDecimal baseAmountJPYLow; 
	
	public SugarOrderValues(String pairStr, String baseAmountJPY, String baseAmountJPYLow){
		this.setPair(convertPair(pairStr));
		this.setBaseAmountJPY(new BigDecimal(baseAmountJPY));
		this.setBaseAmountJPYLow(new BigDecimal(baseAmountJPYLow));					
	}
	
	private CurrencyPair convertPair(String pairStr) {
		if(pairStr.equals("xem_jpy")) {
			return CurrencyPair.XEMJPY;
		}else {
			return null;
		}
	}
			
	public CurrencyPair getPair() {
		return pair;
	}

	public void setPair(CurrencyPair pair) {
		this.pair = pair;
	}

	public BigDecimal getBaseAmountJPYLow() {
		return baseAmountJPYLow;			
	}

	public void setBaseAmountJPYLow(BigDecimal baseAmountJPYLow) {
		this.baseAmountJPYLow = baseAmountJPYLow;
	}

	public BigDecimal getBaseAmountJPY() {
		return baseAmountJPY;
	}

	public void setBaseAmountJPY(BigDecimal baseAmountJPY) {
		this.baseAmountJPY = baseAmountJPY;
	}
}