package th.go.dxc.infra.datasource.reference_db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import th.go.dxc.infra.datasource.reference_db.entity.HouseEntity;

public interface HouseRepository extends PagingAndSortingRepository<HouseEntity, String>{

}
