import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Main {

    // Define your whitelist
    private static final List<String> WHITELIST = Arrays.asList(
            "https://api.ext.payconiq.com",
            "https://api.payconiq.com",
            "https://ext.payconiq.com",
            "https://payconiq.com"
    );

    private static final List<String> TO_CHECK = Arrays.asList(
            "https://api.payconiq.com/some/path",
            "https://untrusted.com",
            "https://portal.ext.payconiq.com",
            "https://portal.ext.payconiq.com/qrcode?c=https%3A%2F%2Fpayconiq.com%2Fpay%2F2%2F5bdaf066b93d1c000bde9683",
            "https://untrusted.com/qrcode?c=https%3A%2F%2Fpayconiq.com%2Fpay%2F2%2F5bdaf066b93d1c000bde9683"
    );


    public static void main(String[] args) {
        for(String toCheck: TO_CHECK){
            System.out.println("URL: " + toCheck + "\nis " + (isWhitelisted(toCheck) ? "whitelisted\n" : "blocked\n"));
        }


    }

    public static boolean isWhitelisted(String url) {
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            String host = uri.getHost();

            if (scheme == null || host == null) {
                return false;
            }

            String baseUrl = scheme + "://" + host;

            for (String allowedUrl : WHITELIST) {
                URI allowedUri = new URI(allowedUrl);
                String allowedScheme = allowedUri.getScheme();
                String allowedHost = allowedUri.getHost();

                if (allowedScheme == null || allowedHost == null) {
                    continue;
                }

                // Check if the URL host ends with the whitelisted host
                if (host.equals(allowedHost) || host.endsWith("." + allowedHost)) {
                    if (scheme.equalsIgnoreCase(allowedScheme)) {
                        return true;
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}