package com.ram.util;

import org.springframework.data.domain.Page;

import com.ram.entity.Book;
import com.ram.model.Pagination;

public class CommonUtils {
	
	public static Pagination createPagination(Page<Book> page) {
		
		return Pagination
					.builder()
					.totalRecords(page.getTotalElements())
					.totalPages(page.getTotalPages())
					.recordsPerPage(page.getSize())
					.currentPageNo((page.getNumber() < page.getTotalPages()) ? (page.getNumber() + 1) : (page.getNumber()))
					.currentPageRecords(page.getNumberOfElements())
					.isFistPage(page.isFirst())
					.isLastPage(page.isLast())
					.build();
		
	}
}
