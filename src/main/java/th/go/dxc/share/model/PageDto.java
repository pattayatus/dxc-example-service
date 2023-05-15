package th.go.dxc.share.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description="เงื่อนไขขอบเขตผลลัพท์")
public class PageDto<T> extends PageImpl<T>{

	public PageDto() {
		super(new ArrayList<T>());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3164748397005190770L;

	public PageDto(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public PageDto(List<T> content) {
		super(content);
	}

	@ApiModelProperty("จำนวนหน้าข้อมูล")
	@Override
	public int getTotalPages() {
		return super.getTotalPages();
	}

	@Override
	public long getTotalElements() {
		return super.getTotalElements();
	}

	@Override
	public boolean isLast() {
		return super.isLast();
	}

	@Override
	public int getNumber() {
		return super.getNumber();
	}

	@Override
	public int getSize() {
		return super.getSize();
	}

	@Override
	public int getNumberOfElements() {
		return super.getNumberOfElements();
	}

	@Override
	public boolean hasPrevious() {
		return super.hasPrevious();
	}

	@Override
	public boolean isFirst() {
		return super.isFirst();
	}

	@Override
	public boolean hasContent() {
		return super.hasContent();
	}

	@Override
	public List<T> getContent() {
		return super.getContent();
	}

	@Override
	public Pageable getPageable() {
		return super.getPageable();
	}

	@Override
	public Sort getSort() {
		return super.getSort();
	}

	
	
}
