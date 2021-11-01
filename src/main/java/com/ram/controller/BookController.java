package com.ram.controller;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.entity.Book;
import com.ram.model.APIResponse;
import com.ram.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService service;
	
	@PostMapping("/books")
	public APIResponse addBook(@Valid @RequestBody Book book) {
		return service.createBook(book);
	}
	
	@PutMapping("/books/{id}")
	public APIResponse modifyBookDetails(@PathVariable @Min(value = 1, message = "Please provide valid ID") long id, @RequestBody Book book) {
		return service.updateBook(id, book);
	}
	
	@DeleteMapping("/books/{id}")
	public APIResponse deleteExitingBook(@PathVariable @Min(value = 1, message = "Please provide valid ID") long id) {
		return service.deleteBook(id);
	}
	
	@GetMapping("/books")
	public APIResponse getBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) Set<Integer> years,
			@RequestParam(required = false) String edition,
			@SortDefault(sort = "title", direction = Direction.ASC) Pageable pageable) {
		return service.getBook(title, years, edition, pageable);
	}
	
	@GetMapping("/books/editions")
	public APIResponse getEditions() {
		return service.getEdition();
	}

}
