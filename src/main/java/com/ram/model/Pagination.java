package com.ram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
	
	private long totalRecords;
	private int totalPages;
	private int recordsPerPage;
	private int currentPageNo;
	private int currentPageRecords;
	private boolean isFistPage;
	private boolean isLastPage;

}
