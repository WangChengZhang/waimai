package xyz.zwc.waimai.dao;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.File;

public interface FileMapper extends Mapper<File> {
	public int deleteById(@Param("fileid") int fileid);

	public File selectById(@Param("fileid") int fileid);

	public File selectLast();

}
