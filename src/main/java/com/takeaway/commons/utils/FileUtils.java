/*
 * Copyright (c) 2015, 享券 and/or its affiliates. All rights reserved.
 * 享券 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.takeaway.commons.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 *对文件的一些处理，比如说判断文件大小，判断图片大小等等
 * @author Administrator
 * @date 2015-5-7
 */
public class FileUtils {
	public static Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * 判断图片是否是符合要求宽度
	 * @param file 随机目标文件
	 * @param expectionWidth 期望的宽度
	 * @param expectionHeight 期望的高度
	 * @param forReuse 文件file是否还需重用
	 * @return
	 */
	public static boolean isRightImageSize(File file,int expectionWidth,int expectionHeight,boolean forReuse){
		boolean flag = false;
		try{
			BufferedImage buff = ImageIO.read(file);
			if(expectionWidth == buff.getWidth() && expectionHeight == buff.getHeight()){
				flag = true;				
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			boolean condition=(null != file && (!forReuse || !flag));
			if(condition){
				file.delete();				
			}
		}
		
		return flag;
	}
	/**
	 * 判断是否小于指定的字节
	 * @param file 随机目标文件
	 * @param pointByte 指定最大字节
	 * @param forReuse 文件file是否还需重用
	 * @return
	 */
	public static boolean isLTPointByte(File file,long pointByte,boolean forReuse){
		boolean flag = false;
		try{
			if(pointByte >= file.length()){
				flag = true;				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			boolean condition=(file.exists() && (!forReuse || !flag));
			if(condition){
				file.delete();				
			}
		}
		return flag;
	}
	/**
	 * 从上传接口中读取数据变成文件
	 * @param multipartFile
	 * @param path
	 * @return
	 */
	public static File multipartFileToFile(MultipartFile multipartFile,String path)throws Exception{
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		try{
			is = multipartFile.getInputStream();
			 
	        String suffix = multipartFile.getOriginalFilename().substring
	                        (multipartFile.getOriginalFilename().lastIndexOf("."));         
	        String imageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
	        String fileName = path + File.separator   + imageName;
	        
	        file = new File(fileName);
	        if(!file.exists()){
	        	file.createNewFile();	        	
	        }
	        os = new FileOutputStream(file);
	        
	        byte[] buff = new byte[1024];
	        int len = 0;
	        while((len = is.read(buff, 0, buff.length))>0){
	        	os.write(buff, 0, len);	        	
	        }
		}catch(Exception e){
			throw new Exception("上传文件读取异常",e);
		}finally{
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return file;
	}

	/**
	 * Return absolute path of specified path, which relatives to the root path
	 * of web application.
	 *
	 * @param request
	 * @param relativePath
	 * @return
	 */
	public static String getAbsolutePath(HttpServletRequest request, String relativePath) {
		return getAbsolutePath(request.getSession().getServletContext(), relativePath);
	}

	/**
	 * Return absolute path of specified path, which relatives to the root path
	 * of web application.
	 *
	 * @param request
	 * @param relativePath
	 * @return
	 */
	public static String getAbsolutePath(ServletContext context, String relativePath) {
		String prefix = getAbsolutePathPrefix(context, relativePath);
		return normalizePath(prefix + (StringUtils.isBlank(relativePath) ? "" : relativePath));
	}

	public static String getAbsolutePathPrefix(HttpServletRequest request, String relativePath) {
		return getAbsolutePathPrefix(request.getSession().getServletContext(), relativePath);
	}

	public static String getAbsolutePathPrefix(ServletContext context, String relativePath) {
		// it's an abstract path already, so return it directly
		if ((relativePath.indexOf(":") >= 0) || (relativePath.startsWith("/"))) {
			return "";
		}

		return normalizePath(context.getRealPath("/"));
	}

	/**
	 * Convert \ as / and remove the repeated /, add / at the end of path
	 *
	 * @param path
	 * @return
	 */
	public static String normalizePath(String path) {
		if (StringUtils.isBlank(path)) {
			return path;
		}
		path = normalizeFilePath(path);
		return (path.endsWith("/") ? path : path + "/");
	}

	public static String normalizeFilePath(String path) {
		if (StringUtils.isBlank(path)) {
			return path;
		}
		path = path.replace("\\", "/").replace("\\", "/").replace("//", "/").replace("//", "/");
		return path;
	}

	/**
	 * Check if the specified dir exists, if it doesn't, then create it.
	 *
	 * @param dir
	 * @throws IOException
	 */
	public static void createDirectoryIfNecessary(String dir) throws IOException {
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	public static void removeFile(String dir) {
		File f = new File(dir);
		if (!f.exists()) {
		} else {
			f.delete();
		}
	}

	/**
	 * Returns a uuid random file name with specified extension.
	 * @param extension
	 * @return
	 */
	public static String getRandomFileName(String extension) {
		extension = StringUtils.isBlank(extension) ? "" : (extension.startsWith(".") ? extension : "." + extension);
		return UUID.randomUUID().toString() + extension;
	}

	/**
	 * Return a file is exists
	 * @param file
	 * @return
	 */
	public static boolean isFileExists(String file) {
		File f = new File(file);
		return f.exists();
	}

	/**
	 * Return if the file is an image
	 * @param file
	 * @return
	 */
	public static boolean isImagefile(String file) {
		String extension = FilenameUtils.getExtension(file).toLowerCase();
		return (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp")
				|| extension.equals("gif") || extension.equals("png"));
	}
	
	
	/** 
     * 复制单个文件 
     *  
     * @param srcFileName 
     *            待复制的文件名 
     * @param descFileName 
     *            目标文件名 
     * @param overlay 
     *            如果目标文件存在，是否覆盖 
     * @return 如果复制成功返回true，否则返回false 
     */  
    public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {  
            return false;  
        } else if (!srcFile.isFile()) {  
            return false;  
        }  
  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }  
        }  
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null){
                	out.close();  
                }                      
                if (in != null){
                	in.close();  
                }                     
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 复制整个目录的内容 
     *  
     * @param srcDirName 
     *            待复制目录的目录名 
     * @param destDirName 
     *            目标目录名 
     * @param overlay 
     *            如果目标目录存在，是否覆盖 
     * @return 如果复制成功返回true，否则返回false 
     */  
    public static boolean copyDirectory(String srcDirName, String destDirName,  
            boolean overlay) {  
        // 判断源目录是否存在  
        File srcDir = new File(srcDirName);  
        if (!srcDir.exists()) {
            return false;  
        } else if (!srcDir.isDirectory()) {
            return false;  
        }  
  
        // 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        File destDir = new File(destDirName);  
        // 如果目标文件夹存在  
        if (destDir.exists()) {  
            // 如果允许覆盖则删除已存在的目标目录  
            if (overlay) {  
                new File(destDirName).delete();  
            } else {
                return false;  
            }  
        } else {  
            // 创建目的目录  
            System.out.println("目的目录不存在，准备创建。。。");  
            if (!destDir.mkdirs()) {  
                System.out.println("复制目录失败：创建目的目录失败！");  
                return false;  
            }  
        }  
  
        boolean flag = true;  
        File[] files = srcDir.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            // 复制文件  
            if (files[i].isFile()) {  
                flag = copyFile(files[i].getAbsolutePath(),  
                        destDirName + files[i].getName(), overlay);  
                if (!flag){
                	break;  
                }                      
            } else if (files[i].isDirectory()) {  
                flag = copyDirectory(files[i].getAbsolutePath(),  
                        destDirName + files[i].getName(), overlay);  
                if (!flag){
                	break;                  	
                }  
            }  
        }  
        if (!flag) {
            return false;  
        } else {  
            return true;  
        }  
    }  
}
