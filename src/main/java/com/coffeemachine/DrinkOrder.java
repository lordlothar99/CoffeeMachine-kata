package com.coffeemachine;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DrinkOrder {

	private DrinkType type;
	private int sugarQuantity;
	private BigDecimal cashAmount = BigDecimal.ZERO;

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}

	public DrinkType getType() {
		return type;
	}

	public int getSugarQuantity() {
		return sugarQuantity;
	}

	public void setSugarQuantity(int sugarQuantity) {
		this.sugarQuantity = sugarQuantity;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
		builder.append("type", type);
		builder.append("sugarQuantity", sugarQuantity);
		builder.append("cashAmount", cashAmount);
		return builder.toString();
	}
}
