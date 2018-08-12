import java.util.LinkedList;
import java.util.List;

/**
 * Created by tanya on 12.08.2018.
 */

public class SectionData {
    public static LinkedList <String> data;

    public SectionData(){
        data = new LinkedList<String>();

        data.add("Новости Москвы");
        data.add("Рамблер — медийный портал");
        data.add("Новости политики и власти");

        data.add("Новости спорта");
        data.add("Происшествия сегодня");
        data.add("Автомобильные новости");
        data.add("Новости экономики и финансов, прогнозы курсов и аналитика биржевых рынков");

    }

    public List<String> getData(){
        return data;
    }
}
