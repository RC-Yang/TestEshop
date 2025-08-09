package com.example.action;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.example.TestPasswordUtil;
import com.example.constant.ConstantName;

public class RegisterAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private User user;

    @Autowired
    private UserService userService;
    
    @Autowired
    private TestPasswordUtil testPasswordUtil;

    /**
     * 使用模型驅動來封裝 User 物件
     * @return 返回 User 物件
     */
    @Override
    public User getModel() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    /**
     * 跳轉至註冊頁面
     * @return 返回註冊頁面
     */
    public String register() {
        return SUCCESS;
    }
    
    /**
     * 處理註冊邏輯
     * @return 註冊成功返回 SUCCESS，失敗返回 INPUT
     */
    public String save() {
        // 註冊前的資料檢查
        if (!doCheck(user)) {
            return INPUT;
        }

        try {
            // 設置註冊時間
            setUserCreateDate(user);
            HttpServletRequest request = ServletActionContext.getRequest();
            
            user.setLoginId(request.getParameter("loginId"));
            user.setName(request.getParameter("loginId"));
            //給密碼hash+加鹽
            user.setPassword(testPasswordUtil.hash(request.getParameter("password")));
            
            user.setUserType(Integer.parseInt(request.getParameter("userType")));

            // 呼叫 UserService 來保存使用者資料
            userService.addUser(user);

            // 註冊成功，將訊息設置到 session
            getSession().setAttribute(ConstantName.SESSION_USER_MSG, "註冊成功，請登入");
            
            getSession().setAttribute("registerResult", "註冊成功，請登入");
            //這裡不可用request，因為redirect會導致request重新產生，進而使原本request的資料丟失
        } catch (Exception e) {
            // 註冊失敗，將錯誤訊息設置到 session
            getSession().setAttribute(ConstantName.SESSION_USER_MSG, "註冊失敗，請重試");
            getSession().setAttribute("registerResult", "註冊失敗，請重試");
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * 註冊前資料檢查，這裡可以擴展更多檢查邏輯
     * @param user 註冊的使用者資料
     * @return 若檢查通過返回 true，否則返回 false
     */
    private boolean doCheck(User user) {
        // 檢查帳號、密碼格式、是否已經存在等邏輯可以放在這裡
        // 比如，可以在這裡加上檢查帳號是否已經被註冊
        return user != null && user.getLoginId() != null && !user.getLoginId().isEmpty();
    }

    /**
     * 設置使用者的創建日期
     * @param user 使用者資料
     */
    private void setUserCreateDate(User user) {
        user.setCreateDate(new Date());
    }

    // Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public TestPasswordUtil getTestPasswordUtil() {
		return testPasswordUtil;
	}

	public void setTestPasswordUtil(TestPasswordUtil testPasswordUtil) {
		this.testPasswordUtil = testPasswordUtil;
	}
}
