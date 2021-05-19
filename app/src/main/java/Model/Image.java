package Model;

import androidx.annotation.NonNull;

import java.io.File;

public class Image {

    public Image() {

    }

    public Image(String name, File imageFile) {
        this.name = name;
        this.imageFile = imageFile;
    }
    private Long id;
    private String name;
    private File imageFile;

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "the name of this file is"+ getName();
    }
}
