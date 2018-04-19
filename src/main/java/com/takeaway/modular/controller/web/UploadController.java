package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.commons.utils.FileUtils;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.model.Managers;

/**
 * 上传管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/upload")
@Api(value = "上传管理", description = "UploadController")
public class UploadController {
	private static final Logger log = Logger.getLogger(UploadController.class);

	public static Properties prop = new Properties();
	private static boolean isWin = false;

	static {
		InputStream inputStream = UploadController.class
				.getResourceAsStream("/project.properties");
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			log.error("加载project.properties失败", e);
		}

		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		boolean condition=(os != null && (os.startsWith("win") || os.startsWith("Win")));
		if (condition) {
			isWin = true;
		}
	}

	public static String resourcePath() {
		if (isWin) {
			return prop.getProperty("resource.path");
		} else {
			return prop.getProperty("resource.linux.path");
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "上传", httpMethod = "POST", notes = "上传文件")
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public Object uploadfile(
			@ApiParam(name = "file", value = "上传的文件", required = false) MultipartFile file,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Map<String, String> map = new HashMap<String, String>();
		JSONObject result = new JSONObject();

		String upload_dir = resourcePath();// 上传路径

		try {
			if (file != null) {

				String name = file.getOriginalFilename();
				String prefix = checkFile(name);
				// 生成目标文件
				ServletContext context = request.getSession()
						.getServletContext();
				final String absolutePath = FileUtils.getAbsolutePath(context,
						upload_dir);
				final String upath = FileUtils.normalizePath(absolutePath);
				String path = makeFile(upath, "", prefix);
				File dest = new File(upath + path);
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(
						dest));

				map.put("status", "0");// 状态
				map.put("path", dest.getPath());// 文件绝对路径
			}
		} catch (Exception e) {
			map.put("status", "1");
			map.put("path", "");
		}

		result.put("path", map.get("path"));
		return result.toString();
	}

	private String checkFile(String name) {
		final int idx = name.lastIndexOf(".");
		String prefix = "";
		if (idx != -1) {
			prefix = name.substring(idx + 1);
		}
		return prefix;
	}

	public static String makeFile(String root, String dir, String prefix)
			throws IOException {
		final String separator = "/";
		StringBuffer path = new StringBuffer();

		// 生成日期目录
		String date = DateUtil.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sb = new StringBuilder();
		sb.append("/" + date);

		path.append(separator);
		dir = StringUtils.trimToEmpty(dir);
		if (StringUtils.isNotBlank(dir)) {
			path.append(dir);
		}
		path.append(sb);
		path.append(separator);

		FileUtils.createDirectoryIfNecessary(root + path.toString());
		// end
		// 生成文件名
		path.append(separator);
		String fileName = FileUtils.getRandomFileName(prefix);

		FileUtils.createDirectoryIfNecessary(root + path.toString());
		String ret = (path + fileName).replaceAll("//", separator);
		return ret;
	}
}
