package th.go.dxc.share.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorCode {
	private Integer major;
	private Integer minor;
	private Integer variant;
	
	public String getCode() {
		return String.format("%1d%2d-%3d", major,minor,variant);
	}
	
	public static void main(String[] args) {
		ErrorCode errorCode =new ErrorCode(1,2,3);
		System.out.println(errorCode);
		System.out.println(errorCode.getCode());
		
	}
}
