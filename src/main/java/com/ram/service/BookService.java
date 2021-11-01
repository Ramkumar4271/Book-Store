package com.ram.service;

import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.ram.entity.Book;
import com.ram.model.APIResponse;

public interface BookService {

	APIResponse createBook(Book book);

	APIResponse updateBook(long id, Book book);

	APIResponse deleteBook(long id);

	APIResponse getBook(String name, Set<Integer> years, String edition, Pageable pageable);

	APIResponse getEdition();

}
