package th.go.dxc.share.model;

public enum PersonSexCodeSimpleType {
//	PersonSexCodeSimpleType	enumeration	F	FEMALE
//	PersonSexCodeSimpleType	enumeration	M	MALE
//	PersonSexCodeSimpleType	enumeration	U	UNKNOWN / UNSPECIFIED
//	PersonSexCodeSimpleType	enumeration	X	NON-BINARY

	
	FEMALE("F"),MALE("M"),UNKNOWN("U"),NON_BINARY("X");
	private final String code;
	PersonSexCodeSimpleType(String code){
		this.code = code;
	}
	public String code() {
		return code;
	}
}
