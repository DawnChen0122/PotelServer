package potel.petsfile.dao;

import java.util.List;

import potel.petsfile.vo.Cat;
import potel.petsfile.vo.Dog;

public interface PetsFileDao {
    List<Dog> selectDog();
    
    List<Cat> selectCat();

    void addDog(String dogOwner, String dogName, String dogBreed, String dogGender, int dogImages);
    
    void addCat(String catOwner, String catName, String catBreed, String catGender, int catImages);

    boolean deleteDog(int dogId);

    boolean deleteCat(int catId);

    void updateDog(int dogId, String dogOwner, String dogName, String dogBreed, String dogGender, int dogImages);
    
    void updateCat(int catId, String catOwner, String catName, String catBreed, String catGender, int catImages);
}

