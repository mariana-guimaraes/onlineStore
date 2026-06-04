package com.mariana.onlinestore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariana.onlinestore.entities.Category;
import com.mariana.onlinestore.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll(){
		return repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}

	public Category insert(Category obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Category update(Long id, Category obj) {
		Category entity = repository.getReferenceById(id);
		entity.setName(obj.getName());
		return repository.save(entity);
	}
}
