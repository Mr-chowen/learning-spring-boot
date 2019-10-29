package com.assemble;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class ComplexUser {
	private String uname;
	private List<String> hobbies;
	private Map<String, String> residenceMap;
	private Set<String> aliasSet;
	private String[] array;
	
	public ComplexUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComplexUser(String uname, List<String> hobbies, Map<String, String> residenceMap, Set<String> aliasSet,
			String[] array) {
		super();
		this.uname = uname;
		this.hobbies = hobbies;
		this.residenceMap = residenceMap;
		this.aliasSet = aliasSet;
		this.array = array;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public Map<String, String> getResidenceMap() {
		return residenceMap;
	}

	public void setResidenceMap(Map<String, String> residenceMap) {
		this.residenceMap = residenceMap;
	}

	public Set<String> getAliasSet() {
		return aliasSet;
	}

	public void setAliasSet(Set<String> aliasSet) {
		this.aliasSet = aliasSet;
	}

	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}

	@Override
	public String toString() {
		return "ComplexUser [uname=" + uname + ", hobbies=" + hobbies + ", residenceMap=" + residenceMap + ", aliasSet="
				+ aliasSet + ", array=" + Arrays.toString(array) + "]";
	}
	
	
	
}
