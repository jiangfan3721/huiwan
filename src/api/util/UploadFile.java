package api.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import api.ret.obj.ErrMsg;
import api.ret.obj.RetCode;
import api.ret.obj.UploadInfoList;
import bll.BizUtil;
import bll.HttpUtil;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/api/util/uploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static enum FileType {
		SITE, MINISITE, ACCOUNT, BACKGROUND
	}
	
	private static int MAX_FILE_SIZE = 100 * 1024;
	private static int MAX_MEM_SIZE = 50 * 1024;
	
	private String ROOT;
	private static String TEMP_PATH = "data/temp/";
	private static String SITE_PATH = "data/site/";
	private static String MINISITE_PATH = "data/minisite/";
	private static String ACCOUNT_PATH = "data/account/";
	private static String BACKGROUND_PATH = "data/background/";
	
	private long uid;
	private long siteId;
	private long minisiteId;
	private FileType type;
	private FileItem fileItem;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String getRoot() {
    	//return "/Users/Buffer/Documents/Code/eclipseEE workspace/huiwan/WebContent/";
    	return this.getServletContext().getRealPath("/");
    }
    
    @Override
    public void init() {
    	this.ROOT = this.getRoot();
    }

    private void initParas() {
    	
    	this.uid = -1;
    	this.siteId = -1;
    	this.minisiteId = -1;
    	this.type = null;
    	this.fileItem = null;
    }
    
    private void parseFileParas(HttpServletRequest request) throws Exception{
    	
    	this.initParas();
    	
    	DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		fileFactory.setSizeThreshold(MAX_MEM_SIZE);
		fileFactory.setRepository(
				new File(this.ROOT + UploadFile.TEMP_PATH));
		
		ServletFileUpload upload = new ServletFileUpload(fileFactory);
		upload.setSizeMax(MAX_FILE_SIZE);
			
		List<FileItem> fileItems = upload.parseRequest(request);
		Iterator<FileItem> iter = fileItems.iterator();
		
		while(iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				fileItem = item;
			} else if (item.getFieldName().equals("uid")){
				uid = Long.parseLong(item.getString());
			} else if (item.getFieldName().equals("siteId")) {
				siteId = Long.parseLong(item.getString());
			} else if (item.getFieldName().equals("type")) {
				type = FileType.valueOf(item.getString().toUpperCase());
			} else if (item.getFieldName().equals("minisiteId")) {
				minisiteId = Long.parseLong(item.getString());
			}
		}
    }
        
    private String getFilePath() throws Exception {
    	
    	String path = null;
    	switch(type) {
    	case SITE:
    		path = UploadFile.SITE_PATH + "id_" + siteId + "/";
    		break;
    	case MINISITE:
    		path = UploadFile.MINISITE_PATH + "id_" + minisiteId + "/";
    		break;
    	case ACCOUNT:
    		path = UploadFile.ACCOUNT_PATH;
    		break;
    	case BACKGROUND:
    		path = UploadFile.BACKGROUND_PATH;
    		break;
    	}
    	
    	return path;
    }
    
    private String getFileName() throws Exception{
    	
    	// Calculate MD5 according to fileName, time and user info
    	String md5 = BizUtil.calcMd5(fileItem.getName(), uid, siteId);
    	
    	String fileName = null;
    	switch(type) {
    	case SITE:
    	case MINISITE:
    		fileName = md5 + "." + BizUtil.getExtensionName(fileItem.getName());
    		break;
    	case ACCOUNT:
    	case BACKGROUND:
    		fileName = "id_" + uid + "." + BizUtil.getExtensionName(fileItem.getName());
    		break;
    	}
    	
    	return fileName;
    }
    
    private boolean checkParas() {
    	
    	if (fileItem == null || type == null || uid == -1) {
    		return false;
    	}
    	
    	switch(type) {
    	case SITE:
    		if (siteId == -1) return false;
    		break;
    	case MINISITE:
    		if (minisiteId == -1) return false;
    		break;
		default:
			break;
    	}
    	
    	return true;
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// check whether the request is multipart
		if (!isMultipart) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.NO_FILE_TO_UPLOAD);
		}
		
		// parse parameters in the request, return bad request if failed
		try {
			parseFileParas(request);
		} catch (Exception e) {
			System.out.println("Error while parsing request.");
			System.err.println(e);
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
			return;
		}
	
		UploadInfoList uploadInfoList = new UploadInfoList();
		if (!this.checkParas()) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
		}
		
		// Get upload parameters
		String fieldName = fileItem.getFieldName();
		String fileName = fileItem.getName();
		String contentType = fileItem.getContentType();
		boolean isInMemory = fileItem.isInMemory();
		long sizeInBytes = fileItem.getSize();
							
		try {
			File dir = new File(this.ROOT + this.getFilePath());
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdir();
			}		
			
			// Write file
			File file = new File(dir.getAbsolutePath() + "/" + this.getFileName());
			fileItem.write(file);					
			
			uploadInfoList.addUploadInfo(fileName, this.getFilePath() + this.getFileName());
		} catch (Exception e) {
			System.out.println("Error while writing file.");
			System.err.println(e);
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
			return;
		}
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, uploadInfoList);
	}

}
