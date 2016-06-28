package com.movieapp.wrappers;

import java.util.List;

import com.movieapp.beans.Category;

public class CategoryWrapper {
	private Category category;
	private List<Category> categories;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		return "CategoryWrapper [category=" + category + ", categories=" + categories + "]";
	}
	
	
}
