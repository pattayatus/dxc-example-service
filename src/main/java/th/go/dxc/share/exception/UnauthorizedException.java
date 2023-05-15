package th.go.dxc.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ClientException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2548859915028667047L;

	public UnauthorizedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UnauthorizedException(String msg) {
		super(msg);
	}


}
