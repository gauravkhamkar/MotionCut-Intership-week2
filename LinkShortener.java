import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;

    public LinkShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    public String shorten(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        
        
        while (shortToLongMap.containsKey(shortUrl)) {
            shortUrl = generateShortUrl(longUrl);
        }

        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);

        return shortUrl;
    }

    public String expand(String shortUrl) {
        return shortToLongMap.getOrDefault(shortUrl, "Invalid short URL");
    }

    private String generateShortUrl(String longUrl) {
        try {
            // Use a simple hash function for demonstration purposes
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(longUrl.getBytes());
            return bytesToHex(digest).substring(0, 8); // Use first 8 characters as short URL
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
