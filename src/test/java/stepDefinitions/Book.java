package stepDefinitions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    //This is a POJO
    String isbn;
    String title;
    String subTitle; // Make sure the field names match the JSON keys
    String author;
    String publish_date; // Match the date format in the JSON
    String publisher;
    int pages;
    String description;
    String website;
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getWebsite() {
        return website;
    }
}
