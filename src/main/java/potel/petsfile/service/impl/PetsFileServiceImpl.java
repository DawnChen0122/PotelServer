package potel.petsfile.service.impl;

import java.util.List;

import potel.petsfile.dao.PetsFileDao;
import potel.petsfile.dao.impl.PetsFileDaoImpl;
import potel.petsfile.service.PetsFileService;
import potel.petsfile.vo.PetsFile;

public class PetsFileServiceImpl implements PetsFileService {

	private PetsFileDao petsFileDao;

	public PetsFileServiceImpl() {
		this.petsFileDao = new PetsFileDaoImpl();
	}

	@Override
	public List<PetsFile> getPetsFile() {
		System.out.println("PetsFile service get petsFile");
		List<PetsFile> petsFiles = petsFileDao.getPetsFile();
		System.out.println("Retrieved petsFiles: " + petsFiles.size()); // 打印返回的論壇數量
		return petsFiles;
	}
	  @Override
	    public PetsFile selectFileById(int id) {
	        System.out.println("PetsFile service selectFileById with id: " + id);
	        PetsFile petsFile = petsFileDao.selectFileById(id);
	        if (petsFile != null) {
	            System.out.println("Retrieved PetsFile: " + petsFile);
	        } else {
	            System.out.println("No PetsFile found with id: " + id);
	        }
	        return petsFile;
	    }

	    // 新增一個新的 PetsFile
	    @Override
	    public boolean insertFile(PetsFile petsFile) {
	        System.out.println("PetsFile service insertFile");
	        boolean isSuccess = petsFileDao.insertFile(petsFile);
	        if (isSuccess) {
	            System.out.println("PetsFile inserted successfully: " + petsFile);
	        } else {
	            System.out.println("Failed to insert PetsFile: " + petsFile);
	        }
	        return isSuccess;
	    }

	    // 更新現有的 PetsFile
	    @Override
	    public boolean updateFile(PetsFile petsFile) {
	        System.out.println("PetsFile service updateFile");
	        boolean isSuccess = petsFileDao.updateFile(petsFile);
	        if (isSuccess) {
	            System.out.println("PetsFile updated successfully: " + petsFile);
	        } else {
	            System.out.println("Failed to update PetsFile: " + petsFile);
	        }
	        return isSuccess;
	    }

	    // 根據 ID 刪除指定的 PetsFile
	    @Override
	    public boolean deleteFile(int id) {
	        System.out.println("PetsFile service deleteFile with id: " + id);
	        boolean isSuccess = petsFileDao.deleteFile(id);
	        if (isSuccess) {
	            System.out.println("PetsFile with id " + id + " deleted successfully");
	        } else {
	            System.out.println("Failed to delete PetsFile with id: " + id);
	        }
	        return isSuccess;
	    }
	}