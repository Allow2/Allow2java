package com.allow2;

public enum Allow2EnvType {
	PRODUCTION,		// production environment, use this in any released code or products
	SANDBOX,		// sandbox environment, use this if you are testing and do not care about data stability
	STAGING			// bleeding edge environment, no guarantee if this is up or down or works or not, use at own peril
}
