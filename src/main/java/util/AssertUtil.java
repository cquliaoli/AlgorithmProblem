package util;

/**
 * Created by liaoli
 * date: 2018/7/22
 * time: 18:22
 */
public class AssertUtil {
    public static boolean assertTrue(int expected,int result){

        System.out.println(String.format("Expected:%s result:%s",expected,result));
        return expected==result;
    }
}
