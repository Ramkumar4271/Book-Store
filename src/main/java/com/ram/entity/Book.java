package com.ram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Please provide title!")
	private String title;
	
	@NotBlank(message = "Please provide author name!")
	@Column(nullable = false)
	private String author;
	
	@Column(name="year_of_publish",nullable = false)
	@Min(value = 1500, message = "Please provide valid year")
	private int yearWritten;
	
	@NotBlank(message = "Please provide edition!")
	@Column(nullable = false)
	private String edition;
	
	@Column(nullable = false)
	@Min(value = 1, message = "Please provide valid price")
	private double price;

}
