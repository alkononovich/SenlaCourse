package task1;

public class Sorter {
    public static String[] sortDown (String[] list) {
        for (int j = 0; j < list.length - 1; j++) {
            for (int i = j + 1; i < list.length; i++) {
                if (list[j].length() < list[i].length()) {
                    String t = list[j];
                    list[j] = list[i];
                    list[i] = t;
                }
            }
        }
      return list;
    }
    
    public static String[] sortUp (String[] list) {
        for (int j = 0; j < list.length - 1; j++) {
            for (int i = j + 1; i < list.length; i++) {
                if (list[j].length() > list[i].length()) {
                    String t = list[j];
                    list[j] = list[i];
                    list[i] = t;
                }
            }
        }
      return list;
    }
    
    public static String maxLength (String[] list) {
    	String t = "";
        for (int j = 0; j < list.length; j++) {
            if (list[j].length() > t.length()) 
            	t = list[j];
        }
      return t;
    }
}
