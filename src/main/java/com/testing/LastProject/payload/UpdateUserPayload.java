package com.testing.LastProject.payload;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateUserPayload {
	private String alias;
	private String name;
	private String phoneNumber;
	private String cityId;
}
