package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.commons.utils.FileUtils;


/**
 * 文件下载
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/download")
@Api(value = "下载管理", description = "DownloadController")
public class DownloadController {
	private static final Logger log = Logger
			.getLogger(DownloadController.class);

	@ApiOperation(value = "下载", httpMethod = "GET", notes = "上传数据管理的模板文件")
	@ApiImplicitParams({ @ApiImplicitParam(name = "filepath", value = "模板文件相对路径", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/downloadfile")
	public void img(String filepath, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// real file name on disk
		String realFileName = DownloadController.class.getResource(
				"/template/" + filepath).getPath();
		if (!FileUtils.isFileExists(realFileName)) {
			log.error(realFileName + " does not exist.");
			return;
		}

		// if file exists, then open it and read the content,send back to browse
		String shortFileName = FilenameUtils.getName(realFileName);

		// get ie version
		boolean isIe6 = (request.getHeader("User-Agent").indexOf("MSIE 6.0") > 0);
		if (isIe6) {
			response.setHeader("Content-Disposition", "filename="
					+ shortFileName);
		} else {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ shortFileName);
		}

		// open file and read its content
		File file = new File(realFileName);
		if (file.exists() && file.isFile()) {
			ServletOutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[40960];
			int size = 0;
			while ((size = fis.read(buffer)) != -1) {
				os.write(buffer, 0, size);
			}
			fis.close();
			os.flush();
			os.close();
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	}

}
