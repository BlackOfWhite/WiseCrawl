import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Visitor {

    private static final Pattern htmlPattern = Pattern.compile("href=\"http(s?)://.[^\"]*");

    public static Set<String> visit(String url) {
        Set<String> urls = new LinkedHashSet<>();
        URLConnection connection;
        try {
            connection = new URL(url).openConnection();
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                scanner.useDelimiter("\\Z");
                while (scanner.hasNext()) {
                    String next = scanner.next();
                    Matcher matcher = htmlPattern.matcher(next);
                    while (matcher.find()) {
                        urls.add(matcher.group().substring(6));
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    public static Set<String> getLinks(String htmlContent) {
        return Stream.of(htmlContent.split(" "))
                .filter(s -> s.startsWith("href=\"http") && s.endsWith("\""))
                .map(s -> s.substring(6, s.length() - 1)).collect(Collectors.toSet());
    }
}
