package th.go.dxc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.app.qm.service.TsiPersonContactQmServiceJpaImpl;
import th.go.dxc.app.qm.util.TsiPersonContactQmServiceJpaImplMapper;
import th.go.dxc.infra.datasource.data_db.repository.TsiPersonContactRepository;
import th.go.dxc.share.qm.app.service.QmService;

@Configuration
public class ApplicaitonConfig {

	@Bean
	public QmService<TsiPersonContact, TsiPersonContactFilter> tsiPersonContactQmService(TsiPersonContactQmServiceJpaImplMapper mapper, TsiPersonContactRepository repository){
		return new TsiPersonContactQmServiceJpaImpl(mapper, repository);
	}
}
