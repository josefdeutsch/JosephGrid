package com.example.josephgrid;

import fr.maif.json.Json;
import fr.maif.json.JsonFormat;
import fr.maif.json.JsonRead;
import fr.maif.json.JsonWrite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

import static fr.maif.json.Json.$;
import static fr.maif.json.JsonRead._string;

@Getter
@Builder
@AllArgsConstructor
@FieldNameConstants
public final class Url {

    //All final attributes
    private final String url;
    private final String ext; // required
    private final String id; // optional

    // A JsonFormat is both JsonRead and JsonWrite
    public static JsonFormat<Url> format() {
        return JsonFormat.of(reader(), writer());
    }

    public static JsonRead<Url> reader() {
        return _string("base", Url.builder()::url)
                .and(_string("ext"), Url.UrlBuilder::ext)
                .and(_string("id"), Url.UrlBuilder::id)
                .map(Url.UrlBuilder::build);
    }

    public static JsonWrite<Url> writer() {
        return url -> Json.obj(
                $("url", url.url),
                $("ext", url.ext),
                $("id", url.id)
        );
    }

}
