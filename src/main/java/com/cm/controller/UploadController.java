package com.cm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cm.CertificationManagerApplication;

@Controller
@RequestMapping("/upload")
public class UploadController {
	private static final Logger LOGGER = Logger.getLogger(UploadController.class.getName());
	
	

	@RequestMapping(value="/file/ajaxSaveMedia.do",method = RequestMethod.POST)
	public 	@ResponseBody String uploadPRocess(MultipartHttpServletRequest request, HttpServletResponse response) {
		 //LOGGER.info("YOOUHOUUOUOOUUOUUUOU");
		 Iterator<String> itr =  request.getFileNames();
	     MultipartFile mpf = request.getFile(itr.next());
	     String returnFilePath = "";
	     //LOGGER.info(mpf.getOriginalFilename() +" uploaded!");
	     
	     // TODO check if file already exist
	     // TODO if file existe create new name (occurence+1)
	     
	     try {
             //just temporary save file info into ufile
	    	 
	    	 File newFile = new File(CertificationManagerApplication.ROOT + "/" + mpf.getOriginalFilename());
	    	 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
	    	 LOGGER.info(String.format("FilePath : Absolute :%s Canonical : %s", newFile.getAbsolutePath(), newFile.getCanonicalPath()));
	    	// String filePath = newFile.getName();
	    	 returnFilePath = String.format("<img src=\"%s\">", "http://localhost:8080/upload/file/" + mpf.getOriginalFilename());
             FileCopyUtils.copy(mpf.getInputStream(), stream);
			 stream.close();

			
		 } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		 }
	     
	     return returnFilePath;
	}
	
	@RequestMapping(value="/file/{fileName}",method=RequestMethod.GET)
	public void sendUploadedStremedImage( @PathVariable("fileName") String fileName, HttpServletResponse response) {
		LOGGER.info(String.format("Downloading : %s ", fileName));
		try {
			Files.copy(Paths.get(CertificationManagerApplication.ROOT + "/" + fileName + ".jpg"), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info(String.format("Error writing file to output stream. Filename was '{}'", fileName, e));
		    throw new RuntimeException("IOError writing file to output stream");
		}
		
	}
	
}
