package Helper;
import java.util.UUID;

public class RandomStringGenerator {
    public String generateRandomString(int length) {
        String randomString = UUID.randomUUID().toString();
        randomString = randomString.replaceAll("-", "");
        return randomString.substring(0, length);
    }
}
