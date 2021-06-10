package com.pension.managment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bank {
	private String bankName;
	private long accountNumber;
	private String bankType;

	
}
