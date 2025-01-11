package potel.petsfile.service;

import java.util.List;

import potel.petsfile.vo.Cat;
import potel.petsfile.vo.Dog;

public interface PetsFileService {

    // 獲取所有狗的資料
    List<Dog> getDog();

    // 獲取所有貓的資料
    List<Cat> getCat();

    // 新增一隻狗
    boolean addDog(String dogOwner, String dogName, String dogBreed, String dogGender, int dogImages);

    // 新增一隻貓
    boolean addCat(String catOwner, String catName, String catBreed, String catGender, int catImages);

    // 刪除指定狗的資料
    boolean deleteDog(int dogId);

    // 刪除指定貓的資料
    boolean deleteCat(int catId);

    // 更新指定狗的資料
    void updateDog(int dogId, String dogOwner, String dogName, String dogBreed, String dogGender, int dogImages);

    // 更新指定貓的資料
    void updateCat(int catId, String catOwner, String catName, String catBreed, String catGender, int catImages);
}
