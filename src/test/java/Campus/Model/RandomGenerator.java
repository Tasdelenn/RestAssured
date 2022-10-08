package Campus.Model;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {

    public static String getRandomName(){
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String getRandomShortName(){
        return RandomStringUtils.randomAlphanumeric(2);
    }

    public static int getRandomCode(){
        return Integer.parseInt(RandomStringUtils.randomNumeric(4));
    }
}
