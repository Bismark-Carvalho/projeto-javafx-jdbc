package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll() {
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "books" ));
		list.add(new Department(2, "cook" ));
		list.add(new Department(3, "eletronic" ));
		list.add(new Department(4, "ti" ));
		list.add(new Department(5, "rh" ));
		
		
		return list;
	}
	
	
}
