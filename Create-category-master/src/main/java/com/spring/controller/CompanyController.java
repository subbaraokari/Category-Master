package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dao.BrandDAO;
import com.spring.dao.CompanyDAO;
import com.spring.domain.Brand;
import com.spring.domain.Company;

@Controller

public class CompanyController {
	
	@Autowired
	CompanyDAO cdao;
	@Autowired
	BrandDAO branddao;
	@RequestMapping("/")
	public String showHome() {
		return "index";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String saveCompany(@RequestParam("companyName") String companyName,@RequestParam("brandName") String brandName,Model model) {
		
		Company company=new Company();
		company.setCompanyName(companyName);
		Brand brand=new Brand();
		brand.setBrandName(brandName);
		company.add(brand);
		brand.setCompany(company);
		cdao.saveCompany(company);
		branddao.saveBrand(brand);
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String listCompaines(Model model) {
		List<Company> companies=cdao.listCompanies();
		for(Company company:companies)
		{
			List<Brand> brands=company.getBrands();
			for(Brand brand:brands)
			{
				System.out.println(company.getCompanyName());
				System.out.println(brand.getBrandName());
			}
		}
		
		model.addAttribute("companies",companies);
		return "home";
	}
	
}
