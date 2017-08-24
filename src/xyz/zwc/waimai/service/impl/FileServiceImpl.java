package xyz.zwc.waimai.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.action.HomeAction;
import xyz.zwc.waimai.dao.FileMapper;
import xyz.zwc.waimai.entity.File;
import xyz.zwc.waimai.service.FileService;
import xyz.zwc.waimai.util.Text;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;

	@Override
	public int deleteById(int fileid) {
		// TODO Auto-generated method stub
		if (fileid == 1) {
			return 1;
		}
		File file = this.selectById(fileid);
		if (file != null) {
			java.io.File realfile = new java.io.File(file.getPath());
			if (realfile.exists() && realfile.isFile()) {
				realfile.delete();
			}
		}
		return fileMapper.deleteById(fileid);
	}

	@Override
	public File selectById(int fileid) {
		// TODO Auto-generated method stub
		return fileMapper.selectById(fileid);
	}

	@Override
	public int insert(HttpServletRequest request, int maxsize, File file) throws Exception {
		// 结果代码0并非表单数据1并非图片2上传成功3大小超出限制4上传失败
		int result = 4;
		// 保存目录
		String savePath = request.getServletContext().getRealPath("/WEB-INF/file");
		// 临时目录
		String tempPath = request.getServletContext().getRealPath("/WEB-INF/temp");
		java.io.File tmpFile = new java.io.File(tempPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓冲区大小
		factory.setSizeThreshold(1024 * 100);
		// 设置临时文件保存目录
		factory.setRepository(tmpFile);
		// 文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		// 判断提交是否上传表单
		if (!ServletFileUpload.isMultipartContent(request)) {
			return result = 0;
		}
		upload.setFileSizeMax(maxsize);
		upload.setSizeMax(maxsize * 2);
		// 解析上传数据
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 判断是否文件,及key是否为image
				if (!item.isFormField() && "image".equals(item.getFieldName())) {
					String filename = item.getName();
					if (filename == null || !Text.isImage(filename)) {
						return result = 1;
					}
					// 设置文件名和文件id
					File lastfile = fileMapper.selectLast();
					if (lastfile == null) {
						file.setFileid(1);
					} else {
						file.setFileid(lastfile.getFileid() + 1);
					}
					// 真实路径与文件名
					String path = makePath(file.getFileid().toString(), savePath) + "/" + file.getFileid() + "."
							+ Text.getSuffix(filename);
					file.setPath(path);
					file.setSize((int) item.getSize());
					file.setType(Text.getSuffix(filename));
					file.setCreateTime(new Timestamp(System.currentTimeMillis()));
					file.setUpdateTime(file.getCreateTime());
					file.setUrl(path);
					// 保存文件
					FileOutputStream out = new FileOutputStream(path);
					byte[] buffer = new byte[1024];
					int len = 0;
					InputStream in = item.getInputStream();
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					if (fileMapper.insert(file) > 0) {
						return result = 2;
					}
				}
			}
		} catch (FileSizeLimitExceededException e) {
			return result = 3;
		} catch (SizeLimitExceededException e) {
			return result = 3;
		} catch (Exception e) {
			Log log = LogFactory.getLog(HomeAction.class);
			e.printStackTrace();
			log.error("头像上传异常", e);
			return result;
		}
		return result;
	}

	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		String dir = savePath + "/" + dir1 + "/" + dir2; // upload\2\3
															// upload\3\5
		// File既可以代表文件也可以代表目录
		java.io.File file = new java.io.File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	@Override
	public File selectLast() {
		// TODO Auto-generated method stub
		return fileMapper.selectLast();
	}

	@Override
	public int insertone(File file) {
		// TODO Auto-generated method stub
		return fileMapper.insert(file);
	}

}
