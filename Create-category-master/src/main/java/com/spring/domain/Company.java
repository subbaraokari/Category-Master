package com.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer	id;
	@Column(name="company_name")
	String companyName;
	//User user;
	
	@OneToMany(mappedBy="company",fetch = FetchType.EAGER)
	List<Brand> brands;
	
	public Company() {}
	public Company(Integer id, String companyName) {
		super();
		this.id = id;
		this.companyName = companyName;
		//this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	public void add(Brand brand) {
		if(brand!=null)
		{
			brands=new ArrayList<Brand>();
		}
		brands.add(brand);
		brand.setCompany(this);
		
	}
}
