package util;

/**
 * Created by liaoli
 * date: 2018/7/18
 * time: 21:38
 */
public class printUtil {
    public static void printArray(int[]arr){
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for (int i : arr) {
            sb.append(i);
            sb.append(",");
        }
        sb.append("]");
        if(sb.length()>2){
            sb.deleteCharAt(sb.length()-2);
        }
        System.out.println(sb.toString());
    }
}
