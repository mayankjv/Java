package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.String;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.DirectoryFile;
import model.FileStatistics;

@RestController
public class TestController{

	
//	TestService testService;
	FileStatistics fileStatistics;
	
	@RequestMapping("/all")
	public ArrayList<DirectoryFile> getTestClass(@RequestParam(value="path") String path) {

		fileStatistics = new FileStatistics(path);		
		try {
			fileStatistics.execute();
		} catch (IOException | InterruptedException e) {
			System.out.println("Exception");
		}
		return fileStatistics.getResult();
	}
}
