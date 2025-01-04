package potel.petsfile.dao;

import java.util.List;

import potel.petsfile.vo.PetsFile;

public interface PetsFileDao {
	List<PetsFile> selectAllFiles();
	// 根据ID查询宠物文件
    PetsFile selectFileById(int id);

    // 插入新的宠物文件
    boolean insertFile(PetsFile petsFile);

    // 更新宠物文件信息
    boolean updateFile(PetsFile petsFile);

    // 删除宠物文件
    boolean deleteFile(int id);
}
