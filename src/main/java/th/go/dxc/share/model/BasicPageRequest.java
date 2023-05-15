package th.go.dxc.share.model;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class BasicPageRequest implements Pageable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -282023092178646592L;
	private Sort sort;
	private int page;
	private int size;

	public BasicPageRequest() {
		this(0,10);
		sort = Sort.unsorted();
	}
	
	/**
	 * Creates a new {@link BasicPageRequest}. Pages are zero indexed, thus providing 0 for {@code page} will return
	 * the first page.
	 *
	 * @param page must not be less than zero.
	 * @param size must not be less than one.
	 */
	public BasicPageRequest(int page, int size) {

		if (page < 0) {
			throw new IllegalArgumentException("Page index must not be less than zero!");
		}

		if (size < 1) {
			throw new IllegalArgumentException("Page size must not be less than one!");
		}

		this.page = page;
		this.size = size;
		this.sort = Sort.unsorted();
	}
	
	/**
	 * Creates a new {@link Pageable} with sort parameters applied.
	 *
	 * @param page zero-based page index, must not be negative.
	 * @param size the size of the page to be returned, must be greater than 0.
	 * @param sort must not be {@literal null}, use {@link Sort#unsorted()} instead.
	 */
	public BasicPageRequest(int page, int size, Sort sort) {
		this(page, size);
		Assert.notNull(sort, "Sort must not be null!");
		this.sort = sort;
	}

	
	

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getSort()
	 */
	public Sort getSort() {
		return sort;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#next()
	 */

	public Pageable next() {
		return new BasicPageRequest(getPageNumber() + 1, getPageSize(), getSort());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.AbstractPageable#previous()
	 */

	public Pageable previous() {
		return getPageNumber() == 0 ? this : new BasicPageRequest(getPageNumber() - 1, getPageSize(), getSort());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#first()
	 */

	public Pageable first() {
		return new BasicPageRequest(0, getPageSize(), getSort());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	public boolean equals(@Nullable Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BasicPageRequest)) {
			return false;
		}

		BasicPageRequest that = (BasicPageRequest) obj;

		return super.equals(that) && this.sort.equals(that.sort);
	}

	/**
	 * Creates a new {@link Pageable} with {@code pageNumber} applied.
	 *
	 * @param pageNumber
	 * @return a new {@link Pageable}.
	 * @since 2.5
	 */

	public Pageable withPage(int pageNumber) {
		return new BasicPageRequest(pageNumber, getPageSize(), getSort());
	}

	/**
	 * Creates a new {@link Pageable} with {@link Direction} and {@code properties} applied.
	 *
	 * @param direction must not be {@literal null}.
	 * @param properties must not be {@literal null}.
	 * @return a new {@link Pageable}.
	 * @since 2.5
	 */
	public Pageable withSort(Direction direction, String... properties) {
		return new BasicPageRequest(getPageNumber(), getPageSize(), Sort.by(direction, properties));
	}

	/**
	 * Creates a new {@link Pageable} with {@link Sort} applied.
	 *
	 * @param sort must not be {@literal null}.
	 * @return a new {@link Pageable}.
	 * @since 2.5
	 */
	public Pageable withSort(Sort sort) {
		return new BasicPageRequest(getPageNumber(), getPageSize(), sort);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */

	public int hashCode() {
		return 31 * super.hashCode() + sort.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String toString() {
		return String.format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(), sort);
	}

	


	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getPageSize()
	 */
	public int getPageSize() {
		return size;
	}

	public void setPageNumber(int page) {
		this.page = page;
	}


	public void setPageSize(int size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getPageNumber()
	 */
	public int getPageNumber() {
		return page;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getOffset()
	 */
	public long getOffset() {
		return (long) page * (long) size;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#hasPrevious()
	 */
	public boolean hasPrevious() {
		return page > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#previousOrFirst()
	 */
	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}


}
