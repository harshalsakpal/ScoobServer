package edu.cpme273.uniserver.test;


import org.apache.commons.lang.RandomStringUtils;



import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.connection.DatabaseConnection;
import edu.cmpe273.univserver.service.UniversityServerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test {

	/**
	 * @param args
	 */
	DatabaseConnection db;
	 ArrayList<String> coursename ;
	 ArrayList<String> coursedesc;
	 
	 ArrayList<Integer> randlist = new ArrayList<Integer>();
	  
		
		
	public static void main(String[] args) {
		
		
		Test obj = new Test();
		DatabaseConnection.initialize();
		obj.TestAddCourse();
		DatabaseConnection.releasePool();

	}
	
	public ArrayList<String> randomstrings(int count, int size) {
		ArrayList<String> randlist = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			String str = RandomStringUtils.randomAlphabetic(size);
			//System.out.println(str);
				randlist.add(str);
		}
		return randlist;
	}
	
	

	public  void TestAddCourse() {
		UniversityServerService service = new UniversityServerService();
		int noofcourses = 2;
		
		
		
		coursename = randomstrings(noofcourses, 30);
		coursedesc = randomstrings(noofcourses, 30);
		 
		int size = coursename.size();
		 Course c[]= new Course[size];
		long start = System.currentTimeMillis();
		
		 Course cc = new Course();
		 
		 for (int i = 0; i < size; i++) {
					cc.setCourseDesc(coursename.get(i));
					cc.setCourseName(coursename.get(i));
					cc.setSection("1");
					cc.setCredits("3");
					cc.setDepartment("CMPE");
					c[i]=cc;
		 }
		 
		start= System.currentTimeMillis();
		
		for (int i = 0; i < noofcourses; i++) {
			
			System.out.println(service.adminAddCourse(c[i]));	
		}
		
		long stop = System.currentTimeMillis();
		System.out.println(" Time to add " + noofcourses + " courses in using Statement interface"
				+ (stop - start));
		
	}
	
	}
