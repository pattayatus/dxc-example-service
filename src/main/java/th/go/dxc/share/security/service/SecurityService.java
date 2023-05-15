package th.go.dxc.share.security.service;

import java.util.Set;

import th.go.dxc.share.security.model.NinAuthentication;


public interface SecurityService {
	public static final String ANONYMOUS_USER="anonymousUser";
	public Set<String> getAllowedDatasets(String username);
	public Set<String> getCurrentUserAllowedDataSet();
	public Object getCurrentPrincipal();	
	public String getCurrentPrincipalName();
	public String getCurrentUserNin();
	public String getCurrentUserName();	
	public NinAuthentication loginWithNin(String nin);
	public Boolean isLogin();
	public Boolean isCurrentUserAllowedDataSet(String dataSetId);
	public Boolean isUserAllow(String userName,String dataSetId);
}
