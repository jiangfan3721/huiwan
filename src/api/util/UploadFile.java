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
import bll.HttpUtil;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/api/util/uploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static int MAX_FILE_SIZE = 50 * 1024;
	private static int MAX_MEM_SIZE = 4 * 1024;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
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
		fileFactory.setRepository(new File(this.getServletContext().getRealPath("/") + "images/temp/"));
		
		ServletFileUpload upload = new ServletFileUpload(fileFactory);
		upload.setSizeMax(MAX_FILE_SIZE);
				
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			
			while(iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					// Get upload parameters
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
										
					// Write file
					String filePath = this.getServletContext().getRealPath("/") + "images/data/";
					System.out.println(filePath);
					File file = new File(filePath + fileName);
					item.write(file);					
				}
			}
		} catch (Exception e) {
			System.out.println("Error while writing file.");
			System.err.println(e);
			HttpUtil.errorRespond(response, RetCode.BAD_REQUEST, ErrMsg.UPLOAD_FILE_ERROR);
			return;
		}
		
		HttpUtil.normalRespond(response, RetCode.SUCCESS, null);
	}

}
