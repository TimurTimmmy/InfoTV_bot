import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class TvInfo {
    private String tvinfo = "ТВ программа на сегодня:";

    public TvInfo(int tvnumber) throws IOException {
        Document docTv = Jsoup.connect("http://tele.ru/programma/moskva/").get();
        int y = 0;
        int i = docTv.getElementsByAttributeValue("class", "tvProgram").
                get(tvnumber).getElementsByAttributeValue("class", "time").size();

        while (y < i){
        Element timeElement = docTv.getElementsByAttributeValue("class", "time").get(y);
        Element tvElement = docTv.getElementsByAttributeValue("class", "fb").get(y);
        tvinfo = tvinfo + "\n" + timeElement.text() + " - " + tvElement.text();
        y++;
        }
        System.out.println(i);
    }

    public String getTvinfo() {
        return tvinfo;
    }

}
