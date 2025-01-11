package potel.petsfile.service;

import java.util.List;

import potel.petsfile.vo.PetsFile;

public interface PetsFileService {
	List<PetsFile> getPetsFile();
	PetsFile selectFileById(int id);
	boolean insertFile(PetsFile petsFile);
	boolean updateFile(PetsFile petsFile);
	boolean deleteFile(int id);

}
