package com.ram.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ram.entity.Book;
import com.ram.model.APIResponse;

public interface BookRepository extends JpaRepository<Book, Long> {


	Page<Book> findAllByTitleIgnoreCaseContaining(String name, Pageable pageable);
	
	Page<Book> findAllByYearWrittenIn(Set<Integer> years, Pageable pageable);
	
	Page<Book> findAllByEdition(String edition, Pageable pageable);
	
	Page<Book> findAllByYearWrittenInAndEdition(Set<Integer> years, String edition, Pageable pageable);

	String sql_query = "select distinct(edition) from books";
	@Query(nativeQuery = true, value = sql_query)
	List<String> getEditionList();

}
