
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class my
{
    private static class parse
    {
        parse(String url)
        {
            try
            {
                doc = Jsoup.connect(url).get();
                System.out.print("Successfully connected\n");
            }
            catch (Exception e)
            {
                System.out.print(e.getMessage()+"\n");
            }
        }
        public void show_days()
        {
            ArrayList<String> result=new ArrayList<String>();
            Elements b=doc.getAllElements();
            String pretmp="";
            for (Element element : b.select("span, .schedule__date"))
            {
                String tmp=element.text();
                if(tmp!=pretmp&&tmp.length()>1)
                {
                    result.add(tmp);
                }
                pretmp=tmp;
            }
            result.remove(0);
            result.remove(0);
            for(int i=0;i<result.size(); i++)
            {
                String text = result.get(i);
                Pattern pattern = Pattern.compile("показать");
                Matcher matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                if(text.length()<8)
                {
                    pattern = Pattern.compile("[0-9]+[:]+[0-9]");
                    matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        result.remove(i--);
                        continue;
                    }
                }
                pattern = Pattern.compile("[.]");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("[0-9]+[ ][0-9]+");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("[0-9]+[/][0-9]+");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("Сетка");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("iCal");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("Печать");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("Групп");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("Поток");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                pattern = Pattern.compile("чётная");
                matcher = pattern.matcher(text);
                if (matcher.find())
                {
                    result.remove(i--);
                    continue;
                }
                if(i>1)
                {
                    pattern = Pattern.compile(text);
                    matcher = pattern.matcher(result.get(i-1));
                    if (matcher.find())
                    {
                        result.remove(i--);
                        continue;
                    }
                }
            }
            list=result;
        }

        private static Document doc;
        public  ArrayList<String> list;
    }


    public static void main(String[] args)
    {
        parse example= new parse("https://ruz.spbstu.ru/faculty/95/groups/27666");
        example.show_days();
        for(String elem:example.list)
        {
            System.out.print(elem+"\n");
        }

        example.list.get(9);
        for(int i=0;i<example.list.size();i++)
        {
            example.list.get(i);
        }
      
        for(String elem: example.list)
        {
            System.out.print(elem+"\n");
        }
    }
}
