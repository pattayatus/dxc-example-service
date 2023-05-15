package th.go.dxc.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BadRequestException extends ClientException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2548859915028667047L;

	public BadRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BadRequestException(String msg) {
		super(msg);
	}


}
