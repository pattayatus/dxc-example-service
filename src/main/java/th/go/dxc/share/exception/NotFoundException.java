package th.go.dxc.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NotFoundException extends ClientException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5230820183562610815L;

	public NotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NotFoundException(String msg) {
		super(msg);
	}

}
