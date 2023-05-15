package th.go.dxc.infra.datasource.dxc_sam.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import th.go.dxc.infra.datasource.dxc_sam.entity.ViewUserDataAclEntity;

public interface ViewUserDataAclRepository extends PagingAndSortingRepository<ViewUserDataAclEntity, Long> {
	public List<ViewUserDataAclEntity> findByUserUsername(String userUsername);
}
