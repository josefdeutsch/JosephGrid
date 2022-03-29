package com.example.josephgrid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import fr.maif.json.Json;

public class MainActivity extends AppCompatActivity {

    List<List<Integer>> TEMPLATE_TRIANGLE_LIST = Arrays.asList(
            Arrays.asList(0, 1, 2, 3, 20, 21, 22, 23, 40, 41, 42, 43),
            Arrays.asList(5, 6, 7, 24, 25, 26, 27, 44, 45, 46, 47, 4),
            Arrays.asList(0, 11, 28, 29, 30, 31, 48, 49, 50, 51, 8, 9),
            Arrays.asList(13, 14, 15, 32, 33, 34, 35, 52, 53, 54, 55, 12),
            Arrays.asList(16, 17, 18, 19, 36, 37, 38, 39, 56, 57, 58, 59)
    );


    Integer[][] TEMPLATE_TRIANGLE = new Integer[][]{
            {0, 1, 2, 3, 20, 21, 22, 23, 40, 41, 42, 43},
            {5, 6, 7, 24, 25, 26, 27, 44, 45, 46, 47, 4},
            {10, 11, 28, 29, 30, 31, 48, 49, 50, 51, 8, 9},
            {13, 14, 15, 32, 33, 34, 35, 52, 53, 54, 55, 12},
            {16, 17, 18, 19, 36, 37, 38, 39, 56, 57, 58, 59}
    };

    final int FIRST = 0;
    final int BLOCK = 60;
    final int TOTAL = 65;

    final String BASE = "http://joseph3d.com/wp-content/uploads/2019/06/";
    final String EXT = ".png";
    final String REGEX = "%04d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Integer> basis =
                Stream.of(TEMPLATE_TRIANGLE)
                        .flatMap(Stream::of)
                        .collect(Collectors.toList());

        final Integer[][] exponent =
                IntStream.range(FIRST, TOTAL)
                        .mapToObj(x ->
                                basis.stream()
                                        .map(y -> y + (BLOCK * x))
                                        .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);

        final List<String> result =
                Stream.of(exponent)
                        .flatMap(Stream::of)
                        .map(Object::toString)
                        .collect(Collectors.toList());

        final List<String> nodes =
                result.stream()
                        .map(src -> String.format(REGEX, Integer.valueOf(src)))
                        .map(src -> src)
                        .map(src -> Json.toJson(Url.builder()
                                .url(src)
                                // .ext(EXT)
                                // .id(UUID.randomUUID().toString())
                                .build(), Url.format()))
                        .map(Json::stringify)
                        .collect(Collectors.toList());

        //nodes.forEach(System.out::println);
        //Log.d(TAG, "onCreate: " + sb.toString());
        //String str = nodes.toString();
        //System.out.println(str);

    }
}