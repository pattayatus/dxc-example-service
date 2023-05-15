package th.go.dxc.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends ServerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2398433982632368525L;

	public InternalServerErrorException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InternalServerErrorException(String msg) {
		super(msg);
	}


}
