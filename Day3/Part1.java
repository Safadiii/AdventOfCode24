import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        try {
            File input = new File("input1.txt");
            Scanner scanner = new Scanner(input);
            int total = 0;
            boolean allowed = true;
            while(scanner.hasNextLine()) {
                String data = scanner.nextLine();
                //part 2
                String instruction = "do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)";
                //
                Pattern pattern = Pattern.compile(instruction);
                Matcher matcher = pattern.matcher(data);
                while(matcher.find()) {
                    System.out.println("M " + matcher.group(0));
                    System.out.println(total);
                    if(matcher.group().equals("do()")) {
                        allowed = true;
                    } else if(matcher.group().equals("don't()")) {
                        allowed = false;
                    } else {
                        if(allowed) {
                            String mul = matcher.group();
                            Pattern pattern_num = Pattern.compile("\\d+");
                            Matcher matcher_num = pattern_num.matcher(mul);
                            while(matcher_num.find()) {
                                int i = Integer.valueOf(matcher_num.group());
                                if(matcher_num.find()) {
                                    int b = Integer.valueOf(matcher_num.group());
                                    total += b*i;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
