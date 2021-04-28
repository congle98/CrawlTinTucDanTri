import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class demo {
    public static void main(String[] args) {

//        // cách cào bằng jsoup
//        try {
//            Document document = Jsoup.connect("https://dantri.com.vn/").get();
//            // lần theo dấu từng thằng một
//            Elements texts = document.select("div.container > ol > li > a");
//            for (Element text:texts
//            ) {
//                System.out.println(text.text());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }







        String link ="https://dantri.com.vn/";
        URL url = null;
        try {
            url = new URL(link);
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            // xoá các ký tự ngắt dòng (xuống dòng)
            content = content.replaceAll("\\R", "");
            // không dùng ^ và $ vì nó lọc chuỗi ở giữa các dòng chứ ko bắt đầu và kết thúc trong 1 dòng
            Pattern p = Pattern.compile("<li class=\"dropdown dropdown--hover\">(.*?)</a>");
            Matcher m = p.matcher(content);
            while (m.find()) {
                // sau khi tách được ra đoạn cơ bản đầu tiên rồi, thì lại tách tiếp, dùng vòng lặp ở trong để tiếp tục tách
                // ở đây không có dấu ? vì để nó tham lam lấy hết mọi thứ từ html"> đến ko lấy được vì sau khi tách lần 1 đằng sau nó ko còn ký tự nào
                Pattern p1 = Pattern.compile("htm\">(.*)");
                Matcher m2 = p1.matcher(m.group(1));
                while (m2.find()){
                    System.out.println(m2.group(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
