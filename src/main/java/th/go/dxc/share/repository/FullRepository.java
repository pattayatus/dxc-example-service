package th.go.dxc.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface FullRepository<ENTITY, ID> extends JpaRepository<ENTITY, ID>, PagingAndSortingRepository<ENTITY, ID>,
		QueryByExampleExecutor<ENTITY>, JpaSpecificationExecutor<ENTITY> {

}
