package net.sf.jabref.model.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ContentSelectors {

    private final List<ContentSelector> contentSelectors;


    public ContentSelectors() {
        contentSelectors = new ArrayList<>();
    }

    public void addContentSelector(ContentSelector contentSelector) {
        Objects.requireNonNull(contentSelector);

        this.contentSelectors.add(contentSelector);
    }

    public List<String> getSelectorValuesForField(String fieldName) {
        for(ContentSelector selector: contentSelectors) {
            if(selector.getFieldName().equals(fieldName)){
                return selector.getValues();
            }
        }

        return Collections.emptyList();
    }

    public void removeSelector(String fieldName) {
        contentSelectors.remove(fieldName);
    }

    public List<ContentSelector> getContentSelectors(){
        return Collections.unmodifiableList(contentSelectors);
    }

    public static ContentSelector parse(String key, String values) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(values);

        List<String> valueList = Arrays.asList(values.split(";"));

        return new ContentSelector(key, valueList);
    }

    public List<String> getFieldNamesWithSelectors() {
        List<String> result = new ArrayList<>(contentSelectors.size());

        for(ContentSelector selector: contentSelectors) {
            result.add(selector.getFieldName());
        }

        return result;
    }
}
