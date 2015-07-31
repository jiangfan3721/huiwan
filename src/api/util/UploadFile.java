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
	
	private String IMAGE_TEMP_PATH_IN_SERVER;
	private String IMAGE_DATA_PATH_IN_SERVER;
	private String IMAGE_TEMP_PATH_IN_LOCAL;
	private String IMAGE_DATA_PATH_IN_LOCAL;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
    	this.IMAGE_TEMP_PATH_IN_SERVER =
    			this.getServletContext().getRealPath("/") + "data/temp/";
    	this.IMAGE_DATA_PATH_IN_SERVER =
    			this.getServletContext().getRealPath("/") + "data/picture/";
    	this.IMAGE_TEMP_PATH_IN_LOCAL = 
    			"/Users/Buffer/Documents/Code/eclipseEE workspace/huiwan/WebContent/data/temp/";
    	this.IMAGE_DATA_PATH_IN_LOCAL = 
    			"/Users/Buffer/Documents/Code/eclipseEE workspace/huiwan/WebContent/data/picture/";
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
				new File(this.IMAGE_TEMP_PATH_IN_SERVER));
		
		ServletFileUpload upload = new ServletFileUpload(fileFactory);
		upload.setSizeMax(MAX_FILE_SIZE);
			
		UploadInfoList uploadInfoList = new UploadInfoList();
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			
			while(iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				System.out.println(item.getFieldName());
				if (!item.isFormField()) {
					// Get upload parameters
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
										
					// Write file
					File file = new File(this.IMAGE_DATA_PATH_IN_SERVER + fileName);
					item.write(file);				
					
					// Calculate MD5 according to fileName, time and user info
					String md5 = BizUtil.calcMd5(fileName, uid, siteId);
					uploadInfoList.addUploadInfo(fileName, md5);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while writing file.");
			System.err.println(e);
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
			return;
		}
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, uploadInfoList);
	}

}
