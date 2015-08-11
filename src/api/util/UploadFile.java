package api.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.security.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

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
	
	private static int MAX_FILE_SIZE = 100 * 1024;
	private static int MAX_MEM_SIZE = 50 * 1024;
	
	private String ROOT_IN_SERVER;
	private String ROOT_IN_LOCAL;
	private String TEMP_PATH = "data/temp/";
	private String DATA_PATH = "data/picture/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
    	this.ROOT_IN_LOCAL = "/Users/Buffer/Documents/Code/eclipseEE workspace/huiwan/WebContent/";
    	this.ROOT_IN_SERVER = this.getServletContext().getRealPath("/");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, 
					ErrMsg.NO_FILE_TO_UPLOAD);
		}
		
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		fileFactory.setSizeThreshold(MAX_MEM_SIZE);
		fileFactory.setRepository(
				new File(this.ROOT_IN_SERVER + this.TEMP_PATH));
		
		ServletFileUpload upload = new ServletFileUpload(fileFactory);
		upload.setSizeMax(MAX_FILE_SIZE);
			
		long uid = -1, siteId = -1;
		String type = "";
		FileItem fileItem = null;
		UploadInfoList uploadInfoList = new UploadInfoList();
		try {
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
					type = item.getString();
				} else if (item.getFieldName().equals("minisiteId")) {
					
				}
			}
		} catch (Exception e) {
			System.out.println("Error while parsing request.");
			System.err.println(e);
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
			return;
		}
		
		if (fileItem == null ||uid == -1 || siteId == -1 || (!type.equals("site") && !type.equals("minisite"))) {
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
		}
		
		// Get upload parameters
		String fieldName = fileItem.getFieldName();
		String fileName = fileItem.getName();
		String contentType = fileItem.getContentType();
		boolean isInMemory = fileItem.isInMemory();
		long sizeInBytes = fileItem.getSize();
							
		try {
			File dir = new File(this.ROOT_IN_SERVER + this.DATA_PATH + "id_" + siteId);
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdir();
			}
			
			// Calculate MD5 according to fileName, time and user info
			String md5 = BizUtil.calcMd5(fileName, uid, siteId);
			
			// Write file
			File file = new File(dir+ "/" + md5 + "." + BizUtil.getExtensionName(fileName));
			fileItem.write(file);					
			
			uploadInfoList.addUploadInfo(fileName, this.DATA_PATH + "id_" + siteId + "/" + file.getName());
		} catch (Exception e) {
			System.out.println("Error while writing file.");
			System.err.println(e);
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
			return;
		}
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, uploadInfoList);
	}

}
