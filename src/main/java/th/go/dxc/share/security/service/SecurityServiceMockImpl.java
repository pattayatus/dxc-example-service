package th.go.dxc.share.security.service;

import java.util.HashSet;
import java.util.Set;

import th.go.dxc.share.security.model.NinAuthentication;

public class SecurityServiceMockImpl implements SecurityService{
	private String ANONYMOUS = "anonymous";
	private String ANONYMOUS_THAI_NIN = "0000000000000";
	
	
	@Override
	public Set<String> getAllowedDatasets(String username) {
		return new HashSet<String>();
	}

	@Override
	public Set<String> getCurrentUserAllowedDataSet() {
		return new HashSet<String>();
	}

	@Override
	public Object getCurrentPrincipal() {
		return ANONYMOUS;
	}

	@Override
	public String getCurrentPrincipalName() {
		return ANONYMOUS;
	}

	@Override
	public String getCurrentUserNin() {
		return ANONYMOUS_THAI_NIN;
	}

	@Override
	public String getCurrentUserName() {
		return ANONYMOUS;
	}

	@Override
	public NinAuthentication loginWithNin(String nin) {
		return new NinAuthentication(nin);
	}

	@Override
	public Boolean isLogin() {
		return true;
	}

	@Override
	public Boolean isCurrentUserAllowedDataSet(String dataSetId) {
		return true;
	}

	@Override
	public Boolean isUserAllow(String userName, String dataSetId) {
		return true;
	}

}
