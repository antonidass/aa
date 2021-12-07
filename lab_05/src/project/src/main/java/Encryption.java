public class Encryption {
    public static String caesar(String s)  {

        StringBuilder s_array = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (s_array.charAt(i) == 'z') {
                s_array.setCharAt(i, 'a');
            } else if (s_array.charAt(i) == 'Z') {
                s_array.setCharAt(i, 'A');
            } else {
                s_array.setCharAt(i, (char) (s_array.charAt(i) + 1));
            }
        }
        return s_array.toString();
    }

    public static String fromLowerToUpper(String s) {
        StringBuilder s_array = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s_array.charAt(i))) {
                s_array.setCharAt(i, Character.toUpperCase(s_array.charAt(i)));
            } else {
                s_array.setCharAt(i, Character.toLowerCase(s_array.charAt(i)));
            }
        }

        return s_array.toString();
    }

    public static String reverse(String s) {

        int shift = s.length() / 2;
        StringBuilder s_array = new StringBuilder(s);

        for (int i = 0; i < s.length() / 2; i++) {
            char st = s_array.charAt(i);
            s_array.setCharAt(i, s_array.charAt(i + shift));
            s_array.setCharAt(i + shift, st);
        }

        return s_array.toString();
    }
}
