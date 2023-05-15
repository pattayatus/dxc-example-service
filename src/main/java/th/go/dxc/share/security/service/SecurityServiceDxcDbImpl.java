package th.go.dxc.share.security.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.extern.slf4j.Slf4j;
import th.go.dxc.infra.datasource.dxc_sam.entity.ViewGroupDataAclEntity;
import th.go.dxc.infra.datasource.dxc_sam.entity.ViewUserDataAclEntity;
import th.go.dxc.infra.datasource.dxc_sam.repository.ViewGroupDataAclRepository;
import th.go.dxc.infra.datasource.dxc_sam.repository.ViewUserDataAclRepository;
import th.go.dxc.share.security.model.NinAuthentication;

@Slf4j
public class SecurityServiceDxcDbImpl implements SecurityService{
	public static final String ALLOW_VALUE_ON = "on";
	public static final String ALLOW_VALUE_OFF = "off";
	public static final String DATASET_ID_DELIMITER = "-";
	private final ViewGroupDataAclRepository groupDataAclRepository;
	private final ViewUserDataAclRepository userDataAclRepository;
	
	
	
	public SecurityServiceDxcDbImpl(ViewGroupDataAclRepository groupDataAclRepository,
			ViewUserDataAclRepository userDataAclRepository) {
		super();
		this.groupDataAclRepository = groupDataAclRepository;
		this.userDataAclRepository = userDataAclRepository;
	}


	@Override
	@Cacheable("allowedDatasets")
	public Set<String> getAllowedDatasets(String username) {
		log.debug("getAllowedDatasets: "+username);
		Set<String> allowedDatasetSet = new HashSet<String>();
		Set<String> notAllowedDatasetSet = new HashSet<String>();
		List<ViewUserDataAclEntity> userDataAclList = userDataAclRepository.findByUserUsername(username);
		for (Iterator<ViewUserDataAclEntity> iterator = userDataAclList.iterator(); iterator.hasNext();) {
			ViewUserDataAclEntity viewUserDataAclEntity = iterator.next();
			String allowValue = viewUserDataAclEntity.getAllowValue();
			String dataset = viewUserDataAclEntity.getDatasetOrganizationId()+DATASET_ID_DELIMITER+viewUserDataAclEntity.getDatasetId();
			if(ALLOW_VALUE_ON.contentEquals(allowValue))
			{
				allowedDatasetSet.add(dataset);
			}else if(ALLOW_VALUE_OFF.contentEquals(allowValue))
			{
				notAllowedDatasetSet.add(dataset);
			}
		}
		List<ViewGroupDataAclEntity> groupDataAclList = groupDataAclRepository.findByUserUsername(username);
		for (Iterator<ViewGroupDataAclEntity> iterator = groupDataAclList.iterator(); iterator.hasNext();) {
			ViewGroupDataAclEntity viewGroupDataAclEntity = iterator.next();
			String allowValue = viewGroupDataAclEntity.getAllowValue();
			String organizationId = viewGroupDataAclEntity.getDatasetOrganizationId();
			String datasetId = viewGroupDataAclEntity.getDatasetId();
			// remove organization from datasetid
			if(datasetId.startsWith(organizationId+"-"))datasetId = datasetId.substring(organizationId.length()+1);
			String dataset = organizationId+DATASET_ID_DELIMITER+datasetId;
			
			if("on".contentEquals(allowValue))
			{
				allowedDatasetSet.add(dataset);
			}else if("off".contentEquals(allowValue))
			{
				notAllowedDatasetSet.add(dataset);
			}
		}
		log.trace("Allowed: "+allowedDatasetSet);
		log.trace("notAllowed: "+notAllowedDatasetSet);
		allowedDatasetSet.removeAll(notAllowedDatasetSet);
		log.trace("Final Allowed: "+allowedDatasetSet);
		log.debug("Allow {} datasets: ",allowedDatasetSet==null?0:allowedDatasetSet.size());
		return allowedDatasetSet;
	}
	
	
	public Object getCurrentPrincipal() {
		Object principal = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
		 {
			principal = authentication.getPrincipal();
		 }
		return principal;
	}
	
	public String getCurrentPrincipalName() {
		log.trace("getCurrentPrincipal");
		String currentPrincipalName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.trace("authentication: "+authentication);
		if(authentication!=null)currentPrincipalName = authentication.getName();
		log.debug("currentPrincipalName: "+currentPrincipalName);
		return currentPrincipalName;
	}
	public String getCurrentUserNin() {
		log.trace("getCurrentUserNin");
		String nin = "0000000000000";
		Object principal = getCurrentPrincipal();
		log.debug("principal: "+principal);
		if(principal!=null)
		{
			if(principal instanceof Jwt)
			{
				Jwt jwt = (Jwt)principal;
				log.trace("jwt: "+jwt);
				nin = jwt.getClaimAsString("nin");
			}else if(principal instanceof String) {
				nin = (String)principal;
			}
		}
		log.debug("nin: "+nin);
		return nin;
	}
	public String getCurrentUserName() {
		log.trace("getCurrentUserNin");
		String username = ANONYMOUS_USER;
		Object principal = getCurrentPrincipal();
		log.trace("principal: "+principal);
		if(principal!=null)
		{
			if(principal instanceof Jwt)
			{
				Jwt jwt = (Jwt)principal;
				log.trace("jwt: "+jwt);
				username = jwt.getClaimAsString("preferred_username");
			}else if(principal instanceof String) {
				username = (String)principal;
			}
		}
		log.debug("current username={}",username);
		return username;
	}
	
	public NinAuthentication loginWithNin(String nin) {
        log.debug("Logging in with [{}]", nin);
		NinAuthentication authentication = new NinAuthentication(nin);
        log.trace("authentication: [{}]", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
	}


	@Override
	public Set<String> getCurrentUserAllowedDataSet() {
		String username = getCurrentUserName();
		return getAllowedDatasets(username);
	}


	@Override
	public Boolean isLogin() {		
		String currentUserName = this.getCurrentUserName();		
		return !ANONYMOUS_USER.contentEquals(currentUserName);
	}


	@Override
	public Boolean isCurrentUserAllowedDataSet(String dataSetId) {
		String userName = getCurrentUserName();
		Set<String> allowedDataSetList= getAllowedDatasets(userName);
		return allowedDataSetList.contains(dataSetId);
	}


	@Override
	public Boolean isUserAllow(String userName, String dataSetId) {
		Set<String> allowedDataSetList= getAllowedDatasets(userName);
		return allowedDataSetList.contains(dataSetId);
	}
	
	

}
