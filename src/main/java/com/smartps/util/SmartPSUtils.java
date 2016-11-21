package com.smartps.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class SmartPSUtils {
	public static boolean isNullOrEmpty(String string) {
		return (string==null || string.isEmpty());
	}
	
	public static File saveFile(String path,UploadedFile uploadFile,String fileName) {
		if (SmartPSUtils.isNullOrEmpty(path) || SmartPSUtils.isNullOrEmpty(fileName) ) {
			return null;
		};
		File file = new File(path, fileName);
	    InputStream input = null;
	    OutputStream output = null;
		try {
			input = uploadFile.getInputstream();
			 output = new FileOutputStream(file);
			 IOUtils.copy(input, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        IOUtils.closeQuietly(input);
	        IOUtils.closeQuietly(output);
	    }
	    return file;
	}
	
	public static File saveFile(String path, UploadedFile uploadFile ) throws IOException {
		if (SmartPSUtils.isNullOrEmpty(path)) {
			return null;
		};
		String fileUploadedName = uploadFile.getFileName();
		if (SmartPSUtils.isNullOrEmpty(fileUploadedName)) {
			fileUploadedName = "fileName";
		}
		String filename = fileUploadedName;
		File file = new File(path, filename);
	    InputStream input = uploadFile.getInputstream();
	    OutputStream output = new FileOutputStream(new File(path, filename));
	    try {
	        IOUtils.copy(input, output);
	    } finally {
	        IOUtils.closeQuietly(input);
	        IOUtils.closeQuietly(output);
	    }
	    return file;
	}
	
	public static StreamedContent getFileToDownload(String filePath,String extension) {
		File file = new File(filePath);
		InputStream stream;
		StreamedContent fileToDownload = null;
		try {
			stream = new FileInputStream(file);
			fileToDownload = new DefaultStreamedContent(stream, extension,file.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return fileToDownload;
	}
	
}
