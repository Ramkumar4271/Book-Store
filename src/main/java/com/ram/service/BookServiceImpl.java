package com.ram.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ram.entity.Book;
import com.ram.model.APIResponse;
import com.ram.model.Pagination;
import com.ram.repository.BookRepository;
import com.ram.util.CommonUtils;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	public APIResponse createBook(Book book) {
		return APIResponse.builder().status(HttpStatus.OK).data(repository.save(book)).build();
	}

	@Override
	public APIResponse updateBook(long id, Book book) {

		Optional<Book> b = repository.findById(id);

		if (b.isEmpty()) {
			return APIResponse.builder().status(HttpStatus.BAD_REQUEST).error("Given Id is not exist!").build();
		}

		if (book.getAuthor() != null && book.getAuthor().length() > 0) {
			b.get().setAuthor(book.getAuthor());
		}
		if (book.getEdition() != null && book.getEdition().length() > 0) {
			b.get().setEdition(book.getEdition());
		}
		if (book.getPrice() > 0) {
			b.get().setPrice(book.getPrice());
		}
		if (book.getYearWritten() > 0) {
			b.get().setYearWritten(book.getYearWritten());
		}

		return APIResponse.builder().status(HttpStatus.OK).data(repository.save(b.get())).build();
	}

	@Override
	public APIResponse deleteBook(long id) {

		Optional<Book> b = repository.findById(id);

		if (b.isEmpty()) {
			return APIResponse.builder().status(HttpStatus.BAD_REQUEST).error("Given Id is not exist!").build();
		}

		String title = b.get().getTitle();
		repository.deleteById(id);

		return APIResponse.builder().status(HttpStatus.OK).data("Deleted Book: " + title).build();
	}

	@Override
	public APIResponse getBook(String title, Set<Integer> years, String edition, Pageable pageable) {
		
		Map<String, Object> output = new HashMap<>();
		Pagination page = null;
		Page<Book> b = null;

		if (title != null && title.length() > 0) {
			b = repository.findAllByTitleIgnoreCaseContaining(title, pageable);
		} else if ((years != null && years.size() > 0) && (edition != null && edition.length() > 0)) {
			b = repository.findAllByYearWrittenInAndEdition(years, edition, pageable);
		} else if (years != null && years.size() > 0) {
			b = repository.findAllByYearWrittenIn(years, pageable);
		} else if (edition != null && edition.length() > 0) {
			b = repository.findAllByEdition(edition, pageable);
		} else {
			b = repository.findAll(pageable);
		}

		if (b != null && b.getContent() != null && b.getContent().size() > 0) {
			page = CommonUtils.createPagination(b);
		}
		
		output.put("records", b.getContent());
		output.put("pagination", page);

		return APIResponse.builder().status(HttpStatus.OK).data(output).build();
	}

	@Override
	public APIResponse getEdition() {
		return APIResponse.builder().status(HttpStatus.OK).data(repository.getEditionList()).build();
	}

}
