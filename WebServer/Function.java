package need;

public class Function {
    public String getUrl(String s) {
        if (s.contains("GET") && s.contains("HTTP") && s.contains("favicon.ico") == false) {
            System.out.println(s + "loplkp");
            String valiny[] = s.split("/");
            if (s.split("/")[1].strip().split(" ").length == 2) {
                // System.out.println(s.split("/")[1].strip().split(" ").length + "neineuin");
                return s.split("/")[1].strip().split(" ")[0];
            }
            return "";

        }
        return null;
    }

}
