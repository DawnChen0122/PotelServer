package potel.petsfile.service.impl;

import java.util.List;

import potel.petsfile.dao.PetsFileDao;
import potel.petsfile.dao.impl.PetsFileDaoImpl;
import potel.petsfile.service.PetsFileService;
import potel.petsfile.vo.PetsFile;

public class PetsFileServiceImpl implements PetsFileService {
	
	private PetsFileDao petsFileDao;
	
	public  PetsFileServiceImpl() {
		this.petsFileDao = new PetsFileDaoImpl();
	}

	@Override
	public List<PetsFile> getPetsFile() {
		System.out.println("PetsFile service get petsFile");
		 List<PetsFile> petsFiles = petsFileDao.selectAllFiles();
		    System.out.println("Retrieved petsFiles: " + petsFiles.size()); // 打印返回的論壇數量
		    return petsFiles;
	}
}