package com.nagarro.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.Part;

public class ImageUtility {
	

	public String genrateFileName(String submittedFileName) {
		String[] name=submittedFileName.split("\\.");
		return name[0]+new Date().getMinutes()+"."+name[name.length-1];
	}
	
	public boolean saveImageToDir(Part image,String path) {
		try (
		InputStream ir=image.getInputStream();
		
		FileOutputStream fos=new FileOutputStream(path)){
			
			byte[] imageData=new byte[ir.available()];
			ir.read(imageData);
			
			fos.write(imageData);
			
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteImageFromDir(String path) {
		
		File f=new File(path);
		return f.delete();
	}

}
