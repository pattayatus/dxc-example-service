package th.go.dxc.share.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ClientException extends NestedRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6286618658840184174L;

	public ClientException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ClientException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {		
		return super.getMessage();
	}


}
