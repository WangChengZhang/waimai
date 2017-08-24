package xyz.zwc.waimai.service;

import javax.servlet.http.HttpServletRequest;

import xyz.zwc.waimai.entity.File;

public interface FileService {
	public int deleteById(int fileid);

	public File selectById(int fileid);

	/**
	 * 
	 * @param request
	 * @param maxsize
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public int insert(HttpServletRequest request, int maxsize, File file) throws Exception;

	public File selectLast();

	public int insertone(File file);
}
