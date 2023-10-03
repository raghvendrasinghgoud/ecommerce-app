package com.nagarro.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private int quantity;
	private int size;
	private String image;
	
	/**
	 * @param id
	 * @param title
	 * @param quantity
	 * @param size
	 * @param image
	 */
	
	public Product(int id, String title, int quantity, int size, String image) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.size = size;
		this.image = image;
	}
	
	/**
	 * 
	 */
	public Product() {
		super();
	}

	/**
	 * @param title
	 * @param quantity
	 * @param size
	 * @param image
	 */
	public Product(String title, int quantity, int size, String image) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.size = size;
		this.image = image;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", quantity=" + quantity + ", size=" + size + ", image="
				+ image + "]";
	}
	
	
	
}
