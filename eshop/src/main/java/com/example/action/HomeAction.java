package com.example.action;

import java.io.File;

import com.example.constant.ConstantName;
import com.example.pojo.entity.User;

/**
 * HomeAction 類別處理首頁顯示、登出和檔案上傳的邏輯。
 */
public class HomeAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    // 用於接收上傳的檔案、檔案類型和檔案名稱
    private File[] upload;
    private String[] uploadContentType;
    private String[] uploadFileName;

    /**
     * 跳轉至首頁，顯示使用者資訊
     * @return SUCCESS 返回成功頁面
     */
    public String goHome() {
        User user = (User) getSession().getAttribute(ConstantName.SESSION_USER);
        if (user != null) {
            return SUCCESS;
        } else {
            // 如果使用者未登入，重定向到登入頁
            return "login"; 
        }
    }

    /**
     * 登出功能，清除 session 並重定向至登入頁面
     * @return INPUT 返回登入頁面
     */
    public String logOut() {
        getSession().invalidate(); // 無效化 session
        return "login"; // 返回登入頁面
    }

    public String keepAlive() {
        // 請求到達此處，Servlet 容器已自動更新 Session 時間
        return SUCCESS;
    }

    // Getter 和 Setter 方法

    public File[] getUpload() {
        return upload;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
