package com.coffeemachine;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DrinkOrder {

	private DrinkType type;
	private Sugar sugar;
	private float cashAmount;

	public void setCashAmount(float cashAmount) {
		this.cashAmount = cashAmount;
	}

	public float getCashAmount() {
		return cashAmount;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}

	public DrinkType getType() {
		return type;
	}

	public Sugar getSugar() {
		return sugar;
	}

	public void setSugar(Sugar sugar) {
		this.sugar = sugar;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
		builder.append("type", type);
		builder.append("sugar", sugar);
		builder.append("cashAmount", cashAmount);
		return builder.toString();
	}
}
