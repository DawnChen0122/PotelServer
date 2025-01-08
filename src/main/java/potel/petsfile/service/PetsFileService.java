package potel.petsfile.service;

import java.util.List;

import potel.petsfile.vo.PetsFile;

public interface PetsFileService {
	List<PetsFile> getPetsFile();

	static void addpetsFile(PetsFile newPetsFile) {
	}

	static void deletePet(String petId) {
	}

	static void updatePet(PetsFile updatedPet) {
	}
}
