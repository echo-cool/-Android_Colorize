package com.echo.colorizeit.ImageUtil;

public class rcImage {
    private String imgName;
    private String imgBase64Data;
    private String imgPath;

    public rcImage(String imgName, String path) {
        this.imgName = imgName;
        this.imgPath = path;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgBase64Data() {
        return imgBase64Data;
    }

    public void setImgBase64Data(String imgBase64Data) {
        this.imgBase64Data = imgBase64Data;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}