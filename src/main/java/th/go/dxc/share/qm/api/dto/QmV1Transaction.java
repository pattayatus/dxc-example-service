package th.go.dxc.share.qm.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class QmV1Transaction {
	private String id;
	private OffsetDateTime beginTime;
	private OffsetDateTime endTime;
	public QmV1Transaction() {
		super();
		id = UUID.randomUUID().toString();
	}
	
	public OffsetDateTime begin() {
		beginTime = OffsetDateTime.now();
		return beginTime;
	}
	public OffsetDateTime end() {
		endTime = OffsetDateTime.now();
		return endTime;
	}
}
