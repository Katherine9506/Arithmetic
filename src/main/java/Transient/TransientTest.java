package Transient;

import java.io.*;

/**
 * @Author Katherine
 * @Description
 * @Date Created in 10:39 2018/3/28
 * @Modified By Katherine
 */
public class TransientTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("Katherine");
        user.setPassword("123456");
        user.setTestStr("test1");

        System.out.println("read before serializable: ");
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPasswd());
        System.out.println("testStr: " + user.getTestStr());

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("E:/user.txt"));
            os.writeObject(user);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "E:/user.txt"));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();
            User.testStr = "test2";
            System.out.println("\nread after Serializable: ");
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPasswd());
            System.out.println("testStr: " + user.getTestStr());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


class User implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;

    private String username;
    private transient String password;
    public static String testStr;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        User.testStr = testStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

}
