package th.go.dxc.share.qm.infra.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface QmRepository<T,ID> extends PagingAndSortingRepository<T, ID>,QueryByExampleExecutor<T>,JpaSpecificationExecutor<T>{

}
