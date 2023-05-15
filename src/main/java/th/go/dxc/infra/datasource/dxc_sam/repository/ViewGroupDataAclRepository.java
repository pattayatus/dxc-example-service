package th.go.dxc.infra.datasource.dxc_sam.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import th.go.dxc.infra.datasource.dxc_sam.entity.ViewGroupDataAclEntity;

public interface ViewGroupDataAclRepository extends PagingAndSortingRepository<ViewGroupDataAclEntity, Long>{
	public List<ViewGroupDataAclEntity> findByUserUsername(String userUsername);
}
